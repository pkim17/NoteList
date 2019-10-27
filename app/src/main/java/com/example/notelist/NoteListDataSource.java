package com.example.notelist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class NoteListDataSource {

    private SQLiteDatabase database;
    private NoteListDBHelper dbHelper;

    public NoteListDataSource(Context context) {
        dbHelper = new NoteListDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertNote(Note n) {

        boolean didSucceed = false;

        try {

            ContentValues initialValues = new ContentValues();

            initialValues.put("notetitle", n.getNoteTitle());
            initialValues.put("bodytext", n.getNoteBodyText());
            initialValues.put("priority", n.getPriority());
            initialValues.put("datecreated", String.valueOf(n.getDateCreated().getTimeInMillis()));

            didSucceed = database.insert("notes", null, initialValues) > 0;

        } catch (Exception e) {
            //Do nothing - Will return false if there is an exception
        }

        return didSucceed;

    }

    public boolean updateNote(Note n) {

        boolean didSucceed = false;

        try {

            Long rowId = (long) n.getNoteId();
            ContentValues updateValues = new ContentValues();

            updateValues.put("notetitle", n.getNoteTitle());
            updateValues.put("bodytext", n.getNoteBodyText());
            updateValues.put("priority", n.getPriority());
            updateValues.put("datecreated", String.valueOf(n.getDateCreated().getTimeInMillis()));

            didSucceed = database.update(
                    "notes", updateValues,"_id=" + rowId, null) > 0;


        } catch (Exception e) {
            //Do nothing - Will return false if there is an exception
        }

        return didSucceed;
    }

    public int getLastNoteId() {
        int lastId = -1;

        try {
            String query = "Select MAX(_id) from notes";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();

        }

        catch (Exception e) {
            lastId = -1;
        }

        return lastId;
    }


    public ArrayList<Note> getNotes(String sortTitle, String sortPriority, String sortDate) {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            String query = "SELECT * FROM notes ORDER BY " + sortTitle + " " + sortPriority + " " + sortDate;
            System.out.println("Test1");
            Cursor cursor = database.rawQuery(query, null);
            System.out.println("Test2");

            Note newNote;
            cursor.moveToFirst();
            while (!cursor.isAfterLast())   {
                newNote = new Note();
                newNote.setNoteId(cursor.getInt(0));
                newNote.setNoteTitle(cursor.getString(1));
                newNote.setNoteBodyText(cursor.getString(2));
                newNote.setPriority(cursor.getString(3));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.valueOf(cursor.getString(4)));
                newNote.setDateCreated(calendar);


                notes.add(newNote);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch   (Exception e)   {
            notes = new ArrayList<Note>();
        }
        return notes;
    }

    public Note getSpecificNote(int noteId)    {
        Note note = new Note();
        String query = "SELECT * FROM notes WHERE _id=" + noteId;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst())   {
            note.setNoteId(cursor.getInt(0));
            note.setNoteTitle(cursor.getString(1));
            note.setNoteBodyText(cursor.getString(2));
            note.setPriority(cursor.getString(3));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.valueOf(cursor.getString(4)));
            note.setDateCreated(calendar);


            cursor.close();



        }
        return note;
    }

    public boolean deleteNote(int noteId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("notes", "_id=" + noteId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing - return value already set to false
        }
        return didDelete;
    }
}
