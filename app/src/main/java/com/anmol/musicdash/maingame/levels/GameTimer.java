package com.anmol.musicdash.maingame.levels;

public class GameTimer {
    private long startTimeMillis;

    public void start() {
        startTimeMillis = System.currentTimeMillis();
    }

    public void reset() {
        startTimeMillis = System.currentTimeMillis();
    }

    public long getTimeMillis() {
        return System.currentTimeMillis() - startTimeMillis;
    }
}
