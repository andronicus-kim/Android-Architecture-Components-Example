package io.andronicus.architectureexample;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Viewmodel from the android system since it knows when to
        // create a new one when necessary
        mViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    }
}
