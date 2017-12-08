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

public class TasksCursor extends CursorAdapter {

    private TaskDbHelper tarefasDBHelper;
    private static String Tag = "Task Adapter";


    public TasksCursor(Context context, Cursor c) {
        super(context, c, 0);
        tarefasDBHelper = TaskDbHelper.getInstance(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_list_tarefas,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblTarefa  = (TextView) view.findViewById(R.id.lblTarefa);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.Tarefa.COLUMN_NAME_TITULO));
        lblTarefa.setText(titulo);


        TextView lblEstado  = (TextView) view.findViewById(R.id.lblEstado);
        String status = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.Tarefa.COLUMN_NAME_STATUS));
        lblEstado.setText(status);

        TextView lblDificuldade  = (TextView) view.findViewById(R.id.lblDificuldade);
        Integer dificuldade = cursor.getInt(cursor.getColumnIndexOrThrow(TaskContract.Tarefa.COLUMN_NAME_DIFICULDADE));
        lblDificuldade.setText("Dificuldade: " + dificuldade);

    }

    public TaskDbHelper getTarefasDBHelper() {
        return tarefasDBHelper;
    }
}
