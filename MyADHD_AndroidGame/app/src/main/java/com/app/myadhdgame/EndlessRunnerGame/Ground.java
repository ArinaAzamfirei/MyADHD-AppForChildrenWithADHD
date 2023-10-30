package com.app.myadhdgame.EndlessRunnerGame;


public class Ground {
    private int groundX, groundY, groundSpeed;
    public Ground(){
        groundX = 0;
        groundY = Constants.SCREEN_HEIGHT - Constants.getBitmapBank().getGroundHeight();
        groundSpeed = 15;
    }

    public int getGroundX() {
        return groundX;
    }

    public int getGroundY() {
        return groundY;
    }

    public int getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundX(int groundX) {
        this.groundX = groundX;
    }

    public void setGroundY(int groundY) {
        this.groundY = groundY;
    }

    public void setGroundSpeed(int groundSpeed) {
        this.groundSpeed = groundSpeed;
    }
}
