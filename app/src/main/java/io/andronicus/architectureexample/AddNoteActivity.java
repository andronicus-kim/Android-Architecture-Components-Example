package io.andronicus.architectureexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

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

    private void saveNote(){
        String title = mEditTextTitle.getText().toString().trim();
        String description = mEditTextDescription.getText().toString().trim();
        int priority = mNumberPicker.getValue();

        if (title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Please input Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }


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
