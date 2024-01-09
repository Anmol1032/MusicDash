package com.anmol.musicdash.maingame;

import android.graphics.Canvas;

public abstract class Item {
    long timeStart;
    boolean loss = false;
    GameView gameView;

    abstract void click();

    abstract void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer);

    abstract boolean isTouched(float touchX, float touchY, float x, float y, float time);
}
