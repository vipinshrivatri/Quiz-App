package com.vipin.quizmcq.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vipin.quizmcq.db.AppRepository;
import com.vipin.quizmcq.db.entity.ScoreEntity;

import java.util.List;


public class ScoreViewModel extends AndroidViewModel {

    private LiveData<List<ScoreEntity>> scores;
    private AppRepository repository;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        repository = AppRepository.getInstance(application.getApplicationContext());
        scores = repository.getAllScores();
    }

    public void addScore(ScoreEntity score) {
        repository.addScore(score);
    }

    public LiveData<List<ScoreEntity>> getScores() {
        scores = repository.getScores();
        return scores;
    }
}
