package com.example.notelist;


import java.util.Calendar;

public class Note {

    private int noteId;
    private String noteTitle;
    private String noteBodyText;
    private int priority;
    private Calendar dateCreated;

    public Note() {
        noteId = -1;
        dateCreated = Calendar.getInstance();
        priority = -1;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }
}
