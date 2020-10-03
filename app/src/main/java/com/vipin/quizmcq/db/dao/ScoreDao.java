package com.vipin.quizmcq.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vipin.quizmcq.db.entity.ScoreEntity;

import java.util.List;

@Dao
public interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertScore(ScoreEntity score);

    @Query("SELECT * from score ORDER BY date DESC")
    LiveData<List<ScoreEntity>> getAllScores();

}
