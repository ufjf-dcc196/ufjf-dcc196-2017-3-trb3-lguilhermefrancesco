package br.com.oak.tasksonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Franc on 05/12/2017.
 */

public class TaskHelper {

    private static TaskHelper instance = null;
    private static String Tag = "TaskHelper";
    TasksCursor tasksCursor = null;
    private String[] statusList = {"A fazer", "Em Execução" , "Bloqueada",  "Concluída" };
    private String[] nivelList = {"1","2","3","4","5"};


    public static TaskHelper getInstance() {
        if (instance == null) {
            instance = new TaskHelper();
        }
        return instance;
    }

    public void initTasks(Context baseContext) {
        tasksCursor = new TasksCursor(baseContext,null);
        PushTask();
    }

    public String[] getStatusList() {
        return statusList;
    }

    public void setStatusList(String[] statusList) {
        this.statusList = statusList;
    }

    public String[] getNivelList() {
        return nivelList;
    }

    public void NivelList(String[] nivelList) {
        this.nivelList = nivelList;
    }

    public TasksCursor getTasksCursor() {
        return tasksCursor;
    }

    public void setTasksCursor(TasksCursor tasksCursor) {
        this.tasksCursor = tasksCursor;
    }

    public void PushTask() {
        try {
            SQLiteDatabase db = getTasksCursor().getTarefasDBHelper().getReadableDatabase();
            String[] visao = {
                    TaskContract.Tarefa._ID,
                    TaskContract.Tarefa.COLUMN_NAME_TITULO,
                    TaskContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TaskContract.Tarefa.COLUMN_NAME_DIFICULDADE,
                    TaskContract.Tarefa.COLUMN_NAME_STATUS
            };
            Cursor c = db.query(TaskContract.Tarefa.TABLE_NAME, visao, null, null, null, null, TaskContract.Tarefa.COLUMN_NAME_STATUS);
            tasksCursor.changeCursor(c);
        } catch (Exception e) {
            Log.e(Tag, "M-pushTask");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
    }

    public long AddNovaTask(Task novaTask) {
        try {
            SQLiteDatabase db = getTasksCursor().getTarefasDBHelper().getReadableDatabase();
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_TITULO, novaTask.getTitulo());
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_DESCRICAO, novaTask.getDescricao());
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_STATUS, novaTask.getStatus());
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_DIFICULDADE, novaTask.getNivel());
            long id = db.insert(TaskContract.Tarefa.TABLE_NAME, null, dataToInsert);
            PushTask();
            return  id;
        } catch (Exception e) {
            Log.e(Tag, "M-AddNewTask");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
        return -1;
    }

    public Task SearchForTask(int id_task) {
        try {
            SQLiteDatabase db = getTasksCursor().getTarefasDBHelper().getReadableDatabase();
            Task novaTask = new Task();
            String[] visao = {
                    TaskContract.Tarefa._ID,
                    TaskContract.Tarefa.COLUMN_NAME_TITULO,
                    TaskContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TaskContract.Tarefa.COLUMN_NAME_STATUS,
                    TaskContract.Tarefa.COLUMN_NAME_DIFICULDADE,
            };
            String selecao = TaskContract.Tarefa._ID + " = ? ";
            String[] arg = {String.valueOf(id_task)};
            Cursor c = db.query(TaskContract.Tarefa.TABLE_NAME, visao, selecao, arg, null, null, null);
            if(c!=null){
                c.moveToFirst();
                novaTask.setId(c.getInt(0));
                novaTask.setTitulo(c.getString(1));
                novaTask.setDescricao(c.getString(2));
                novaTask.setStatus(c.getString(3));
                novaTask.setNivel(c.getInt(4));
            }
            return novaTask;
        } catch (Exception e) {
            Log.e(Tag, "M-SearchForTask");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
        return null;
    }

    public void DeleteTask(Task myTask) {
        try {
            SQLiteDatabase db = getTasksCursor().getTarefasDBHelper().getReadableDatabase();
            String where = TaskContract.Tarefa._ID + " = ? ";
            String[] arg = {String.valueOf(myTask.getId())};
            db.delete(TaskContract.Tarefa.TABLE_NAME,where,arg);
        } catch (Exception e) {
            Log.e(Tag, "M-DeleteTask");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
    }

    public void UpdateTask(Task myTask) {
        try {
            SQLiteDatabase db = getTasksCursor().getTarefasDBHelper().getReadableDatabase();
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_TITULO, myTask.getTitulo());
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_DESCRICAO, myTask.getDescricao());
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_STATUS, myTask.getStatus());
            dataToInsert.put(TaskContract.Tarefa.COLUMN_NAME_DIFICULDADE, myTask.getNivel());
            String where = TaskContract.Tarefa._ID + " = ? ";
            String[] arg = {String.valueOf(myTask.getId())};
            db.update(TaskContract.Tarefa.TABLE_NAME,dataToInsert,where,arg);
        } catch (Exception e) {
            Log.e(Tag, "M-UpdateTask");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
    }

    public void CriaComposicaoDeTags(long id_task, long id_tag) {
        try {
            SQLiteDatabase db = getTasksCursor().getTarefasDBHelper().getReadableDatabase();
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put(TaskContract.Composicao.COLUMN_NAME_ID_TAG, id_tag);
            dataToInsert.put(TaskContract.Composicao.COLUMN_NAME_ID_TAREFA, id_task);
            long id = db.insert(TaskContract.Composicao.TABLE_NAME, null, dataToInsert);
            PushTask();
        } catch (Exception e) {
            Log.e(Tag, "M-CriaComposicaoDeTags");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
    }
}