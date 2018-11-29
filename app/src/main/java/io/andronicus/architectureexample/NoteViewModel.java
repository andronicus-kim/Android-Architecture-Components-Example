package io.andronicus.architectureexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<Note>> mNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(Note note){
        mRepository.insert(note);
    }

    public void update(Note note){
        mRepository.update(note);
    }

    public void delete(Note note){
        mRepository.delete(note);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public LiveData<List<Note>> getNotes() {
        return mNotes;
    }
}
