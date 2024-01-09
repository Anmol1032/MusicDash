package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.maingame.Circle;
import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameSoundPlayer;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.Text;

public class Level8 extends AbstractLevel {
    public Level8() {
        super();
        LEVEL_NAME_IN = "You Again!";
        LEVEL_NAME_OUT = "Oh! you are not that.";
        LEVEL_DESCRIPTION_IN = "Why did you come here.";
        LEVEL_DESCRIPTION_OUT = "I was thinking that you are...Wait why I an talking with you.";
        Stars = 34;
        StarsRequirement = 25;
        id = 8;
    }

    @Override
    public void implement(GameView gameView) {
        long t = 1000;

        gameView.addItem(new Text(t, 4000, "Why did you come here.", Color.WHITE, 248, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 4000, 4000, "I will not let you win.", Color.WHITE, 248, Typeface.DEFAULT, 0.5f, 0.5f));
        t += 10000;

        gameView.addItem(new Circle(t, 1000, 500, 0.25f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 500, 1000, 0.75f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 500, 500, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));

        t += 2000;

        gameView.addItem(new Circle(t, 500, 1000, 0.25f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 500, 1000, 0.75f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 1000, 500, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));

        t += 2000;

        gameView.addItem(new Circle(t, 1000, 500, 0.25f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 500, 1000, 0.75f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 1000, 500, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));

        t += 2000;

        gameView.addItem(new Circle(t, 1000, 1000, 0.25f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 1000, 1000, 0.75f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 1000, 1000, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));

        t += 2000;

        gameView.addItem(new Circle(t, 1000, 1000, 0.25f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 1000, 1000, 0.75f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Circle(t, 500, 1000, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));

        t += 6000;
        gameView.addItem(new End(t));
    }
}

