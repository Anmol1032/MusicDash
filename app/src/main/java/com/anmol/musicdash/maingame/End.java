package com.anmol.musicdash.maingame;

import android.graphics.Canvas;

public class End extends Item {
    public End(long timeStart) {
        this.timeStart = timeStart;
    }

    @Override
    void click() {

    }

    @Override
    void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        if (time > this.timeStart) {
            gameView.win();
        }
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        return false;
    }
}
