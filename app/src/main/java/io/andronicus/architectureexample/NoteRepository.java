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
        new UpdateNoteAsyncTask(mNoteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(mNoteDao).execute(note);
    }

    public void deleteAll(){
        new DeleteAllNotesAsyncTask(mNoteDao).execute();
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

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao mNoteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao mNoteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao mNoteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.deleteAll();
            return null;
        }
    }
}
