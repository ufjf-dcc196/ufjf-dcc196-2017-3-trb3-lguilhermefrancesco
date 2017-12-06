package br.com.oak.tasksonline;

/**
 * Created by Franc on 05/12/2017.
 */

import android.provider.BaseColumns;

public final class TaskContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";

    public static final String SQL_CREATE_TAREFAS = "CREATE TABLE " + Tarefa.TABLE_NAME + " (" +
            Tarefa._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tarefa.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_DESCRICAO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_DIFICULDADE + INT_TYPE + SEP +
            Tarefa.COLUMN_NAME_STATUS + TEXT_TYPE +
            ")";

    public static final String SQL_DROP_TAREFAS = "DROP TABLE IF EXISTS " + Tarefa.TABLE_NAME;

    public TaskContract() {
    }
    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefas";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_DIFICULDADE = "dificuldade";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    //Vai ser assim as tags???//
    /*
    public static final class Tags implements BaseColumns {
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_NAME_TAG = "tag";
    }
    */

    /*
    public static final class Composicao implements BaseColumns{
        public static final String TABLE_NAME = "composicao";
        public static final String COLUMN_NAME_ID_TAG = "id_tag";
        public static final String COLUMN_NAME_ID_TAREFA = "id_tarefa";
    }
    */
}
