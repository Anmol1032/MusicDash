package com.anmol.musicdash.maingame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle extends Item {
    final long inTime;
    final long hintTime;
    final float x;
    final float y;
    final float radius;
    final GameSoundPlayer.Sound sound;
    final Paint paint;
    final Paint paint0;
    final Paint paint1;
    boolean clicked;
    boolean played;

    public Circle(long timeStart, long hintTime, long inTime, float x, float y, float radius, int color, GameSoundPlayer.Sound sound) {
        this.timeStart = timeStart;
        this.inTime = inTime;
        this.hintTime = hintTime;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.sound = sound;
        clicked = false;
        loss = false;
        played = false;
        paint = new Paint();
        paint.setColor(color);

        paint0 = new Paint();
        paint0.setColor(color);
        paint0.setStrokeWidth(4);
        paint0.setStyle(Paint.Style.STROKE);

        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setStrokeWidth(6);
        paint1.setStyle(Paint.Style.STROKE);
    }

    @Override
    void click() {
        clicked = true;
    }

    @Override
    void draw(Canvas canvas, float x0, float y0, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        if (!played && time > timeStart + hintTime) {
            played = true;
            gameSoundPlayer.playSound(sound);
        }
        if (!clicked) {
            if (time > timeStart) {
                if (time < timeStart + hintTime) {
                    float t = (float) (time - this.timeStart) / hintTime;
                    paint0.setAlpha((int) (t * 255));
                    canvas.drawCircle((x + offX) * x0, (y + offY) * y0, radius * Math.min(x0, y0) * (2f - t), paint0);
                    canvas.drawCircle((x + offX) * x0, (y + offY) * y0, radius * Math.min(x0, y0), paint1);
                } else if (time < timeStart + hintTime + inTime) {
                    if (!played) {
                        played = true;
                        gameSoundPlayer.playSound(sound);
                    }
                    float t = (float) (time - this.timeStart - hintTime) / inTime;
                    paint0.setAlpha((int) ((1 - t) * 255));
                    paint.setAlpha((int) ((1 - t) * 255));
                    canvas.drawCircle((x + offX) * x0, (y + offY) * y0, radius * Math.min(x0, y0) * (1f - t), paint0);
                    canvas.drawCircle((x + offX) * x0, (y + offY) * y0, radius * Math.min(x0, y0), paint);
                } else {
                    paint.setAlpha(255);
                    canvas.drawCircle((x + offX) * x0, (y + offY) * y0, radius * Math.min(x0, y0), paint);
                    loss = true;
                }
            }
        }
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        double distance = Math.sqrt(Math.pow(touchX * x - this.x * x, 2) + Math.pow(touchY * y - this.y * y, 2));
        return distance <= radius * Math.min(x, y) && time > this.timeStart + hintTime && time < this.timeStart + hintTime + inTime && !clicked;
    }
}
