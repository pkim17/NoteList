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
        initSortTitleClick();
        initSortPriorityClick();
        initSortDateClick();

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

        String sortTitle = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sorttitle", "ASC");
        String sortPriority = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sortpriority", "high");
        String sortDateCreated = getSharedPreferences("MyNoteListPreferences", Context.MODE_PRIVATE)
                .getString("sortdate", "ASC");

        RadioButton radioTitleAscending = (RadioButton) findViewById(R.id.radioTitleAscending);
        RadioButton radioTitleDescending = (RadioButton) findViewById(R.id.radioTitleDescending);

//        Value retrieved for preferred sort field is to determine which RadioButton should be checked

        if (sortTitle.equalsIgnoreCase("ASC")) {
            radioTitleAscending.setChecked(true);
        }
        else {
            radioTitleDescending.setChecked(true);
        }

        RadioButton radioPriorityHigh = (RadioButton) findViewById(R.id.radioHighToLow);
        RadioButton radioPriorityLow = (RadioButton) findViewById(R.id.radioLowToHigh);

        if (sortPriority.equalsIgnoreCase("high")) {
            radioPriorityHigh.setChecked(true);
        }
        else {
            radioPriorityLow.setChecked(true);
        }

        RadioButton radioDateAscending = (RadioButton) findViewById(R.id.radioDateAscending);
        RadioButton radioDateDescending = (RadioButton) findViewById(R.id.radioDateDescending);

        if (sortDateCreated.equalsIgnoreCase("ASC")) {
            radioDateAscending.setChecked(true);
        }
        else {
            radioDateDescending.setChecked(true);
        }

    }

    private void initSortTitleClick()  {

        RadioGroup rgSortTitle = (RadioGroup) findViewById(R.id.radioGroupSortTitle);
        rgSortTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton radioTitleAscending = (RadioButton) findViewById(R.id.radioTitleAscending);
                RadioButton radioTitleDescending = (RadioButton) findViewById(R.id.radioTitleDescending);

                if (radioTitleAscending.isChecked()) {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sorttitle", "ASC").commit();
                }
                else {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sorttitle", "DSC").commit();
                }
            }
        });
    }

    private void initSortPriorityClick()  {

        RadioGroup rgSortPriority = (RadioGroup) findViewById(R.id.radioGroupSortPriority);
        rgSortPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton radioPriorityHighToLow = (RadioButton) findViewById(R.id.radioHighToLow);
                RadioButton radioPriorityLowToHigh = (RadioButton) findViewById(R.id.radioLowToHigh);

                if (radioPriorityHighToLow.isChecked()) {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortpriority", "high").commit();
                }
                else {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortpriority", "low").commit();
                }
            }
        });
    }

    private void initSortDateClick()  {

        RadioGroup rgSortDate = (RadioGroup) findViewById(R.id.RadioGroupSortDate);
        rgSortDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton radioDateAscending = (RadioButton) findViewById(R.id.radioDateAscending);
                RadioButton radioDateDescending = (RadioButton) findViewById(R.id.radioDateDescending);

                if (radioDateAscending.isChecked()) {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortdate", "ASC").commit();
                }
                else {
                    getSharedPreferences("MyNoteListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortdate", "DSC").commit();
                }
            }
        });
    }





}
