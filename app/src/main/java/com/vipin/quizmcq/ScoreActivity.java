package com.vipin.quizmcq;


import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vipin.quizmcq.adapters.ScoreAdapter;
import com.vipin.quizmcq.db.entity.ScoreEntity;
import com.vipin.quizmcq.viewmodel.ScoreViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScoreActivity extends AppCompatActivity {

    private Context activityContext;
    private Unbinder unbinder;
    private ScoreViewModel scoreViewModel;
    private List<ScoreEntity> scores = new ArrayList<>();
    private ScoreAdapter adpScore;

    @BindView(R.id.recycler_score)
    RecyclerView scoreView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ButterKnife.bind(this);

        activityContext = ScoreActivity.this;
        unbinder = ButterKnife.bind(this);
        setUpToolbar();
        initScoreView();
        initViewModel();
    }

    private void initViewModel() {
        Observer<List<ScoreEntity>> scoreObserver = new Observer<List<ScoreEntity>>() {
            @Override
            public void onChanged(@Nullable List<ScoreEntity> scoreEntities) {
                scores.clear();
                if (scoreEntities != null) {
                    scores.addAll(scoreEntities);
                }
                if (adpScore == null) {
                    adpScore = new ScoreAdapter(scores, activityContext);
                    scoreView.setAdapter(adpScore);
                } else {
                    adpScore.notifyDataSetChanged();
                }
            }
        };
        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);
        scoreViewModel.getScores().observe(this, scoreObserver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            return false;
        }
        return false;
    }


    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        title.setText("Scores");
    }

    private void initScoreView() {
        scoreView.setLayoutManager(new LinearLayoutManager(activityContext));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
