package com.vipin.quizmcq.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vipin.quizmcq.db.converter.DateConverter;
import com.vipin.quizmcq.db.dao.ScoreDao;
import com.vipin.quizmcq.db.entity.ScoreEntity;


@Database(entities = {ScoreEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class QuizDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "QuizDatabase.db";

    private static volatile QuizDatabase quizDbInstance;
    private static final Object LOCK = new Object();

    public abstract ScoreDao scoreDao();

    public static QuizDatabase getInstance(Context context) {
        if (quizDbInstance == null) {
            synchronized (LOCK) {
                if (quizDbInstance == null) {
                    quizDbInstance = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return quizDbInstance;
    }
}
