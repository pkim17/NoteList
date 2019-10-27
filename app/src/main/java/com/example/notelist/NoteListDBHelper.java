package com.example.notelist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteListDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_NOTES =
            "create table notes (_id integer primary key autoincrement, "
            + "notetitle text not null, bodytext text, "
            + "priority text, datecreated text);";


    public NoteListDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)    {
        Log.w(NoteListDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will alter table data");
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }
}
