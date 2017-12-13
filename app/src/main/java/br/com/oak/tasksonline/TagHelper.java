package br.com.oak.tasksonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Franc on 13/12/2017.
 */

public class TagHelper {

    private static TagHelper instance = null;
    private static String Tag = "TagHelper";
    Tag tag = null;
    Tag tagTask = null;


    private ArrayList<String> tags = new ArrayList<>();

    public static TagHelper getInstance() {
        if (instance == null) {
            instance = new TagHelper();
        }
        return instance;
    }

    public void initTagAdapter(Context baseContext) {
        tag = new Tag(baseContext,null);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Tag getTag() {
        return tag;
    }

    public void PushTags() {
        try {
            SQLiteDatabase db = getTag().getTarefasDBHelper().getReadableDatabase();
            String[] visao = {
                    TaskContract.Tags._ID,
                    TaskContract.Tags.COLUMN_NAME_TAG
            };
            Cursor c = db.query(TaskContract.Tags.TABLE_NAME, visao, null, null, null, null, TaskContract.Tags.COLUMN_NAME_TAG);
            tag.changeCursor(c);
        } catch (Exception e) {
            Log.e(Tag, "M-pushTag");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
    }

    public void AddNewTag(String s) {
        try {
            SQLiteDatabase db = getTag().getTarefasDBHelper().getReadableDatabase();
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put(TaskContract.Tags.COLUMN_NAME_TAG, s);
            long id = db.insert(TaskContract.Tags.TABLE_NAME, null, dataToInsert);
            PushTags();
        } catch (Exception e) {
            Log.e(Tag, "M-AddNewTag");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
    }

    public boolean VerifyTag(String tag) {
        try {
            SQLiteDatabase db = getTag().getTarefasDBHelper().getReadableDatabase();
            String[] visao = {
                    TaskContract.Tags._ID,
                    TaskContract.Tags.COLUMN_NAME_TAG,
            };
            String selecao = TaskContract.Tags.COLUMN_NAME_TAG + " = ? ";
            String[] arg = {tag};
            Cursor c = db.query(TaskContract.Tags.TABLE_NAME, visao, selecao, arg, null, null, null);
            c.moveToNext();
            String temp = c.getString(1);
            if(temp.equals(tag)){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            Log.e(Tag, "M-VerificarTag");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
        return true;
    }

    public int SearchTag(String tag) {
        try {
            SQLiteDatabase db = getTag().getTarefasDBHelper().getReadableDatabase();
            String[] visao = {
                    TaskContract.Tags._ID,
                    TaskContract.Tags.COLUMN_NAME_TAG,
            };
            String selecao = TaskContract.Tags.COLUMN_NAME_TAG + " = ? ";
            String[] arg = {tag};
            Cursor c = db.query(TaskContract.Tags.TABLE_NAME, visao, selecao, arg, null, null, null);
            c.moveToNext();
            return c.getInt(0);
        } catch (Exception e) {
            Log.e(Tag, "M-VerificarTag");
            Log.e(Tag, e.getLocalizedMessage());
            Log.e(Tag, e.getStackTrace().toString());
        }
        return -1;
    }

    public StringBuilder getTagsForTask(int id_task) {
        String rawQuery = "";
        StringBuilder sb = new StringBuilder();
        try {
            SQLiteDatabase db = getTag().getTarefasDBHelper().getReadableDatabase();
            rawQuery = "SELECT " + TaskContract.Tags.COLUMN_NAME_TAG + " FROM " + TaskContract.Tarefa.TABLE_NAME + " AS a "
                    + " INNER JOIN " + TaskContract.Composicao.TABLE_NAME + " AS b "
                    + " INNER JOIN " + TaskContract.Tags.TABLE_NAME + " AS c "
                    + " ON a." + TaskContract.Tarefa._ID + " = b." + TaskContract.Composicao.COLUMN_NAME_ID_TAREFA
                    + " AND b." + TaskContract.Composicao.COLUMN_NAME_ID_TAG + " = c." + TaskContract.Tags._ID
                    + " WHERE a." + TaskContract.Tarefa._ID + " = " + id_task ;
            String[] arg = {String.valueOf(id_task)};
            Cursor c = db.rawQuery(rawQuery,null);
            while (c.moveToNext()) {
                sb.append(c.getString(0));
                sb.append(",");
            }
            return sb;
        } catch (Exception e) {
            Log.e(Tag, rawQuery);
            Log.e(Tag, "M-GetTagsForTask");
            Log.e(Tag , e.getLocalizedMessage());
            Log.e(Tag , e.getStackTrace().toString());
        }
        return null;
    }
}
