package com.anmol.musicdash.maingame.levels;

import android.graphics.Color;
import android.graphics.Typeface;

import com.anmol.musicdash.maingame.AnimatePosition;
import com.anmol.musicdash.maingame.Circle;
import com.anmol.musicdash.maingame.End;
import com.anmol.musicdash.maingame.GameSoundPlayer;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.Text;

public class Level3 extends AbstractLevel {
    public Level3() {
        super();
        LEVEL_NAME_IN = "Free Stars";
        LEVEL_NAME_OUT = "No Free Stars";
        LEVEL_DESCRIPTION_IN = "One time use!";
        LEVEL_DESCRIPTION_OUT = "Used";
        Stars = 3;
        id = 3;
    }

    @Override
    public void implement(GameView gameView) {
        if (gameView.activity.gameData.completedLevels.contains(this.id)) {
            gameView.addItem(new Text(1000, 5000, "One time use only", Color.WHITE, 144f, Typeface.DEFAULT, 0.5f, 0.5f));
            gameView.addItem(new End(5000));
        } else {
            gameView.addItem(new AnimatePosition(new Text(0, 2000, "Click To Get Free Stars", Color.WHITE, 144f, Typeface.DEFAULT, 0.5f, 0.25f),
                    1, 0, 0, 0, 500
            ));
            gameView.addItem(new Circle(1000, 400, 1000, 0.5f, 0.65f, 0.3f, Color.WHITE, new GameSoundPlayer.Sound(f[6], 0.5f, 500)));
            gameView.addItem(new End(4000));
        }
    }
}
