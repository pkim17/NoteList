package com.example.notelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    ArrayList<Note> notes;
    boolean isDeleting = false;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initAddNoteButton();
        initListButton();
        initSettingsButton();
        initItemClick();
        initDeleteButton();

    }

    @Override
    protected void onResume() {
        super.onResume();

        String sortBy = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sortfield", "notetitle");
        String sortOrder = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sortorder", "ASC");

        NoteListDataSource ds = new NoteListDataSource(this);

        try {

            ds.open();
            notes = ds.getNotes(sortBy, sortOrder);
            ds.close();

//            If data contains existing notes
            if (notes.size() > 0)    {
                ListView listView = findViewById(R.id.listViewNotes);
                adapter = new NoteAdapter(this, notes);
                listView.setAdapter(adapter);


            }

            //If there is no existing note, goto AddNoteActivity
            else {
                Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving notes", Toast.LENGTH_LONG).show();

        }
    }

    private void initAddNoteButton() {
        ImageButton ibAddNote = (ImageButton) findViewById(R.id.imageButtonAddNote);
        ibAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonList);
        ibList.setEnabled(false);
    }

    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteListActivity.this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initDeleteButton() {
        final Button deleteButton = (Button) findViewById(R.id.buttonNoteListDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDeleting) {
                    deleteButton.setText("Delete");
                    isDeleting = false;
                    adapter.notifyDataSetChanged();
                }
                else {
                    deleteButton.setText("Done Deleting");
                    isDeleting = true;
                }
            }
        });
    }

    private void initItemClick()    {
        ListView listView = (ListView) findViewById(R.id.listViewNotes);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Note selectedNote = notes.get(position);

                if (isDeleting) {
                    adapter.showDelete(position, itemClicked, NoteListActivity.this, selectedNote);
                }
                else {
                    Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                    intent.putExtra("noteid", selectedNote.getNoteId());
                    startActivity(intent);
                }
            }
        });
    }





}
