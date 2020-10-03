package com.vipin.quizmcq.db;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.vipin.quizmcq.db.entity.ScoreEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppRepository {

    private LiveData<List<ScoreEntity>> scores;
    private QuizDatabase quizDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private static AppRepository repoInstance;

    public static AppRepository getInstance(Context context) {
        if (repoInstance == null) {
            repoInstance = new AppRepository(context);
        }
        return repoInstance;
    }

    public LiveData<List<ScoreEntity>> getScores() {
        return scores;
    }

    private AppRepository(Context context) {
        quizDb = QuizDatabase.getInstance(context);
        scores = getAllScores();
    }

    public LiveData<List<ScoreEntity>> getAllScores() {
        return quizDb.scoreDao().getAllScores();
    }

    public void addScore(final ScoreEntity score) {
        executor.execute(() -> quizDb.scoreDao().insertScore(score));
    }
}
