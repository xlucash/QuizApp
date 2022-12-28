package me.xlucash.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import me.xlucash.quizapp.R;

public class EndActivity extends AppCompatActivity {

    private CircularProgressBar circularProgressBar;
    private TextView resultTextView, resultTitle;
    private Button goBackBtn;
    private int correct;
    private int wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressBar);
        resultTextView = (TextView) findViewById(R.id.resultText);
        resultTitle = (TextView) findViewById(R.id.resultTitleText);
        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        correct = getIntent().getIntExtra("correctAnswers", 0);
        wrong = getIntent().getIntExtra("wrongAnswers", 0);


        circularProgressBar.setProgress(correct);
        resultTextView.setText(""+correct+"/10");


    }
}