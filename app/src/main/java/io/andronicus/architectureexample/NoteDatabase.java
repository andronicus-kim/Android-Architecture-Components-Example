package io.andronicus.architectureexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao sNoteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDatabase.class,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback sCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private NoteDao mNoteDao;

        private PopulateDbAsyncTask(NoteDatabase database){
            mNoteDao = database.sNoteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.insert(new Note("Title 1","Descrition 1",1));
            mNoteDao.insert(new Note("Title 2","Descrition 2",2));
            mNoteDao.insert(new Note("Title 3","Descrition 3",3));
            return null;
        }
    }
}
