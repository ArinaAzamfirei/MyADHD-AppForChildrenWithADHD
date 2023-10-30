package com.app.myadhdgame.EndlessRunnerGame;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.myadhdgame.MainActivity;

public class GameActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.gameActivityContext = this;
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        gameView = new GameView(this);
        layout.addView(gameView);
        setContentView(layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(MainActivity.mediaPlayer != null){
            MainActivity.mediaPlayer.stop();
            MainActivity.mediaPlayer.release();
            MainActivity.mediaPlayer = null;
        }
    }
}
