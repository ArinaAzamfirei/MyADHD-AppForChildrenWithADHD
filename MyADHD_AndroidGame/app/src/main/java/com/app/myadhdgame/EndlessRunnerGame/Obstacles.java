package com.app.myadhdgame.EndlessRunnerGame;

import com.app.myadhdgame.EndlessRunnerGame.Constants;

import java.util.Random;

public class Obstacles {

    public int oX, oY;
    public String type;
    public int speed;
    public int width;
    Random random;

    public Obstacles(String type) {
        this.type = type;
        this.oX = Constants.SCREEN_WIDTH + 1000;
        this.oY = Constants.SCREEN_HEIGHT - Constants.getBitmapBank().getGroundHeight();
        random = new Random();
        if(type.equalsIgnoreCase("monster1")){
            oY -= Constants.getBitmapBank().getMonster1Height();
            this.speed = Constants.SPEED_OBSTACLES + 14 + random.nextInt(5);// 14-18
            width = Constants.getBitmapBank().getMonster1Width();

        }else if(type.equalsIgnoreCase("monster2")){
            oY -= Constants.getBitmapBank().getMonster2Height();
            this.speed = Constants.SPEED_OBSTACLES + 14 + random.nextInt(5);// 14-18
            width = Constants.getBitmapBank().getMonster2Width();
        }else if(type.equalsIgnoreCase("stone")){
            oY -= Constants.getBitmapBank().getStoneHeight();
            this.speed = Constants.SPEED_OBSTACLES + 11 + random.nextInt(5); // 11-15
            width = Constants.getBitmapBank().getStoneWidth();
        }else if(type.equalsIgnoreCase("mushroom1")){
            oY -= Constants.getBitmapBank().getMushroom1Height();
            this.speed = Constants.SPEED_OBSTACLES + 10 + random.nextInt(6); // 10-15
            width = Constants.getBitmapBank().getMushroom1Width();
        }else if(type.equalsIgnoreCase("mushroom2")){
            oY -= Constants.getBitmapBank().getMushroom2Height();
            this.speed = Constants.SPEED_OBSTACLES + 10 + random.nextInt(6); // 10-15
            width = Constants.getBitmapBank().getMushroom2Width();
        }
    }

    public void reset(){
        oX = Constants.SCREEN_WIDTH + 300;
        if(type.equalsIgnoreCase("monster1")){
            this.speed = Constants.SPEED_OBSTACLES + 14 + random.nextInt(5);// 14-18
        }else if(type.equalsIgnoreCase("monster2")){
            this.speed= Constants.SPEED_OBSTACLES  + 12 + random.nextInt(5); // 12-16
        }else if(type.equalsIgnoreCase("stone")){
            this.speed = Constants.SPEED_OBSTACLES  + 11 + random.nextInt(5); // 11-15
        }else if(type.equalsIgnoreCase("mushroom1")){
            this.speed = Constants.SPEED_OBSTACLES  + 10 + random.nextInt(6); // 10-15
        }else if(type.equalsIgnoreCase("mushroom2")){
            this.speed = Constants.SPEED_OBSTACLES + 15 + random.nextInt(11); // 15-25
        }
    }

}
