package com.anmol.musicdash.maingame.levels;

import androidx.annotation.Nullable;

import com.anmol.musicdash.maingame.GameView;

public abstract class AbstractLevel {
    public String LEVEL_NAME_IN;
    public String LEVEL_NAME_OUT;
    public String LEVEL_DESCRIPTION_IN;
    public String LEVEL_DESCRIPTION_OUT;
    public int Stars;
    public int StarsRequirement;
    public int id;
    final float[] f = new float[67];

    {
        for (int i = 0; i < f.length; i++) {
            f[i] = (float) (55 * i);
        }
    }

    public AbstractLevel() {
        LEVEL_NAME_IN = "ERROR!";
        LEVEL_NAME_OUT = "ERROR!";
        LEVEL_DESCRIPTION_IN = "ERROR!";
        LEVEL_DESCRIPTION_OUT = "ERROR!";
        Stars = 0;
        StarsRequirement = 0;
        id = 0;
    }

    abstract public void implement(GameView gameView);

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof AbstractLevel) {
            return ((AbstractLevel) obj).id == this.id;
        }
        return false;
    }
}
