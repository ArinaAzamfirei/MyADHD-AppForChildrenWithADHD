package com.app.myadhdgame.EndlessRunnerGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.app.myadhdgame.R;

public class BitmapBank {

    Bitmap bg, ground, monster1, monster2, stone, mushroom1, mushroom2;
    Bitmap[] player = new Bitmap[9];
    Bitmap[] playerJump = new Bitmap[9];
    Bitmap[] playerDead = new Bitmap[11];
    Bitmap[] story = new Bitmap[10];
    Bitmap tapToStart;

    public BitmapBank(Resources res){
        bg = BitmapFactory.decodeResource(res, R.drawable.bglevel1);
        bg = scaleImage(bg);
        ground = BitmapFactory.decodeResource(res, R.drawable.path);
        monster1 = BitmapFactory.decodeResource(res, R.drawable.monster1);
        monster2 = BitmapFactory.decodeResource(res, R.drawable.monster2);
        stone = BitmapFactory.decodeResource(res, R.drawable.stone);
        mushroom1 = BitmapFactory.decodeResource(res, R.drawable.mushroom_1);
        mushroom2 = BitmapFactory.decodeResource(res, R.drawable.mushroom_2);
        tapToStart = BitmapFactory.decodeResource(res, R.drawable.tap_to_start);

        //player
        player[0] = BitmapFactory.decodeResource(res, R.drawable.run1);
        player[1] = BitmapFactory.decodeResource(res, R.drawable.run2);
        player[2] = BitmapFactory.decodeResource(res, R.drawable.run3);
        player[3] = BitmapFactory.decodeResource(res, R.drawable.run4);
        player[4] = BitmapFactory.decodeResource(res, R.drawable.run5);
        player[5] = BitmapFactory.decodeResource(res, R.drawable.run6);
        player[6] = BitmapFactory.decodeResource(res, R.drawable.run7);
        player[7] = BitmapFactory.decodeResource(res, R.drawable.run8);

        //player when jumping
        playerJump[0] = BitmapFactory.decodeResource(res, R.drawable.jump1);
        playerJump[1] = BitmapFactory.decodeResource(res, R.drawable.jump2);
        playerJump[2] = BitmapFactory.decodeResource(res, R.drawable.jump3);
        playerJump[3] = BitmapFactory.decodeResource(res, R.drawable.jump4);
        playerJump[4] = BitmapFactory.decodeResource(res, R.drawable.jump5);
        playerJump[5] = BitmapFactory.decodeResource(res, R.drawable.jump6);
        playerJump[6] = BitmapFactory.decodeResource(res, R.drawable.jump7);
        playerJump[7] = BitmapFactory.decodeResource(res, R.drawable.jump8);

        //player when dying
        playerDead[0] = BitmapFactory.decodeResource(res, R.drawable.dead1);
        playerDead[1] = BitmapFactory.decodeResource(res, R.drawable.dead2);
        playerDead[2] = BitmapFactory.decodeResource(res, R.drawable.dead3);
        playerDead[3] = BitmapFactory.decodeResource(res, R.drawable.dead4);
        playerDead[4] = BitmapFactory.decodeResource(res, R.drawable.dead5);
        playerDead[5] = BitmapFactory.decodeResource(res, R.drawable.dead6);
        playerDead[6] = BitmapFactory.decodeResource(res, R.drawable.dead7);
        playerDead[7] = BitmapFactory.decodeResource(res, R.drawable.dead8);
        playerDead[8] = BitmapFactory.decodeResource(res, R.drawable.dead9);
        playerDead[9] = BitmapFactory.decodeResource(res, R.drawable.dead10);


    }

    public Bitmap getBg(){
        return bg;
    }

    public int getBgWidth(){
        return bg.getWidth();
    }

    public int getBgHeight(){
        return bg.getHeight();
    }


    public Bitmap getGround() {
        return ground;
    }

    public int getGroundWidth() {
        return ground.getWidth();
    }

    public int getGroundHeight() {
        return ground.getHeight();
    }

    public Bitmap getPlayer(int pFrame) {
        return player[pFrame];
    }

    public int getPlayerWidth() {
        return player[0].getWidth();
    }

    public int getPlayerHeight() {
        return player[0].getHeight();
    }


    public Bitmap getPlayerJump(int pFrame) {
        return playerJump[pFrame];
    }

    public int getPlayerJumpWidth() {
        return playerJump[0].getWidth();
    }

    public int getPlayerJumpHeight() {
        return playerJump[0].getHeight();
    }


    public Bitmap getPlayerDead(int pFrame) {
        return playerDead[pFrame];
    }

    public int getPlayerDeadWidth() {
        return playerDead[0].getWidth();
    }

    public int getPlayerDeadHeight() {
        return playerDead[0].getHeight();
    }

    public Bitmap getMonster1() {
        return monster1;
    }

    public int getMonster1Height(){
        return  monster1.getHeight();
    }

    public int getMonster1Width(){
        return  monster1.getWidth();
    }
    public Bitmap getMonster2() {
        return monster2;
    }
    public int getMonster2Height(){
        return  monster2.getHeight();
    }

    public int getMonster2Width(){
        return  monster2.getWidth();
    }

    public Bitmap getStone() {
        return stone;
    }

    public int getStoneHeight(){
        return stone.getHeight();
    }

    public int getStoneWidth(){
        return stone.getWidth();
    }

    public Bitmap getMushroom1() {
        return mushroom1;
    }

    public int getMushroom1Height(){
        return mushroom1.getHeight();
    }

    public int getMushroom1Width(){
        return mushroom1.getWidth();
    }

    public Bitmap getMushroom2() {
        return mushroom2;
    }
    public int getMushroom2Height(){
        return mushroom2.getHeight();
    }
    public int getMushroom2Width(){
        return mushroom2.getWidth();
    }

    public Bitmap getTapToStart() {
        return tapToStart;
    }

    public int getTapToStartHeight(){
        return tapToStart.getHeight();
    }

    public int getTapToStartWidth(){
        return tapToStart.getWidth();
    }

    public Bitmap scaleImage(Bitmap bitmap){
         /*
        We'll multiply widthHeightRatio with screenHeight to get
        scaled width of the bitmap. Then call createScaledBitmap()
        to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        float widthHeightRatio = getBgWidth() / getBgHeight();
        int bgScaledWidth = (int) (widthHeightRatio * Constants.SCREEN_HEIGHT);
        return Bitmap.createScaledBitmap(bitmap, bgScaledWidth, Constants.SCREEN_HEIGHT, false);
    }
}
