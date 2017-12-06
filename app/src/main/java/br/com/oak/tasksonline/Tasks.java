package br.com.oak.tasksonline;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Franc on 05/12/2017.
 */

public class Tasks extends CursorAdapter {

    private TaskDbHelper tarefasDBHelper;
    private static String Tag = "Task Adapter";


    public Tasks(Context context, Cursor c) {
        super(context, c, 0);
        tarefasDBHelper = TaskDbHelper.getInstance(context);
    }

    @Override //layout de visualizaçao do adapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_tarefa,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTituloTarefa = (TextView) view.findViewById(R.id.txtTarefas);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.Tarefa.COLUMN_NAME_TITULO));
        txtTituloTarefa.setText( "   " + titulo);
        TextView txtStatus = (TextView) view.findViewById(R.id.txtStatusTarefa);
        String status = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.Tarefa.COLUMN_NAME_STATUS));
        txtStatus.setText("Status: " + status);
    }

    public TaskDbHelper getTarefasDBHelper() {
        return tarefasDBHelper;
    }
}