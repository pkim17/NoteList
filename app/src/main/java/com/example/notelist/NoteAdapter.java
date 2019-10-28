package com.example.notelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

            if (note.getPriority() == 3) {
                noteTextPriority.setText("High");
            }
            else if (note.getPriority() == 2) {
                noteTextPriority.setText("Medium");
            }
            else if (note.getPriority() == 1) {
                noteTextPriority.setText("Low");
            }
        }
        catch (Exception e)   {
            e.printStackTrace();
            e.getCause();
        }

        return v;
    }

    public void showDelete(final int position, final View convertView, final Context context, final Note note)    {

        View v = convertView;
        final Button b = (Button) v.findViewById(R.id.buttonDelete);

        if (b.getVisibility() == View.INVISIBLE)    {
            b.setVisibility(View.VISIBLE);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideDelete(position, convertView, context);
                    items.remove(note);
                    deleteOption(note.getNoteId(), context);
                }
            });
        }
        else {
            hideDelete(position, convertView, context);
        }
    }

    private void deleteOption(int contactToDelete, Context context) {

        NoteListDataSource db = new NoteListDataSource(context);
        try {
            db.open();
            db.deleteNote(contactToDelete);
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(adapterContext, "Delete Contact Failed", Toast.LENGTH_LONG).show();
        }
        this.notifyDataSetChanged();
    }

    public void hideDelete(int position, View convertView, Context context) {
        View v = convertView;
        final Button b = (Button) v.findViewById(R.id.buttonDelete);
        b.setVisibility(View.INVISIBLE);
        b.setOnClickListener(null);
    }



}
