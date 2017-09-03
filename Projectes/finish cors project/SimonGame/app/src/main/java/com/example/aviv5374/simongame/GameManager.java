package com.example.aviv5374.simongame;


import android.media.MediaPlayer;
import android.widget.Button;

import junit.framework.Test;

public class GameManager {
    private static final GameManager ourInstance = new GameManager();

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
    }

    public void Test(Button button, boolean state){
        button.setPressed(state);
    }
}
