package io.andronicus.architectureexample;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.andronicus.simplifiedrecyclerview.MyAdapter;

public class MainActivity extends AppCompatActivity implements MyAdapter.ViewHolderCallbacks<Note>{

    private NoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNote = findViewById(R.id.fab_add_note);
        addNote.setOnClickListener(view -> {
            Intent intent = new Intent(this,AddNoteActivity.class);
            startActivityForResult(intent,100);
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter<Note> adapter = new MyAdapter<>(R.layout.note_item,new ArrayList<>(),this);

        //get Viewmodel from the android system since it knows when to
        // create a new one when necessary
        mViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mViewModel.getNotes().observe(this,notes -> {
            //Update recyclerview data here
            adapter.setData(notes);
            recyclerView.setAdapter(adapter);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mViewModel.delete(adapter.getItemAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1);

            Note note = new Note(title,description,priority);
            mViewModel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all_notes){
            saveNote();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
