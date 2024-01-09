package com.anmol.musicdash.maingame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;

import com.anmol.musicdash.MainActivity;
import com.anmol.musicdash.R;
import com.anmol.musicdash.maingame.levels.AbstractLevel;
import com.anmol.musicdash.maingame.levels.GameTimer;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    public MainActivity activity;
    public AbstractLevel level;
    GameTimer gameTimer;
    GameSoundPlayer gameSoundPlayer;
    int bg;
    final Paint text = new Paint();
    long winLoseTime = 0;
    Handler handler;
    boolean lose = false;
    boolean win = false;
    private List<Item> items;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bg = Color.BLACK;
        text.setColor(Color.WHITE);
        text.setTextSize(128);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTypeface(ResourcesCompat.getFont(getContext(), R.font.press_start_2p));

        items = new ArrayList<>();
        gameTimer = new GameTimer();
        gameSoundPlayer = new GameSoundPlayer();
        handler = new Handler();
        startContinuousUpdate();
        gameTimer.start();
    }

    public void addItem(Item item) {
        item.gameView = this;
        items.add(item);
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        long time = gameTimer.getTimeMillis();
        canvas.drawColor(bg);
        if (winLoseTime == 0) for (Item item : items) {
            item.draw(canvas, getWidth(), getHeight(), 0, 0, time, gameSoundPlayer);
        }
        else for (Item item : items) {
            item.draw(canvas, getWidth(), getHeight(), 0, 0, winLoseTime, gameSoundPlayer);
        }


        if (lose || win) {
            canvas.drawARGB(191, 0, 0, 0);
            canvas.drawText(lose ? "You Loss" : win ? "Level Completed" : "Error", getWidth() / 2f, getHeight() / 2f - ((text.descent() + text.ascent()) / 2), text);
        }
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    private void startContinuousUpdate() {
        Runnable updateRunnable = new Runnable() {
            @Override
            public void run() {
                for (Item item : items) {
                    if (item.loss) {
                        lose();
                        handler.removeCallbacksAndMessages(null);
                        break;
                    }
                }

                gameSoundPlayer.update(gameTimer.getTimeMillis());

                invalidate();
                postInvalidateOnAnimation();
                handler.postDelayed(this, 30);
            }
        };


        handler.postDelayed(updateRunnable, 100);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            long time = gameTimer.getTimeMillis();
            if (winLoseTime == 0) {
                boolean clicked = false;
                for (Item item : items) {
                    if (isTouched(touchX, touchY, item, time)) {
                        item.click();
                        invalidate();
                        clicked = true;
                        break;
                    }
                }
                if (!clicked) {
                    lose();
                }
            } else if (time > winLoseTime + 1000) {
                if (lose) {
                    bg = Color.BLACK;
                    items.clear();
                    level.implement(this);
                    lose = false;
                    winLoseTime = 0;
                    gameTimer.reset();
                    gameSoundPlayer.play();
                } else if (win) {
                    Navigation.findNavController(this).navigateUp();
                }
            }
        }

        return true;
    }

    private boolean isTouched(float touchX, float touchY, Item item, float time) {
        return item.isTouched(touchX / getWidth(), touchY / getHeight(), getWidth(), getHeight(), time);
    }

    public void lose() {
        if (lose || win) {
            return;
        }
        lose = true;
        winLoseTime = gameTimer.getTimeMillis();
        gameSoundPlayer.pause();
    }

    public void win() {
        if (lose || win) {
            return;
        }
        win = true;
        winLoseTime = gameTimer.getTimeMillis();
        gameSoundPlayer.pause();
        activity.gameData.completedLevels.add(level.id);
        activity.gameData.starts += level.Stars;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
        gameSoundPlayer.destroy();
    }
}
