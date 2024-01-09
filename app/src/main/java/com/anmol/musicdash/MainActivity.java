package com.anmol.musicdash;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.anmol.musicdash.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    public GameData gameData;
    final String mainDataFile = "MainData.dat";
    SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.anmol.musicdash.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(mainDataFile));
            gameData = ((GameData) ois.readObject());
            ois.close();
        } catch (ClassNotFoundException | InvalidClassException | FileNotFoundException e) {
            try {
                boolean exists = new File(getFilesDir(), mainDataFile).createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            gameData = new GameData();
            saveData(gameData);
        } catch (IOException e) {
            e.printStackTrace();
            gameData = new GameData();
            saveData(gameData);
        }
    }

    private void saveData(GameData gameData) {
        if (gameData == null) {
            return;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(mainDataFile, Context.MODE_PRIVATE));
            oos.writeObject(gameData);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPlayer.destroy();
        saveData(gameData);
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPlayer.pause();
        saveData(gameData);
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundPlayer.pause();
        saveData(gameData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundPlayer.play();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundPlayer.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
        soundPlayer = new SoundPlayer();
    }
}