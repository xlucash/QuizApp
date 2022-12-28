package me.xlucash.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import me.xlucash.quizapp.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    private ImageView quizImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        quizImage = (ImageView) findViewById(R.id.quizLogo);
        quizImage.animate()
                .scaleX(0.02f)
                .scaleY(1.5f)
                .scaleY(0.02f)
                .rotation(360f)
                .setDuration(3000);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}