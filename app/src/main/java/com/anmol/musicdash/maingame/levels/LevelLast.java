package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.Text;

public class LevelLast extends AbstractLevel {
    public LevelLast() {
        super();
        LEVEL_NAME_IN = "Last Level";
        LEVEL_NAME_OUT = "Good by.";
        LEVEL_DESCRIPTION_IN = "Ok, this is the last level";
        LEVEL_DESCRIPTION_OUT = "Good by.\nThere are No more levels left";
        Stars = 55;
        StarsRequirement = 36;
        id = -1;
    }

    @Override
    public void implement(GameView gameView) {
        gameView.addItem(new Text(1000, 3000, "Ok, this is the last Level", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(4000, 3000, "You may seen the 'Need more levels' page", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(7000, 3000, "Well that was fake", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(10000, 3000, "It's impossible to collect All stars", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.5f));

        gameView.addItem(new Text(15000, 3000, "This was My first Android Project.", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(18000, 5000, "If you still want more levels you can create them yourself.", Color.WHITE, 100, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(23000, 15000, "For help (on how to create levels) you can read file:", Color.WHITE, 100, Typeface.DEFAULT, 0.5f, 0.3f));
        gameView.addItem(new Text(23000, 15000, "com/anmol/musicdash/maingame/HowToCreateLevel.md from source code.", Color.WHITE, 100, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(38000, 15000, "Did you like this.", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.25f));

        gameView.addItem(new Text(48000, 5000, "By", Color.WHITE, 144, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new End(55000));
    }
}

