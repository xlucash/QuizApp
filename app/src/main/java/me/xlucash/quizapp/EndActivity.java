package me.xlucash.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        resultTextView = findViewById(R.id.result);
        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        int wrongAnswers = getIntent().getIntExtra("wrongAnswers", 0);

        resultTextView.setText(String.format("Correct: %d%nWrong: %d",correctAnswers, wrongAnswers));
    }
}