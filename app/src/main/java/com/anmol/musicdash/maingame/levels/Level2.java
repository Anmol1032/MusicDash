package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.R;
import com.anmol.musicdash.maingame.BgColor;
import com.anmol.musicdash.maingame.Circle;
import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameSoundPlayer;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.SoundItem;
import com.anmol.musicdash.maingame.Text;

import java.util.Random;

public class Level2 extends AbstractLevel {
    public Level2() {
        super();
        LEVEL_NAME_IN = "The 100 Beats";
        LEVEL_NAME_OUT = "The 107 Beats";
        LEVEL_DESCRIPTION_IN = "100 clicks";
        LEVEL_DESCRIPTION_OUT = "107 clicks";
        Stars = 2;
        id = 2;
    }

    @Override
    public void implement(GameView gameView) {
        Typeface casualFont = Typeface.create("casual", Typeface.NORMAL);

        gameView.addItem(new Text(1000, 4000, gameView.getResources().getString(R.string.app_name), Color.BLACK, 216f, casualFont, 0.5f, 0.5f));

        gameView.addItem(new BgColor(Color.rgb(127, 255, 255), 1000, 2000) {
            @Override
            protected int lerpColor(int c1, int c2, float t) {
                int r1 = Color.red(c1);
                int g1 = Color.green(c1);
                int b1 = Color.blue(c1);

                int r2 = Color.red(c2);
                int g2 = Color.green(c2);
                int b2 = Color.blue(c2);

                int r0 = lerpInt(r1, r2, t * t);
                int g0 = lerpInt(g1, g2, t * t * t);
                int b0 = lerpInt(b1, b2, curve(t));

                return Color.rgb(r0, g0, b0);
            }
        });
        gameView.addItem(new BgColor(Color.BLACK, 3000, 2000) {
            @Override
            protected int lerpColor(int c1, int c2, float t) {
                int r1 = Color.red(c1);
                int g1 = Color.green(c1);
                int b1 = Color.blue(c1);

                int r2 = Color.red(c2);
                int g2 = Color.green(c2);
                int b2 = Color.blue(c2);

                int r0 = lerpInt(r1, r2, curve(t));
                int g0 = lerpInt(g1, g2, t * t);
                int b0 = lerpInt(b1, b2, t * t);

                return Color.rgb(r0, g0, b0);
            }
        });

        gameView.addItem(new SoundItem(1000, f[1], 1 / 13f, 1600));
        gameView.addItem(new SoundItem(1250, f[2], 1 / 13f, 1700));
        gameView.addItem(new SoundItem(1500, f[3], 1 / 13f, 1800));
        gameView.addItem(new SoundItem(1750, f[4], 1 / 13f, 1900));
        gameView.addItem(new SoundItem(2000, f[5], 1 / 13f, 1800));
        gameView.addItem(new SoundItem(2250, f[6], 1 / 13f, 1700));
        gameView.addItem(new SoundItem(2500, f[7], 1 / 13f, 1600));
        gameView.addItem(new SoundItem(2750, f[8], 1 / 13f, 1500));
        gameView.addItem(new SoundItem(3000, f[9], 1 / 13f, 1400));
        gameView.addItem(new SoundItem(3250, f[10], 1 / 13f, 1300));
        gameView.addItem(new SoundItem(3500, f[11], 1 / 13f, 1200));
        gameView.addItem(new SoundItem(3750, f[12], 1 / 13f, 1100));
        gameView.addItem(new SoundItem(4000, f[13], 1 / 13f, 1000));

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 13; j++) {
                gameView.addItem(new SoundItem(5000 + 1000 * i * 13 + 1000 * j, f[i ^ j + 4], 1 / 13f, 1500));
            }
        }

        long t = 5000;

        Random random = new Random(441);
        long time = 0;
        for (int i = 1; i < 107; i++) {
            time += 1000 - i * 5;
            gameView.addItem(new Circle(t + time, 300, 500, 0.2f + random.nextFloat() * 0.6f, 0.2f + random.nextFloat() * 0.6f, 0.2f, random.nextInt(0xffffff), new GameSoundPlayer.Sound(f[4 + i / 13 + random.nextInt(13 - i / 21)], 0.15f, 300)));
        }

        gameView.addItem(new End(t + time + 3000));
    }
}
