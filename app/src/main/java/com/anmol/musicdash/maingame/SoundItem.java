package com.anmol.musicdash.maingame;

import android.graphics.Canvas;

public class SoundItem extends Item {
    final float frequency;
    final int duration;
    final float volumeL;
    final float volumeR;
    boolean played;

    public SoundItem(long timeStart, float frequency, float volume, int duration) {
        this.timeStart = timeStart;
        this.frequency = frequency;
        this.duration = duration;
        this.volumeL = volume;
        this.volumeR = volume;
        played = false;
    }

    public SoundItem(long timeStart, float frequency, float volumeL, float volumeR, int duration) {
        this.timeStart = timeStart;
        this.frequency = frequency;
        this.duration = duration;
        this.volumeL = volumeL;
        this.volumeR = volumeR;
        played = false;
    }

    @Override
    void click() {
    }

    @Override
    void draw(Canvas canvas, float x, float y, float offX, float offY, long time, GameSoundPlayer gameSoundPlayer) {
        if (time > this.timeStart && !played) {
            played = true;
            gameSoundPlayer.playSound(new GameSoundPlayer.Sound(frequency, volumeL, volumeR, duration));
        }
    }

    @Override
    boolean isTouched(float touchX, float touchY, float x, float y, float time) {
        return false;
    }
}
