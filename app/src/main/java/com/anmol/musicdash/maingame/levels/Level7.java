package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.maingame.Circle;
import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameSoundPlayer;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.SoundItem;
import com.anmol.musicdash.maingame.Text;

import java.util.Random;

public class Level7 extends AbstractLevel {
    public Level7() {
        super();
        LEVEL_NAME_IN = "Random";
        LEVEL_NAME_OUT = "Code (language='unknown'):";
        LEVEL_DESCRIPTION_IN = "You must study Probabilities.";
        LEVEL_DESCRIPTION_OUT = "     useRandom(new Random(seed=Math.random()));\n     extendLevel(`unexpectedly`);\n     levelType(`long0`);\n     ... ... ... ... ...";
        Stars = 21;
        StarsRequirement = 16;
        id = 7;
    }

    @Override
    public void implement(GameView gameView) {
        long t = 1000;
        Random r = new Random();

        gameView.addItem(new Text(t + 1000, 2000, "/implement(`Random`);", Color.CYAN, 216, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));

        t += 4000;

        for (int i = 0; i < 54; i++) {
            gameView.addItem(new SoundItem(t + 750 * i, f[(i * 2 + 7) % (f.length - 1)], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 750 * i + 125, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.2f + r.nextInt(7) / 7f * 0.8f;
            float y = 0.2f + r.nextInt(7) / 7f * 0.8f;
            gameView.addItem(new Circle(t + 750 * i + 250, 250, 500, x, y, 0.2f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)));

            gameView.addItem(new Text(t + 750 * i + 250, 250, String.valueOf(i), Color.WHITE, 144, Typeface.DEFAULT_BOLD, x, y));
        }

        t += 55 * 750;


        gameView.addItem(new Text(t + 1000, 3000, "Do you KNOW you had NOT clicked 54 times.", Color.WHITE, 128, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 4000, 9000, "Do you counted how may times you  lost also and most important can you read this sentence, if not then why.", Color.WHITE, 32, Typeface.DEFAULT, 0.5f, 0.5f));

        t += 17000;
        gameView.addItem(new End(t));
    }
}

