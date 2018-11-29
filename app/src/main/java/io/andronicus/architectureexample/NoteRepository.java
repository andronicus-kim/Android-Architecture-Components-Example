package io.andronicus.architectureexample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mNotes;

    public NoteRepository(Application application){
        mNoteDao = NoteDatabase.getInstance(application).sNoteDao();
        mNotes = mNoteDao.getAll();
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(mNoteDao).execute(note);
    }

    public void update(Note note){

    }

    public void delete(Note note){

    }

    public void deleteAll(){

    }

    public LiveData<List<Note>> getNotes() {
        return mNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao mNoteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }
}
