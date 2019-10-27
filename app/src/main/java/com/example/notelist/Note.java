package com.example.notelist;


import java.util.Calendar;

public class Note {

    private int noteId;
    private String noteTitle;
    private String noteBodyText;
    private String priority;
    private Calendar dateCreated;

    public Note() {
        noteId = -1;
        dateCreated = Calendar.getInstance();

    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteBodyText() {
        return noteBodyText;
    }

    public void setNoteBodyText(String noteBodyText) {
        this.noteBodyText = noteBodyText;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }
}
