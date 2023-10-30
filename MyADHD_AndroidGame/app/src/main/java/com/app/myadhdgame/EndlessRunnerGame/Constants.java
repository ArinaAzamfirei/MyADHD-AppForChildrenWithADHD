package com.app.myadhdgame.EndlessRunnerGame;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Constants {

    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int SPEED_WHEN_JUMPING;
    static int SPEED_OBSTACLES;
    static Context gameActivityContext;
    static boolean playerGrounded;

    public static void initialization(Context context){
        setScreenSize(context);
        gameActivityContext = context;
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();
    }
    public static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;
    }

    public static void setGameConstants(){
        gravity = 3;
        SPEED_OBSTACLES = 45;
        SPEED_WHEN_JUMPING = -40;
        playerGrounded = true;
    }

    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }
}
