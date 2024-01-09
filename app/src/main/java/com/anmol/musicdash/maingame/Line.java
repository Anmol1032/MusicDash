package com.anmol.musicdash.maingame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Line extends Item {
    final long timeStart;
    final float inTime;
    final float x0;
    final float y0;
    final float x1;
    final float y1;
    final int color;
    final Paint paint;

    public Line(float x0, float y0, float x1, float y1, int color, long timeStart, int inTime) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.timeStart = timeStart;
        this.inTime = inTime;
        this.color = color;
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(color);
    }

    @Override
    void click() {
    }

    @Override
    void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        if (time > timeStart && time < timeStart + inTime) {
            canvas.drawLine(x * (x0 + offX), y * (y0 + offY), x * (x1 + offX), y * (y1 + offY), paint);
        }
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        return false;
    }
}
