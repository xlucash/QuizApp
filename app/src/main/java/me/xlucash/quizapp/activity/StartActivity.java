package me.xlucash.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.xlucash.quizapp.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startBtn = (Button) findViewById(R.id.startButton);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startButton:
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
                break;
        }
    }
}