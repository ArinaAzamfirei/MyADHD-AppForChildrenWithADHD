package com.app.myadhdgame.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;

import com.app.myadhdgame.EndlessRunnerGame.GameActivity;
import com.app.myadhdgame.EndlessRunnerGame.GameEngine;
import com.app.myadhdgame.EndlessRunnerGame.GameOver;
import com.app.myadhdgame.MainActivity;
import com.app.myadhdgame.MiniGames.MatchActivity;
import com.app.myadhdgame.MiniGames.OrientationGameActivity;
import com.app.myadhdgame.DTOS.GameResult;
import com.app.myadhdgame.R;
import com.app.myadhdgame.Retrofit.ApiClient;
import com.app.myadhdgame.Retrofit.RetrofitClient;

import java.time.LocalDateTime;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryActivity extends AppCompatActivity {

    GifImageView gifImageView;
    ImageButton imageButton;
    static MediaPlayer mediaPlayer;
    static long start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        gifImageView = findViewById(R.id.storyImage);
        imageButton = findViewById(R.id.continueGame);
        if(MainActivity.appState== 0){
            new CountDownTimer(34000, 34000) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story1);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio1);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            });

        }
        else if(MainActivity.appState == 2){
            new CountDownTimer(29000, 29000) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story2);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio2);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                start = System.nanoTime();
                Intent intent = new Intent(StoryActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            });
        }
        else if(MainActivity.appState == 3){
            new CountDownTimer(16900, 16900) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story3);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio3);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            });
        }

        else if(MainActivity.appState == 4){
            new CountDownTimer(58200, 58200) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story4);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio4);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            MainActivity.appState = 5;
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, MatchActivity.class);
                startActivity(intent);
                finish();
            });
        }
        else if(MainActivity.appState == 5){
            new CountDownTimer(14300, 14300) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story5);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio5);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, MatchActivity.class);
                startActivity(intent);
                finish();
            });
        }
        else if(MainActivity.appState == 6){
            new CountDownTimer(14300, 14300) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story6);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio6);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            });
        }
        else if(MainActivity.appState == 7){
            new CountDownTimer(32800, 32800) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story7);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio7);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, OrientationGameActivity.class);
                startActivity(intent);
                finish();
            });
        }
        else if(MainActivity.appState == 8){
            new CountDownTimer(32800, 32800) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story8);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio8);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {
                Intent intent = new Intent(StoryActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            });
        }
        else if(MainActivity.appState == 9){
            new CountDownTimer(44700, 44700) {

                public void onTick(long millisUntilFinished) {
                    gifImageView.setBackgroundResource(R.drawable.story9);
                    mediaPlayer = MediaPlayer.create(StoryActivity.this, R.raw.audio9);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

                public void onFinish() {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    mediaPlayer.stop();
                }
            }.start();
            imageButton.setOnClickListener(v -> {

                ApiClient api = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
                GameResult gameResult = new GameResult(String.valueOf(LocalDateTime.now()),
                        String.valueOf(GameEngine.points));
                Call<GameResult> call = api.putGameResult(gameResult, MainActivity.childId);
                call.enqueue(new Callback<GameResult>() {
                    @Override
                    public void onResponse(Call<GameResult> call, Response<GameResult> response) {
                        Log.i("RASPUNS", response.toString());
                    }

                    @Override
                    public void onFailure(Call<GameResult> call, Throwable t) {
                        Log.i("FAILURE", String.valueOf(t));
                    }
                });
                Intent intent = new Intent(StoryActivity.this, GameOver.class);
                startActivity(intent);
                finish();
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onBackPressed();
    }
}