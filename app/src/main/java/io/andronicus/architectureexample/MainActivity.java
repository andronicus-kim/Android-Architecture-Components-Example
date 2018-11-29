package io.andronicus.architectureexample;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Viewmodel from the android system since it knows when to
        // create a new one when necessary
        mViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mViewModel.getNotes().observe(this,notes -> {
            //Update recyclerview data here
            Toast.makeText(this, "onChanged", Toast.LENGTH_SHORT).show();
        });
    }
}
