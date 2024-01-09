package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.R;
import com.anmol.musicdash.maingame.AnimatePosition;
import com.anmol.musicdash.maingame.BgColor;
import com.anmol.musicdash.maingame.Circle;
import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameSoundPlayer;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.SoundItem;
import com.anmol.musicdash.maingame.Text;

import java.util.Arrays;
import java.util.Random;

public class Level5 extends AbstractLevel {
    public Level5() {
        super();
        LEVEL_NAME_IN = "Unnamed Level";
        LEVEL_NAME_OUT = "Reverse Psychology";
        LEVEL_DESCRIPTION_IN = "What should it's name";
        LEVEL_DESCRIPTION_OUT = "This may be a good name";
        Stars = 8;
        StarsRequirement = 4;
        id = 5;
    }

    @Override
    public void implement(GameView gameView) {
        long t = 0;
        Typeface casualFont = Typeface.create("casual", Typeface.NORMAL);

        gameView.addItem(new AnimatePosition(3500, new Text(1000, 4000, gameView.getResources().getString(R.string.app_name), Color.BLACK, 216f, casualFont, 0.5f, 0.5f),
                0, 0, 0, -1.5f, 500
        ));

        gameView.addItem(new BgColor(Color.rgb(255, 127, 255), 1000, 2000) {
            @Override
            protected int lerpColor(int c1, int c2, float t) {
                int r1 = Color.red(c1);
                int g1 = Color.green(c1);
                int b1 = Color.blue(c1);

                int r2 = Color.red(c2);
                int g2 = Color.green(c2);
                int b2 = Color.blue(c2);

                int r0 = lerpInt(r1, r2, curve(t));
                int g0 = lerpInt(g1, g2, t * t * t);
                int b0 = lerpInt(b1, b2, t);

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
                int b0 = lerpInt(b1, b2, t);

                return Color.rgb(r0, g0, b0);
            }
        });

        for (SoundItem soundItem : Arrays.asList(
                new SoundItem(1000, f[8], 1 / 13f, 1600),
                new SoundItem(1250, f[2], 1 / 13f, 1700),
                new SoundItem(1500, f[10], 1 / 13f, 1800),
                new SoundItem(1750, f[5], 1 / 13f, 1900),
                new SoundItem(2000, f[9], 1 / 13f, 1800),
                new SoundItem(2250, f[1], 1 / 13f, 1700),
                new SoundItem(2500, f[10], 1 / 13f, 1600),
                new SoundItem(2750, f[5], 1 / 13f, 1500),
                new SoundItem(3000, f[9], 1 / 13f, 1400),
                new SoundItem(3250, f[1], 1 / 13f, 1300),
                new SoundItem(3500, f[11], 1 / 13f, 1200),
                new SoundItem(3750, f[19], 1 / 13f, 1100),
                new SoundItem(4000, f[12], 1 / 13f, 1000))) {
            gameView.addItem(soundItem);
        }

        t += 6000;

        gameView.addItem(new Text(t, 1000, "Ok...", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 1000, 1000, "I think..", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 2000, 6000, "You want to complete this level.", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 8000, 2000, "It's very easy.", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 10000, 4000, "I know you are the best player...", Color.YELLOW, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 14000, 4000, "and will complete it in one attempt.", Color.WHITE, 144, casualFont, 0.5f, 0.5f));

        t += 18000;

        gameView.addItem(new Circle(t, 1000, 1000, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));
        gameView.addItem(new Text(t + 2000, 2000, "It's very easy.", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 4000, 4000, "It gives 0.5ms to react.", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 8000, 4000, "A normal person can react in just 0.1ms.", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 16000, 4000, "I know you will complete it in one attempt.", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 20000, 4000, "And you must.", Color.WHITE, 164, casualFont, 0.5f, 0.5f));

        gameView.addItem(new Circle(t + 24000, 1000, 1000, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound()));

        t += 26000;

        gameView.addItem(new Text(t + 1000, 2000, "It's very easy.", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 4000, 4000, "It's very easy.", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 8000, 4000, "You will COMPLETE it in just one attempt.", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 16000, 4000, "In Just One ATTEMPT", Color.CYAN, 164, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 20000, 4000, "In Just One ATTEMPT", Color.MAGENTA, 164, casualFont, 0.5f, 0.5f));

        gameView.addItem(new Text(t + 24000, 4000, "It level gives " + Stars + " stars.", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 28000, 2000, "Just focus", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 30000, 6000, "You just have to click on circles...", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 36000, 4000, "It's very easy.", Color.CYAN, 144, casualFont, 0.5f, 0.5f));

        gameView.addItem(new Text(t + 40000, 4000, "Clicking circles is not too hard", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 44000, 4000, "No one can loss such a easy game", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 48000, 8000, "I know you hadn't loss ever in this game.", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 56000, 4000, "Do you reading this", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 60000, 4000, "I just want to say...", Color.WHITE, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 64000, 4000, "FOCUS", Color.CYAN, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 68000, 4000, "FOCUS", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));
        gameView.addItem(new Text(t + 74000, 4000, "It's just Done", Color.MAGENTA, 144, casualFont, 0.5f, 0.5f));

        Random random = new Random(4114);
        long time = 0;
        for (int i = 1; i < 107; i++) {
            time += 1000 - i * 5;
            gameView.addItem(new Circle(t + time, 300, 500, 0.2f + random.nextFloat() * 0.6f, 0.2f + random.nextFloat() * 0.6f, 0.2f, random.nextInt(0xffffff), new GameSoundPlayer.Sound(f[4 + i / 13 + random.nextInt(13 - i / 21)], 0.15f, 300)));
        }
        t += time + 500;  // time: 77645
        gameView.addItem(new End(t));

    }
}
