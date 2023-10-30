package com.app.myadhdgame.EndlessRunnerGame;

import com.app.myadhdgame.EndlessRunnerGame.Constants;

public class Player {
    public int pX, pY, pyInitial, pFrame, speed;

    public Player(){
        pX = Constants.SCREEN_WIDTH/3 - Constants.getBitmapBank().getPlayerWidth();
        pyInitial = Constants.SCREEN_HEIGHT - Constants.getBitmapBank().getGroundHeight()
                - Constants.getBitmapBank().getPlayerHeight();
        pY = Constants.SCREEN_HEIGHT - Constants.getBitmapBank().getGroundHeight()
                - Constants.getBitmapBank().getPlayerHeight();
        pFrame = 0;
        speed = 0;
    }

    public int getX() {
        return pX;
    }

    public void setX(int pX) {
        this.pX = pX;
    }

    public int getY() {
        return pY;
    }

    public void setY(int pY) {
        this.pY = pY;
    }

    public int getPyInitial() {
        return pyInitial;
    }

    public void setPyInitial(int pyInitial) {
        this.pyInitial = pyInitial;
    }

    public int getPFrame() {
        return pFrame;
    }

    public void setPFrame(int pFrame) {
        this.pFrame = pFrame;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
