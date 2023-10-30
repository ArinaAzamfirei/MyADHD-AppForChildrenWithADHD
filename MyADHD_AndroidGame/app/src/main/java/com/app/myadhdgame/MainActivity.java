package com.app.myadhdgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.myadhdgame.DTOS.Child;
import com.app.myadhdgame.EndlessRunnerGame.Constants;
import com.app.myadhdgame.Quiz.StoryActivity;
import com.app.myadhdgame.Retrofit.ApiClient;
import com.app.myadhdgame.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mediaPlayer;
    ImageButton startGameBtn;
    public static int childId;
    public static int appState;
    EditText code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appState = 0;

        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        Constants.initialization(this.getApplicationContext());
        startGameBtn = findViewById(R.id.ibStartGame);
        code = findViewById(R.id.childCode);
        ApiClient api = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
        startGameBtn.setOnClickListener(view -> {
            Call<Child> call = api.getChildByCode(code.getText().toString());
            call.enqueue(new Callback<Child>() {
                @Override
                public void onResponse(@NonNull Call<Child> call, @NonNull Response<Child> response) {
                    assert response.body() != null;
                    Log.i("Raspuns: ", String.valueOf(response));
                    childId = response.body().getId();
                    mediaPlayer.stop();
                    Toast.makeText(MainActivity.this, "Va începe jocul!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, StoryActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(@NonNull Call<Child> call, Throwable t) {
                    Log.i("FAILURE", t.toString());
                    Toast.makeText(MainActivity.this, "Nu ați introdus codul corect!",Toast.LENGTH_LONG).show();
                }
            });

        });

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