package br.com.oak.tasksonline;

/**
 * Created by Franc on 13/12/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Tag extends CursorAdapter{

    private TaskDbHelper tagsDBHelper;
    private static String Tag = "Task Adapter";


    public Tag(Context context, Cursor c) {
        super(context, c, 0);
        tagsDBHelper = TaskDbHelper.getInstance(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return null;
        // /return LayoutInflater.from(context).inflate(R.layout.layout_tags,null, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //TextView txtTag = (TextView) view.findViewById(R.id.txtTag);
        //String tag = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.Tags.COLUMN_NAME_TAG));
        //txtTag.setText("tag:" + tag);
    }

    public TaskDbHelper getTarefasDBHelper() {
        return tagsDBHelper;
    }

}
