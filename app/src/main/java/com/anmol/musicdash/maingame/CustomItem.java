package com.anmol.musicdash.maingame;

import android.graphics.Canvas;

public class CustomItem extends Item {
    @Override
    public void click() {

    }

    @Override
    public void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {

    }

    @Override
    public boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        return false;
    }
}
