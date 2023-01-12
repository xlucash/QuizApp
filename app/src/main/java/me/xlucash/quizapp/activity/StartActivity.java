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
                selectedTopicSetBackground("java", javaLinearLayout, phpLinearLayout, csharpLinearLayout, pythonLinearLayout);
                break;
            case R.id.phpLayout:
                selectedTopicSetBackground("php", phpLinearLayout, javaLinearLayout, csharpLinearLayout, pythonLinearLayout);
                break;
            case R.id.csharpLayout:
                selectedTopicSetBackground("csharp", csharpLinearLayout, phpLinearLayout, javaLinearLayout, pythonLinearLayout);
                break;
            case R.id.pythonLayout:
                selectedTopicSetBackground("python", pythonLinearLayout, phpLinearLayout, csharpLinearLayout, javaLinearLayout);
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

    private void selectedTopicSetBackground(String topic, LinearLayout selectedLinearLayout, LinearLayout secondLinearLayout, LinearLayout thirdLinearLayout, LinearLayout fourthLinearLayout) {
        selectedTopic = topic;
        selectedLinearLayout.setBackgroundResource(R.drawable.round_back_gray_creamy);
        secondLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
        thirdLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
        fourthLinearLayout.setBackgroundResource(R.drawable.round_back_creamy);
    }
}