package io.andronicus.architectureexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "io.andronicus.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "io.andronicus.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION= "io.andronicus.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "io.andronicus.architectureexample.EXTRA_PRIORITY";

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

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            mEditTextTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
            mEditTextDescription.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
            mNumberPicker.setValue(getIntent().getIntExtra(EXTRA_PRIORITY,1));
        }else {
            setTitle("Add Note");
        }
    }

    private void saveNote(){
        String title = mEditTextTitle.getText().toString().trim();
        String description = mEditTextDescription.getText().toString().trim();
        int priority = mNumberPicker.getValue();

        if (title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Please input Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,title);
        data.putExtra(EXTRA_PRIORITY,priority);


        // This confirms that data was successfully entered and saved
        setResult(RESULT_OK,data);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note){
            saveNote();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
