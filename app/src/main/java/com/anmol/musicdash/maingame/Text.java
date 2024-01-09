package com.anmol.musicdash.maingame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Text extends Item {
    final long inTime;
    final String text;
    final Paint paint;
    final float x;
    final float y;

    public Text(long timeStart, long inTime, String text, int color, float size, Typeface font, float x, float y) {
        this.timeStart = timeStart;
        this.inTime = inTime;
        this.text = text;
        this.paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(size);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(font);
        this.x = x;
        this.y = y;
    }

    @Override
    void click() {
    }

    @Override
    void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        if (time > this.timeStart && time < this.timeStart + inTime) {
            canvas.drawText(text, x * (this.x + offX), y * (this.y + offY) - ((paint.descent() + paint.ascent()) / 2), paint);
        }
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        return false;
    }
}
