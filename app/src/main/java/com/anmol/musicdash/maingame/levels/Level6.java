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

public class Level6 extends AbstractLevel {
    public Level6() {
        super();
        id = 6;
        LEVEL_NAME_IN = "/implement(`EveryThing`);";
        LEVEL_NAME_OUT = "";
        LEVEL_DESCRIPTION_IN = "Is this all levels MIXED";
        LEVEL_DESCRIPTION_OUT = "/handleError(unexpected='Level Completed');\n\nUnexpectedError: player completed level\n    \tat Level(id='" + id + "', title='', state='completed')\n    \tat file <stdin>\n    \t at line 3592";
        Stars = 13;
        StarsRequirement = 9;
    }

    @Override
    public void implement(GameView gameView) {
        long t = 1000;
        Random r = new Random(1421);

        gameView.addItem(new Text(t + 1000, 2000, "/implement(`EveryThing`);", Color.CYAN, 216, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));

        t += 4000;

        for (int i = 0; i < 18; i++) {
            gameView.addItem(new SoundItem(t + 750 * i, f[i * 2 + 7], 0.25f, 0.125f, 300));
            gameView.addItem(new SoundItem(t + 750 * i + 125, f[(i * i + 2 * i) % (f.length - 1)], 0.125f, 0.25f, 300));
            float x = 0.2f + r.nextInt(7) / 7f * 0.8f;
            float y = 0.2f + r.nextInt(7) / 7f * 0.8f;
            gameView.addItem(new Circle(t + 750 * i + 250, 250, 500, x, y, 0.2f, r.nextInt(0xffffff), new GameSoundPlayer.Sound(f[(i * i + 8) % (f.length - 1)], 0.5f, 300)));

            gameView.addItem(new Text(t + 750 * i + 250, 250, String.valueOf(i), Color.WHITE, 144, Typeface.DEFAULT_BOLD, x, y));
        }

        t += 19 * 750;

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

        gameView.addItem(new Text(t + 1000, 1500, "/handleError(unexpected='Level Completed');", Color.CYAN, 128, Typeface.DEFAULT_BOLD, 0.5f, 0.5f));

        t += 3000;
        gameView.addItem(new End(t));
    }
}

