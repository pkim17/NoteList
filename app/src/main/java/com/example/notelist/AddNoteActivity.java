package com.example.notelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private Note currentNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initAddNoteButton();
        initListButton();
        initSettingsButton();
        initSaveButton();
        initTextChangedEvents();
        initPriorityButtons();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            initNote(extras.getInt("noteid"));
        }
        else {
            currentNote = new Note();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initAddNoteButton() {
        ImageButton ibAddNote = (ImageButton) findViewById(R.id.imageButtonAddNote);
        ibAddNote.setEnabled(false);

    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNoteActivity.this, NoteListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNoteActivity.this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSaveButton()   {
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                boolean wasSuccessful = false;
                NoteListDataSource ds = new NoteListDataSource(AddNoteActivity.this);
                try {
                    ds.open();

                    if (currentNote.getNoteId() == -1)    {
                        wasSuccessful = ds.insertNote(currentNote);

                        if (wasSuccessful)  {
                            int newId = ds.getLastNoteId();
                            currentNote.setNoteId(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateNote(currentNote);
                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }

                Intent intent = new Intent(AddNoteActivity.this, NoteListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }

        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        imm.hideSoftInputFromWindow(editTitle.getWindowToken(), 0);
        EditText editTextBody = (EditText) findViewById(R.id.editBodyText);
        imm.hideSoftInputFromWindow(editTextBody.getWindowToken(), 0);

    }

    private void initNote(int id) {

        NoteListDataSource ds = new NoteListDataSource(AddNoteActivity.this);

        try {
            ds.open();
            currentNote = ds.getSpecificNote(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Note Failed", Toast.LENGTH_LONG).show();
        }

        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        EditText editBodyText = (EditText) findViewById(R.id.editBodyText);
        RadioButton rbHigh = (RadioButton) findViewById(R.id.radioButtonHigh);
        RadioButton rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        RadioButton rbLow = (RadioButton) findViewById(R.id.radioButtonLow);

        editTitle.setText(currentNote.getNoteTitle());
        editBodyText.setText(currentNote.getNoteBodyText());

        if (currentNote.getPriority() == 3) {
            rbHigh.setChecked(true);
        } else if (currentNote.getPriority() == 2) {
            rbMedium.setChecked(true);
        } else if (currentNote.getPriority() == 1) {
            rbLow.setChecked(true);
        }
    }

    public void initTextChangedEvents() {

        final EditText etNotetitle = (EditText) findViewById(R.id.editTitle);
        etNotetitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentNote.setNoteTitle(etNotetitle.getText().toString());

            }
        });

        final EditText etNoteBodyText = (EditText) findViewById(R.id.editBodyText);
        etNoteBodyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentNote.setNoteBodyText(etNoteBodyText.getText().toString());

            }
        });
    }

    public void initPriorityButtons() {

        RadioGroup rgPriority = (RadioGroup) findViewById(R.id.RadioGroupPriority);
        rgPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbHigh = (RadioButton) findViewById(R.id.radioButtonHigh);
                RadioButton rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
                RadioButton rbLow = (RadioButton) findViewById(R.id.radioButtonLow);

                if (rbHigh.isChecked()) {
                    currentNote.setPriority(3);
                }
                else if (rbMedium.isChecked()) {
                    currentNote.setPriority(2);
                }
                else {
                    currentNote.setPriority(1);

                }
        }
        });

    }


}
