package io.andronicus.architectureexample;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.andronicus.simplifiedrecyclerview.MyAdapter;

public class MainActivity extends AppCompatActivity implements MyAdapter.ViewHolderCallbacks<Note>{

    private NoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get Viewmodel from the android system since it knows when to
        // create a new one when necessary
        mViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mViewModel.getNotes().observe(this,notes -> {
            //Update recyclerview data here
            recyclerView.setAdapter(new MyAdapter(R.layout.note_item,notes,this));
        });
    }

    @Override
    public void onViewHolderClick(Note note, int position) {

    }

    @Override
    public void bindDataToViews(Note note, View view) {

        TextView priority = view.findViewById(R.id.tv_priority);
        TextView title = view.findViewById(R.id.tv_title);
        TextView desc = view.findViewById(R.id.tv_description);

        priority.setText(String.valueOf(note.getPriority()));
        title.setText(note.getTitle());
        desc.setText(note.getDescription());
    }
}
