package me.xlucash.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import me.xlucash.quizapp.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout javaLinearLayout;
    private LinearLayout phpLinearLayout;
    private LinearLayout csharpLinearLayout;
    private LinearLayout pythonLinearLayout;
    private Button startBtn;
    private String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        javaLinearLayout = (LinearLayout) findViewById(R.id.javaLayout);
        javaLinearLayout.setOnClickListener(this);
        phpLinearLayout = (LinearLayout) findViewById(R.id.phpLayout);
        phpLinearLayout.setOnClickListener(this);
        csharpLinearLayout = (LinearLayout) findViewById(R.id.csharpLayout);
        csharpLinearLayout.setOnClickListener(this);
        pythonLinearLayout = (LinearLayout) findViewById(R.id.pythonLayout);
        pythonLinearLayout.setOnClickListener(this);
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.javaLayout:
                selectedTopic = "java";
                javaLinearLayout.setBackgroundResource(R.drawable.round_back_gray_creamy);
                phpLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                csharpLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                pythonLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                break;
            case R.id.phpLayout:
                selectedTopic = "php";
                phpLinearLayout.setBackgroundResource(R.drawable.round_back_gray_creamy);
                javaLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                csharpLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                pythonLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                break;
            case R.id.csharpLayout:
                selectedTopic = "csharp";
                csharpLinearLayout.setBackgroundResource(R.drawable.round_back_gray_creamy);
                phpLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                javaLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                pythonLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                break;
            case R.id.pythonLayout:
                selectedTopic = "python";
                pythonLinearLayout.setBackgroundResource(R.drawable.round_back_gray_creamy);
                phpLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                csharpLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                javaLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
                break;
            case R.id.startBtn:
                if(selectedTopic.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must select the topic!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(StartActivity.this, QuizActivity.class);
                    intent.putExtra("quizTopic", selectedTopic);
                    startActivity(intent);
                    finish();
                }
                break;
        }

    }
}