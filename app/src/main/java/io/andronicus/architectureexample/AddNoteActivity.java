package io.andronicus.architectureexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private NumberPicker mNumberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mEditTextTitle = findViewById(R.id.et_title);
        mEditTextDescription = findViewById(R.id.et_description);
        mNumberPicker = findViewById(R.id.number_picker_priority);

        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }
}
