package com.example.notelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    private ArrayList<Note> items;
    private Context adapterContext;

    public NoteAdapter(Context context, ArrayList<Note> items) {
        super(context, R.layout.list_note_item, items);
        adapterContext = context;
        this.items = items;
    }

    // Populates the list
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            Note note = items.get(position);

            if (v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_note_item, null);
            }

            TextView noteTextTitle = (TextView) v.findViewById(R.id.textViewTitle);
            noteTextTitle.setText(note.getNoteTitle());
            TextView noteTextPriority = (TextView) v.findViewById(R.id.textViewPriority);
            noteTextPriority.setText(note.getPriority());
        }
        catch (Exception e)   {
            e.printStackTrace();
            e.getCause();
        }

        return v;
    }



}
