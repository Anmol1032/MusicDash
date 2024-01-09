package com.anmol.musicdash;


import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {
    private static final long serialVersionUID = -93412481266767438L;

    public int starts;
    public final ArrayList<Integer> completedLevels;

    public GameData() {
        starts = 0;
        completedLevels = new ArrayList<>();
    }
}
