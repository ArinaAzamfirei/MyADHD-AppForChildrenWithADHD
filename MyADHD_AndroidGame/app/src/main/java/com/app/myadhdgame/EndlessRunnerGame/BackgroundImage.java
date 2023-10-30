package com.app.myadhdgame.EndlessRunnerGame;


//For managing the scrolling bg
public class BackgroundImage {
    private int bgImgX, bgImgY, bgSpeed;

    public BackgroundImage(){
        bgImgX = 0;
        bgImgY = 0;
        //speed of scrolling
        bgSpeed = 3;
    }

    public int getBgImgX() {
        return bgImgX;
    }

    public int getBgImgY() {
        return bgImgY;
    }

    public int getBgSpeed() {
        return bgSpeed;
    }

    public void setBgImgX(int bgImgX) {
        this.bgImgX = bgImgX;
    }

    public void setBgImgY(int bgImgY) {
        this.bgImgY = bgImgY;
    }

    public void setBgSpeed(int bgSpeed) {
        this.bgSpeed = bgSpeed;
    }
}
