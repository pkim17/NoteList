package com.example.notelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initAddNoteButton();
        initListButton();
        initSettingsButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();

    }

    private void initAddNoteButton() {
        ImageButton ibAddNote = (ImageButton) findViewById(R.id.imageButtonAddNote);
        ibAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, AddNoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, NoteListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }

    private void initSettings() {

//        Retrieves string value associated with sortfield key
//        If no value stored, default value is assigned

        String sortBy = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sortfield", "notetitle");
        String sortOrder = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sortorder", "ASC");

        RadioButton radioSortTitle = (RadioButton) findViewById(R.id.radioButtonSortTitle);
        RadioButton radioSortPriority = (RadioButton) findViewById(R.id.radioButtonSortPriority);
        RadioButton radioSortDateCreated = (RadioButton) findViewById(R.id.radioButtonSortDateCreated);


//        Value retrieved for preferred sort field is to determine which RadioButton should be checked

        if (sortBy.equalsIgnoreCase("notetitle")) {
            radioSortTitle.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("priority")) {
            radioSortPriority.setChecked(true);
        }
        else {
            radioSortDateCreated.setChecked(true);
        }


        RadioButton radioAscending = (RadioButton) findViewById(R.id.radioButtonAscending);
        RadioButton radioDescending = (RadioButton) findViewById(R.id.radioButtonDescending);

        if (sortOrder.equalsIgnoreCase("ASC")) {
            radioAscending.setChecked(true);
        }
        else {
            radioDescending.setChecked(true);
        }

    }

    private void initSortByClick()  {

        RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {

                RadioButton radioTitle = (RadioButton) findViewById(R.id.radioButtonSortTitle);
                RadioButton radioPriority = (RadioButton) findViewById(R.id.radioButtonSortPriority);
                RadioButton radioDateCreated = (RadioButton) findViewById(R.id.radioButtonSortDateCreated);


                if (radioTitle.isChecked()) {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "notetitle").commit();
                }
                else if (radioPriority.isChecked()) {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "priority").commit();
                }
                else {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortfield", "datecreated").commit();
                }
            }
        });
    }

    private void initSortOrderClick()  {

        RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton radioAscending = (RadioButton) findViewById(R.id.radioButtonAscending);
                RadioButton radioDescending = (RadioButton) findViewById(R.id.radioButtonDescending);

                if (radioAscending.isChecked()) {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortorder", "ASC").commit();
                }
                else {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortorder", "DSC").commit();
                }
            }
        });
    }






}
