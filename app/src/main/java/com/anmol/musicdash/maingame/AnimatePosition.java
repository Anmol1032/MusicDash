package com.anmol.musicdash.maingame;

import android.graphics.Canvas;

public class AnimatePosition extends Item {
    final Item toAnimate;
    final float fromX;
    final float fromY;
    final float toX;
    final float toY;
    final int animationTime;

    public AnimatePosition(long timeStart, Item toAnimate, float fromX, float fromY, float toX, float toY, int animationTime) {
        this.toAnimate = toAnimate;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.animationTime = animationTime;
        this.timeStart = timeStart;
    }

    public AnimatePosition(Item toAnimate, float fromX, float fromY, float toX, float toY, int animationTime) {
        this(toAnimate.timeStart, toAnimate, fromX, fromY, toX, toY, animationTime);
    }

    @Override
    void click() {
        toAnimate.click();
    }

    @Override
    void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        float t = (float) (time - this.timeStart) / animationTime;
        if (t > 1) {
            t = 1;
        } else if (t < 0) {
            t = 0;
        }


        float x0 = lerp(fromX, toX, t);
        float y0 = lerp(fromY, toY, t);

        toAnimate.draw(canvas, x, y, x0 + offX, y0 + offY, time, gameSoundPlayer);
        this.loss = toAnimate.loss;
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        float t = (time - this.timeStart) / animationTime;
        if (t > 1) {
            t = 1;
        } else if (t < 0) {
            t = 0;
        }


        float x0 = lerp(fromX, toX, t);
        float y0 = lerp(fromY, toY, t);
        return toAnimate.isTouched(touchX - x0, touchY - y0, x, y, time);
    }

    protected float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
}
