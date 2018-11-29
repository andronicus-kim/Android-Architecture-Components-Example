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

    private static class updateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao mNoteDao;

        private updateNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao mNoteDao;

        private deleteNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }

    private static class deleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao mNoteDao;

        private deleteAllNotesAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.deleteAll();
            return null;
        }
    }
}
