package com.vipin.quizmcq.adapters;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vipin.quizmcq.R;
import com.vipin.quizmcq.db.entity.ScoreEntity;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    List<ScoreEntity> scores;
    private Context context;

    public ScoreAdapter(List<ScoreEntity> scores, Context context) {
        this.scores = scores;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View scoreItemView = inflater.inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(scoreItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder scoreViewHolder, int i) {
        ScoreEntity score = scores.get(i);
        scoreViewHolder.date.setText(
                dateToString(score.getDate()));
        scoreViewHolder.category.setText(score.getCategory());
        scoreViewHolder.score.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView date;
        @BindView(R.id.txt_category)
        TextView category;
        @BindView(R.id.txt_score)
        TextView score;

        public ScoreViewHolder(@NonNull View scoreItemView) {
            super(scoreItemView);
            ButterKnife.bind(this, scoreItemView);
        }
    }

    private String dateToString(Date date) {
        String result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            result = dateFormat.format(date);
        } else {
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd MMMM yyyy");
            result = dateFormat.format(date);
        }
        return result;
    }
}
