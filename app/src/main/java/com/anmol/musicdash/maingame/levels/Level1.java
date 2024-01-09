package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.maingame.AnimatePosition;
import com.anmol.musicdash.maingame.BgColor;
import com.anmol.musicdash.maingame.Circle;
import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameSoundPlayer;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.SoundItem;
import com.anmol.musicdash.maingame.Text;

import java.util.Random;

public class Level1 extends AbstractLevel {
    public Level1() {
        super();
        LEVEL_NAME_IN = "The First Level";
        LEVEL_NAME_OUT = "The First Completed Level";
        LEVEL_DESCRIPTION_IN = "Play it now!";
        LEVEL_DESCRIPTION_OUT = "Go to second Level -->";
        Stars = 1;
        id = 1;
    }

    @Override
    public void implement(GameView gameView) {
        long t = 0;
        Random r = new Random(1002);

        gameView.addItem(new BgColor(0x88ffdd, 1000, 1000));
        gameView.addItem(new BgColor(0x225545, 2000, 1000));
        gameView.addItem(new BgColor(0x000000, 3000, 10000));

        t += 3000;

        for (int i = 0; i < 13; i++) {
            gameView.addItem(new AnimatePosition(
                    new Circle(t + i * 1500, 500, 999, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound(f[i + 9], 0.5f, 300)),
                    1 - r.nextFloat() * 2, 1 - r.nextFloat() * 2, 0, 0, 200
            ));
        }

        t += 13 * 1500 + 1000;


        gameView.addItem(new AnimatePosition(new Text(t, 2000, "Is it Easy?", Color.MAGENTA, 288, Typeface.DEFAULT_BOLD, 0.5f, 0.5f),
                0, -1, 0, 0, 1000
        ) {
            @Override
            protected float lerp(float a, float b, float t) {
                return super.lerp(a, b, easeBounce(t));
            }
        });

        t += 3000;

        for (int i = 0; i < 18; i++) {
            gameView.addItem(new SoundItem(t + 1000 * i, f[i * 2 + 7], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 1000 * i + 250, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.3f + r.nextInt(4) / 4f * 0.7f;
            float y = 0.3f + r.nextInt(4) / 4f * 0.7f;
            float x2 = r.nextInt(4) / 4f * 0.5f - 0.25f;
            float y2 = r.nextInt(4) / 4f * 0.5f - 0.25f;
            gameView.addItem(new AnimatePosition(new Circle(t + 1000 * i + 500, 500, 500, x, y, 0.3f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)),
                    x2, y2, 0, 0, 100
            ));

            gameView.addItem(new Text(t + 1000 * i + 500, 500, String.valueOf(i), Color.WHITE, 144, Typeface.DEFAULT_BOLD, x, y));
        }

        t += 19 * 1000;

        gameView.addItem(new AnimatePosition(new Text(t, 2000, "Is it Still Easy?", Color.CYAN, 216, Typeface.DEFAULT_BOLD, 0.5f, 0.5f),
                0, -1, 0, 0, 1000
        ) {
            @Override
            protected float lerp(float a, float b, float t) {
                return super.lerp(a, b, easeBounce(t));
            }
        });

        t += 3000;

        for (int i = 0; i < 18; i++) {
            gameView.addItem(new SoundItem(t + 750 * i, f[i * 2 + 7], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 750 * i + 125, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.2f + r.nextInt(7) / 7f * 0.8f;
            float y = 0.2f + r.nextInt(7) / 7f * 0.8f;
            gameView.addItem(new Circle(t + 750 * i + 250, 250, 500, x, y, 0.2f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)));

            gameView.addItem(new Text(t + 750 * i + 250, 250, String.valueOf(i), Color.WHITE, 144, Typeface.DEFAULT_BOLD, x, y));
        }

        t += 19 * 750;


        gameView.addItem(new Text(t, 2000, "Yes, It was Easy.", 0xff12ffdf, 216, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));
        gameView.addItem(new AnimatePosition(t + 2900,
                new Text(t + 2000, 2000, "By", 0xff94f6af, 216, Typeface.DEFAULT_BOLD, 0.5f, 0.5f),
                0f, 0, 0, 1.5f, 500
        ));

        t += 4100;

        gameView.addItem(new End(t));
    }

    float easeBounce(float t) {
        float n1 = 7.5625f;
        float d1 = 2.75f;

        if (t < 1 / d1) {
            return n1 * t * t;
        } else if (t < 2 / d1) {
            return n1 * (t -= 1.5f / d1) * t + 0.75f;
        } else if (t < 2.5 / d1) {
            return n1 * (t -= 2.25f / d1) * t + 0.9375f;
        } else {
            return n1 * (t -= 2.625f / d1) * t + 0.984375f;
        }
    }
}
