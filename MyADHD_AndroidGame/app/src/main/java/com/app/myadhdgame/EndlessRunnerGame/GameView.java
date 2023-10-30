package com.app.myadhdgame.EndlessRunnerGame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.app.myadhdgame.R;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;
    MediaPlayer mediaPlayer;

    public GameView(Context context){
        super(context);
        mediaPlayer = MediaPlayer.create(context, R.raw.jump);
        initView();
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if(! gameThread.isRunning()){
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        }
        else{
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if( gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry){
                try {
                    gameThread.join();
                    retry = false;
                }
                catch (InterruptedException e){

                }
            }
        }
    }

    public void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            if(GameEngine.gameState == 0){
                GameEngine.gameState = 1;
            }
            if(Constants.playerGrounded) {
                Constants.getGameEngine().player.setSpeed(Constants.SPEED_WHEN_JUMPING);
                Constants.playerGrounded = false;
            }
            mediaPlayer.start();
        }
        return true;
    }
}
