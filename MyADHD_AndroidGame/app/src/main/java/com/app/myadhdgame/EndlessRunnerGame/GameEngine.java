package com.app.myadhdgame.EndlessRunnerGame;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;

import com.app.myadhdgame.MainActivity;
import com.app.myadhdgame.MiniGames.MemGame1;
import com.app.myadhdgame.R;

import java.util.ArrayList;
import java.util.Random;


public class GameEngine {

    BackgroundImage backgroundImage;
    Ground ground;
    Player player;
    DeadPlayer playerDead;
    Random random;
    int score;
    Paint scorePaint;
    int pFrame, pJFrame, pDFrame;
    public static int gameState;
    ArrayList<Obstacles> obstaclesList;
    Obstacles obstacles;
    Obstacles obstacles1;
    Obstacles obstacles2;
    Obstacles obstacles3;
    Obstacles obstacles4;
    Obstacles obstacles5;
    Bitmap obs;
    boolean obsSpawned;
    public static int points;
    final int TEXT_SIZE = 80;
    boolean collision = false;
    MediaPlayer hit;
    boolean hitSound;

    public GameEngine(){
        backgroundImage = new BackgroundImage();
        ground = new Ground();
        player = new Player();
        playerDead = new DeadPlayer();
        gameState = 0;// 0 = Not started 1 = Playing 2 = GameOver
        obsSpawned = false;
        pFrame = 0;
        pJFrame = 0;
        pDFrame=0;
        random = new Random();
        score = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);

        obstaclesList = new ArrayList<>();
        obstacles = new Obstacles("");
        obstacles1 = new Obstacles("monster1");
        obstacles2 = new Obstacles("monster2");
        obstacles3 = new Obstacles("stone");
        obstacles4 = new Obstacles("mushroom1");
        obstacles5 = new Obstacles("mushroom2");
        obstaclesList.add(obstacles1);
        obstaclesList.add(obstacles2);
        obstaclesList.add(obstacles3);
        obstaclesList.add(obstacles4);
        obstaclesList.add(obstacles5);
        points = 0;
        hitSound = false;
        hit = MediaPlayer.create(Constants.gameActivityContext, R.raw.hit);
    }

    public void updateAndDrawBackgroundImage(Canvas canvas){
        if(!collision){
            backgroundImage.setBgImgX(backgroundImage.getBgImgX() -
                    backgroundImage.getBgSpeed());
            if(backgroundImage.getBgImgX() < Constants.getBitmapBank().getBgWidth()){
                backgroundImage.setBgImgX(0);
            }
        }
        canvas.drawBitmap(Constants.getBitmapBank().getBg(),backgroundImage.getBgImgX(),
                backgroundImage.getBgImgY(), null);

        if(backgroundImage.getBgImgX() < -(Constants.getBitmapBank().getBgWidth() - Constants.SCREEN_WIDTH)){
            canvas.drawBitmap(Constants.getBitmapBank().getBg(), backgroundImage.getBgImgX() +
                    Constants.getBitmapBank().getBgWidth(), backgroundImage.getBgImgY(), null);
        }
    }

    public void updateAndDrawGround(Canvas canvas){
        if(!collision){
            ground.setGroundX(ground.getGroundX() - ground.getGroundSpeed());
            if(ground.getGroundX() < - Constants.getBitmapBank().getGroundWidth()){
                ground.setGroundX(0);
            }
        }
        canvas.drawBitmap(Constants.getBitmapBank().getGround(), ground.getGroundX(), ground.getGroundY(), null);
        if(ground.getGroundX() < -(Constants.getBitmapBank().getGroundWidth() - Constants.SCREEN_WIDTH)){
            canvas.drawBitmap(Constants.getBitmapBank().getGround(), ground.getGroundX() + Constants.getBitmapBank().getGroundWidth()
                    , ground.getGroundY(), null);
        }
    }

    public void updateAndDrawPlayer(Canvas canvas){



                if(gameState == 1){

                    if(!collision && !Constants.playerGrounded){
                        player.setSpeed(player.getSpeed() + Constants.gravity);
                        //falling down on the ground after jumping
                        player.setY(player.getY() + player.getSpeed());
                        canvas.drawBitmap(Constants.getBitmapBank().getPlayerJump(pJFrame), player.getX(), player.getY(),null);
                        pJFrame ++;
                        if(pJFrame > 7){
                            pJFrame = 0;
                        }
                        if(player.getY() >= player.pyInitial){
                            player.setY(player.pyInitial);
                            Constants.playerGrounded = true;
                        }
                    }//hitting an obstacle when jumping
                    else if(collision && !Constants.playerGrounded){
                        playerDead.setSpeed(playerDead.getSpeed() + Constants.gravity);
                        playerDead.setY(playerDead.getY() + playerDead.getSpeed());
                        canvas.drawBitmap(Constants.getBitmapBank().getPlayerDead(pDFrame), playerDead.getX(), playerDead.getY(), null);
                        pDFrame ++;
                        //dead animation done
                        if(pDFrame ==  10){
                            GameEngine.gameState = 2;
                            MainActivity.appState = 2;
                            Context context = Constants.gameActivityContext;
                            Intent intent = new Intent(context, MemGame1.class);
                            context.startActivity(intent);
                            ((Activity)context).finish();
                        }
                        if(playerDead.getY() >= playerDead.pyInitial){
                            playerDead.setY(playerDead.pyInitial);
                            Constants.playerGrounded = true;
                        }
                        if(!hitSound){
                            hit.start();
                            hitSound = true;
                        }
                    }else if (!collision && Constants.playerGrounded ){
                        canvas.drawBitmap(Constants.getBitmapBank().getPlayer(pFrame),
                                player.getX(), player.getY(), null);
                        pFrame++;
                        if(pFrame > 7){
                            pFrame = 0;
                        }

                    }
                    else if(collision && Constants.playerGrounded){
                        canvas.drawBitmap(Constants.getBitmapBank().getPlayerDead(pDFrame), playerDead.getX(), playerDead.getY(), null);
                        pDFrame ++;
                        //when animation is over we set gameState to 2 (gameOver)
                        if(pDFrame == 9){
                            GameEngine.gameState = 2;
                            MainActivity.appState = 2;
                            Context context = Constants.gameActivityContext;
                            Intent intent = new Intent(context, MemGame1.class);
                            intent.putExtra("points", points);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                        if(hitSound == false) {
                            hit.start();
                            hitSound = true;
                        }
                    }
                    if (obstacles.oX <= player.pX + Constants.getBitmapBank().getPlayerWidth()
                            && obstacles.oX + obstacles.width >= player.pX
                            && obstacles.oY >= player.pY
                            && obstacles.oY <= player.pY + Constants.getBitmapBank().getPlayerHeight()) {
                        collision = true;
                        obstacles.reset();
                    }
                    canvas.drawText("Pt: " + points, 0, TEXT_SIZE, scorePaint);
                }

    }

    public void updateAndDrawObstacles(Canvas canvas){
        if (gameState == 1) {
            if (!obsSpawned) {
                int randIndex = random.nextInt(5);
                obstacles = obstaclesList.get(randIndex);
                obsSpawned = true;
            }
            if (!collision) {
                obstacles.oX -= obstacles.speed;
                if (obstacles.type.equalsIgnoreCase("monster1")) {
                    obs = Constants.getBitmapBank().getMonster1();
                }
                if (obstacles.type.equalsIgnoreCase("monster2")) {
                    obs = Constants.getBitmapBank().getMonster2();
                }
                if (obstacles.type.equalsIgnoreCase("stone")) {
                    obs = Constants.getBitmapBank().getStone();
                }
                if (obstacles.type.equalsIgnoreCase("mushroom1")) {
                    obs = Constants.getBitmapBank().getMushroom1();
                }
                if (obstacles.type.equalsIgnoreCase("mushroom2")) {
                    obs = Constants.getBitmapBank().getMushroom2();
                }
                canvas.drawBitmap(obs, obstacles.oX, obstacles.oY, null);
                if (obstacles.oX <= -Constants.getBitmapBank().getMonster1Width()) {
                    obstacles.reset();
                    points++;
                    obsSpawned = false;
                }
            }
        }

    }
    public void tapToStart(Canvas canvas){
        if(gameState == 0){
            canvas.drawBitmap(Constants.getBitmapBank().getTapToStart(),
                    Constants.SCREEN_WIDTH/2 - Constants.getBitmapBank().getTapToStartWidth()/2,
                    Constants.SCREEN_HEIGHT/2 - Constants.getBitmapBank().getTapToStartHeight()/2, null);
        }
    }

}
