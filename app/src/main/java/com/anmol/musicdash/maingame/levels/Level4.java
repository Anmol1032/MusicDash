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

public class Level4 extends AbstractLevel {
    public Level4() {
        super();
        LEVEL_NAME_IN = "Level Impossible";
        LEVEL_NAME_OUT = "Level Possible";
        LEVEL_DESCRIPTION_IN = "Just don't play";
        LEVEL_DESCRIPTION_OUT = "Why did you play it";
        Stars = 5;
        StarsRequirement = 1;
        id = 4;
    }

    @Override
    public void implement(GameView gameView) {
        long t = 0;
        Random r = new Random(1012);

        gameView.addItem(new Circle(t + 1000, 400, 1000, 0.5f, 0.5f, 0.3f, Color.WHITE, new GameSoundPlayer.Sound(f[10], 0.5f, 500)));
        gameView.addItem(new Circle(t + 2000, 400, 1000, 0.5f, 0.5f, 0.25f, Color.RED, new GameSoundPlayer.Sound(f[16], 0.5f, 500)));
        gameView.addItem(new Circle(t + 3000, 400, 1000, 0.5f, 0.5f, 0.25f, Color.BLUE, new GameSoundPlayer.Sound(f[12], 0.5f, 500)));
        gameView.addItem(new Circle(t + 4000, 400, 1000, 0.5f, 0.5f, 0.25f, Color.GREEN, new GameSoundPlayer.Sound(f[19], 0.5f, 500)));

        gameView.addItem(new Text(t + 5000, 1000, "It's Easy", Color.CYAN, 72, Typeface.DEFAULT, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 6100, 1000, "No! It's Very Hard!", Color.MAGENTA, 72, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));

        t += 7500;

        gameView.addItem(new BgColor(0x888888, t, 19 * 1000) {
            @Override
            protected int lerpColor(int c1, int c2, float t) {
                int r1 = Color.red(c1);
                int g1 = Color.green(c1);
                int b1 = Color.blue(c1);

                int r2 = Color.red(c2);
                int g2 = Color.green(c2);
                int b2 = Color.blue(c2);

                int r0 = lerpInt(r1, r2, t);
                int g0 = lerpInt(g1, g2, t * t * t);
                int b0 = lerpInt(b1, b2, t * t);

                return Color.rgb(r0, g0, b0);
            }
        });

        for (int i = 0; i < 18; i++) {
            gameView.addItem(new SoundItem(t + 1000 * i, f[i * 2 + 7], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 1000 * i + 250, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.3f + r.nextInt(9) / 9f * 0.7f;
            float y = 0.3f + r.nextInt(9) / 9f * 0.7f;
            float x2 = r.nextInt(4) / 4f * 0.5f - 0.25f;
            float y2 = r.nextInt(4) / 4f * 0.5f - 0.25f;
            gameView.addItem(new AnimatePosition(new Circle(t + 1000 * i + 500, 500, 500, x, y, 0.3f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)),
                    x2, y2, 0, 0, 100
            ));

            gameView.addItem(new Text(t + 1000 * i + 500, 500, String.valueOf(i), Color.WHITE, 144, Typeface.DEFAULT_BOLD, x, y));
        }

        t += 19 * 1000;

        gameView.addItem(new BgColor(0x888888, t, 28 * 1000) {
            @Override
            protected int lerpColor(int c1, int c2, float t) {
                int r1 = Color.red(c1);
                int g1 = Color.green(c1);
                int b1 = Color.blue(c1);

                int r2 = Color.red(c2);
                int g2 = Color.green(c2);
                int b2 = Color.blue(c2);

                int r0 = lerpInt(r1, r2, t);
                int g0 = lerpInt(g1, g2, t * t * t);
                int b0 = lerpInt(b1, b2, t * t);

                return Color.rgb(r0, g0, b0);
            }
        });

        for (int i = 0; i < 28; i++) {
            gameView.addItem(new SoundItem(t + 1000 * i, f[i * 2 + 7], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 1000 * i + 250, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.3f + r.nextInt(9) / 9f * 0.7f;
            float y = 0.3f + r.nextInt(9) / 9f * 0.7f;
            gameView.addItem(new AnimatePosition(new Circle(t + 1000 * i + 500, 500, 500, x, y, 0.2f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)),
                    -x + 0.5f, -y + 0.5f, 0, 0, 1500
            ));
        }

        t += 28 * 1000;

        gameView.addItem(new Text(t + 1000, 1500, "Flashing Warning.....", Color.WHITE, 144, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));

        t += 3000;

        for (int i = 0; i < 28; i++) {
            gameView.addItem(new SoundItem(t + 500 * i, f[i * 2 + 7], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 500 * i + 125, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.3f + r.nextInt(9) / 9f * 0.7f;
            float y = 0.3f + r.nextInt(9) / 9f * 0.7f;
            gameView.addItem(new AnimatePosition(new Circle(t + 500 * i + 250, 250, 300, x, y, 0.255f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)),
                    -x + 0.5f, -y + 0.5f, 0, 0, 600
            ));
            gameView.addItem(new BgColor(i % 2 == 0 ? Color.WHITE : Color.BLACK, t + 500 * i, 100));
        }

        t += 28 * 500;

        gameView.addItem(new Text(t + 1000, 1500, "Nice", Color.CYAN, 216, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));

        t += 3000;

        gameView.addItem(new End(t));
    }
}
