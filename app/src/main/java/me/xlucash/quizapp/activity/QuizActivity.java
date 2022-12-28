package me.xlucash.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.xlucash.quizapp.R;
import me.xlucash.quizapp.model.Question;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    Button answerOneBtn, answerTwoBtn, answerThreeBtn, answerFourBtn, goNextBtn;
    List<Question> questionList;
    int currentQuestion = 0;
    int correctAnswers = 0;
    int wrongAnswers = 0;
    CountDownTimer countDownTimer;
    int timeValueInSeconds = 30;
    private ProgressBar timerProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = (TextView) findViewById(R.id.questionText);

        answerOneBtn = (Button) findViewById(R.id.answer1Button);
        answerOneBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerOneBtn.setOnClickListener(this);

        answerTwoBtn = (Button) findViewById(R.id.answer2Button);
        answerTwoBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerTwoBtn.setOnClickListener(this);

        answerThreeBtn = (Button) findViewById(R.id.answer3Button);
        answerThreeBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerThreeBtn.setOnClickListener(this);

        answerFourBtn = (Button) findViewById(R.id.answer4Button);
        answerFourBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerFourBtn.setOnClickListener(this);

        goNextBtn = (Button) findViewById(R.id.nextBtn);
        goNextBtn.setOnClickListener(this);

        timerProgressBar = (ProgressBar) findViewById(R.id.timer);
        timerProgressBar.setMax(30);
        timerProgressBar.setMin(0);

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timeValueInSeconds-=1;
                timerProgressBar.setProgress(timeValueInSeconds);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(QuizActivity.this, R.style.Dialoge);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.tryAgain_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(QuizActivity.this, StartActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialog.show();
            }
        }.start();

        loadAllQuestions();
        Collections.shuffle(questionList);
        setQuestionOnScreen(currentQuestion);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.answer1Button:
                if(questionList.get(currentQuestion).getAnswerOne().equals(questionList.get(currentQuestion).getAnswerCorrect())) {
                    pickCorrectAnswer();
                    setRightColor(answerOneBtn);
                } else {
                    pickWrongAnswer();
                    setWrongColor(answerOneBtn);
                }
                break;
            case R.id.answer2Button:
                if(questionList.get(currentQuestion).getAnswerTwo().equals(questionList.get(currentQuestion).getAnswerCorrect())) {
                    pickCorrectAnswer();
                    setRightColor(answerTwoBtn);
                } else {
                    pickWrongAnswer();
                    setWrongColor(answerTwoBtn);
                }
                break;
            case R.id.answer3Button:
                if(questionList.get(currentQuestion).getAnswerThree().equals(questionList.get(currentQuestion).getAnswerCorrect())) {
                    pickCorrectAnswer();
                    setRightColor(answerThreeBtn);
                } else {
                    pickWrongAnswer();
                    setWrongColor(answerThreeBtn);
                }
                break;
            case R.id.answer4Button:
                if(questionList.get(currentQuestion).getAnswerFour().equals(questionList.get(currentQuestion).getAnswerCorrect())) {
                    pickCorrectAnswer();
                    setRightColor(answerFourBtn);

                } else {
                    pickWrongAnswer();
                    setWrongColor(answerFourBtn);
                }
                break;
            case R.id.nextBtn:
                if(currentQuestion < questionList.size()-1) {
                    goToNextQuestion();
                } else {
                    goToEndScreen();
                }
                break;
        }
    }

    private void goToEndScreen() {
        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
        intent.putExtra("correctAnswers", correctAnswers);
        intent.putExtra("wrongAnswers",wrongAnswers);
        startActivity(intent);
        finish();
    }

    private void goToNextQuestion() {
        currentQuestion++;
        resetColor();
        setQuestionOnScreen(currentQuestion);
        timeValueInSeconds=31;
        countDownTimer.start();
    }

    private void pickWrongAnswer() {
        wrongAnswers++;
        Toast.makeText(QuizActivity.this, "Wrong! Correct answer: "+questionList.get(currentQuestion).getAnswerCorrect(), Toast.LENGTH_LONG).show();
        countDownTimer.cancel();
    }

    private void pickCorrectAnswer() {
        correctAnswers++;
        Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_LONG).show();
        countDownTimer.cancel();
    }

    public void setRightColor(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.green));
    }

    public void setWrongColor(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.red));
    }

    public void resetColor() {
        answerOneBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerTwoBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerThreeBtn.setBackgroundColor(getResources().getColor(R.color.white));
        answerFourBtn.setBackgroundColor(getResources().getColor(R.color.white));
    }

    void setQuestionOnScreen(int number) {
        questionTextView.setText(questionList.get(number).getQuestion());
        answerOneBtn.setText(questionList.get(number).getAnswerOne());
        answerTwoBtn.setText(questionList.get(number).getAnswerTwo());
        answerThreeBtn.setText(questionList.get(number).getAnswerThree());
        answerFourBtn.setText(questionList.get(number).getAnswerFour());
    }

    private void loadAllQuestions() {
        questionList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset(this));
            JSONArray questions = (JSONArray) jsonObject.getJSONArray("questions");
            for(int i=0; i<questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);

                String questionString = question.getString("question");
                String anwserOneString = question.getString("answer1");
                String answerTwoString = question.getString("answer2");
                String answerThreeString = question.getString("answer3");
                String answerFourString = question.getString("answer4");
                String answerCorrectString = question.getString("correct");

                questionList.add(new Question(questionString, anwserOneString, answerTwoString, answerThreeString, answerFourString, answerCorrectString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}