package com.anmol.musicdash.maingame;

import android.graphics.Canvas;
import android.graphics.Color;

public class BgColor extends Item {
    final int color;
    final int inTime;
    int inColor;
    boolean needToSetColor;

    public BgColor(int color, long timeStart, int inTime) {
        this.color = color;
        this.timeStart = timeStart;
        this.inTime = inTime;
        needToSetColor = true;
        inColor = 0;
    }

    @Override
    void click() {
    }

    @Override
    void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        if (time > this.timeStart && time < this.timeStart + inTime) {
            if (needToSetColor) {
                inColor = gameView.bg;
                needToSetColor = false;
            }
            float t = (float) (time - this.timeStart) / inTime;
            gameView.setBg(lerpColor(inColor, color, curve(t)));
        }
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        return false;
    }

    protected int lerpColor(int c1, int c2, float t) {
        int r1 = Color.red(c1);
        int g1 = Color.green(c1);
        int b1 = Color.blue(c1);

        int r2 = Color.red(c2);
        int g2 = Color.green(c2);
        int b2 = Color.blue(c2);

        int r0 = lerpInt(r1, r2, t);
        int g0 = lerpInt(g1, g2, t);
        int b0 = lerpInt(b1, b2, t);

        return Color.rgb(r0, g0, b0);
    }

    protected int lerpInt(int a, int b, float t) {
        return (int) (a + (b - a) * t);
    }

    protected float curve(float t) {
        return (float) (t < 0.5
                ? (1 - Math.sqrt(1 - Math.pow(2 * t, 2))) / 2
                : (Math.sqrt(1 - Math.pow(-2 * t + 2, 2)) + 1) / 2);
    }
}
