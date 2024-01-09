package com.anmol.musicdash;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.anmol.musicdash.databinding.FragmentLevelsBinding;
import com.anmol.musicdash.maingame.levels.AbstractLevel;
import com.anmol.musicdash.maingame.levels.Levels;


public class LevelsFragment extends Fragment {

    final int SWITCH_TIME = 500;
    boolean switching;
    int levelCompletedColor;
    int levelUncompletedColor;
    private FragmentLevelsBinding binding;
    private int currentLevel = 0;
    private boolean canPlay = false;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLevelsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("ClickableViewAccessibility")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switching = false;
        levelCompletedColor = getResources().getColor(R.color.levelCompleted, requireActivity().getTheme());
        levelUncompletedColor = getResources().getColor(R.color.levelUncompleted, requireActivity().getTheme());
        initLevel(currentLevel);

        View.OnTouchListener buttonTouchListener = (v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ((MainActivity) requireActivity()).soundPlayer.clickSound();
                    animateButton(v, 1f, 1.5f);
                    break;
                }
                case MotionEvent.ACTION_UP:
                    if (v.getId() == R.id.ButtonR) {
                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                            setLevel(currentLevel + 1);
                            animateButton(v, 1.5f, 1f);
                        }, 110);
                    } else if (v.getId() == R.id.ButtonL) {
                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                            setLevel(currentLevel - 1);
                            animateButton(v, 1.5f, 1f);
                        }, 110);
                    }
                    v.performClick();
                    break;
            }
            return true;
        };

        binding.ButtonR.setOnTouchListener(buttonTouchListener);

        binding.ButtonL.setOnTouchListener(buttonTouchListener);

        binding.LevelLayout.setOnClickListener(v -> {
            if (switching || !canPlay) {
                return;
            }
            ((MainActivity) requireActivity()).soundPlayer.clickSound();
            animateForStart(binding.allContent);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Bundle bundle = new Bundle();
                bundle.putInt("levelIndex", currentLevel);
                try {
                    NavHostFragment.findNavController(LevelsFragment.this).navigate(R.id.action_levelsFragment_to_gameFragment, bundle);
                } catch (Exception ignored) {
                }
            }, SWITCH_TIME * 4);
        });
    }

    private void animateButton(View v, float scaleStart, float scaleEnd) {
        Animation anim = new ScaleAnimation(
                scaleStart, scaleEnd, scaleStart, scaleEnd,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        anim.setFillAfter(true);
        anim.setDuration(100);
        v.startAnimation(anim);
    }

    private void animateView(View v, boolean in, boolean toL) {
        ObjectAnimator rotation1;
        if (toL) {
            rotation1 = ObjectAnimator.ofFloat(v, "rotationY", in ? 0 : 270, in ? 90 : 360);
        } else {
            rotation1 = ObjectAnimator.ofFloat(v, "rotationY", in ? 0 : -270, in ? -90 : -360);
        }
        rotation1.setDuration(SWITCH_TIME);
        rotation1.start();
    }

    private void animateForStart(View v) {
        Animation animAlpha = new AlphaAnimation(1, 0.1f);
        Animation anim = new ScaleAnimation(
                1, 0.01f, 1, 0.01f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(SWITCH_TIME * 4);
        animationSet.setInterpolator(new DecelerateInterpolator(3));
        animationSet.addAnimation(anim);
        animationSet.addAnimation(animAlpha);
        animationSet.setFillAfter(false);
        v.startAnimation(animationSet);
    }

    private void setLevel(int index) {
        if (switching) {
            return;
        }
        boolean toL = index < currentLevel;
        if (index < 0) {
            index = Levels.levels.length;
        }
        if (index > Levels.levels.length) {
            index = 0;
        }
        if (!(currentLevel == index)) {
            switching = true;
            animateView(binding.LevelLayout, true, toL);
            int finalIndex = index;
            currentLevel = finalIndex;
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                initLevel(finalIndex);
                animateView(binding.LevelLayout, false, toL);
            }, SWITCH_TIME);
            new Handler(Looper.getMainLooper()).postDelayed(() -> switching = false, 2 * SWITCH_TIME);
        }
    }

    private void initLevel(int index) {
        if (index < Levels.levels.length) {
            AbstractLevel level = Levels.levels[index];
            String indexString = "Level: " + (index + 1);
            binding.LevelIndex.setText(indexString);
            binding.LevelStars.setText(String.valueOf(level.Stars));

            int color;
            if (((MainActivity) requireActivity()).gameData.completedLevels.contains(level.id)) {
                color = levelCompletedColor;
                binding.StarImage.setAlpha(0.5f);
                binding.completedText.setVisibility(View.VISIBLE);

                binding.LevelName.setText(level.LEVEL_NAME_OUT);
                binding.LevelDescription.setText(level.LEVEL_DESCRIPTION_OUT);
            } else {
                color = levelUncompletedColor;
                binding.StarImage.setAlpha(1f);
                binding.completedText.setVisibility(View.INVISIBLE);

                binding.LevelName.setText(level.LEVEL_NAME_IN);
                binding.LevelDescription.setText(level.LEVEL_DESCRIPTION_IN);
            }
            if (level.StarsRequirement > ((MainActivity) requireActivity()).gameData.starts) {
                binding.LockImage.setVisibility(View.VISIBLE);
                binding.RequiredStarImage.setVisibility(View.VISIBLE);
                binding.RequiredStarText.setVisibility(View.VISIBLE);
                String text = ((MainActivity) requireActivity()).gameData.starts + "/" + level.StarsRequirement;
                binding.RequiredStarText.setText(text);
                canPlay = false;
            } else {
                canPlay = true;
                binding.LockImage.setVisibility(View.INVISIBLE);
                binding.RequiredStarImage.setVisibility(View.INVISIBLE);
                binding.RequiredStarText.setVisibility(View.INVISIBLE);
            }

            binding.LevelIndex.setTextColor(color);
            binding.LevelStars.setTextColor(color);
            binding.LevelName.setTextColor(color);
            binding.LevelDescription.setTextColor(color);
        } else {
            binding.StarImage.setAlpha(0f);
            binding.completedText.setVisibility(View.INVISIBLE);

            binding.LevelName.setText(R.string.wantMoreLevels);
            binding.LevelDescription.setText(R.string.collectStars);

            binding.LevelIndex.setText("");
            binding.LevelStars.setText("");

            binding.LockImage.setVisibility(View.INVISIBLE);

            int maxStar = 0;
            for (AbstractLevel a : Levels.levels) {
                maxStar += Math.max(a.Stars, 0);
            }
            String text = ((MainActivity) requireActivity()).gameData.starts + "/" + (maxStar + 1) + " (wait " + (maxStar + 1) + "!)";
            binding.RequiredStarText.setText(text);
            binding.RequiredStarImage.setVisibility(View.VISIBLE);
            binding.RequiredStarText.setVisibility(View.VISIBLE);

            binding.LevelIndex.setTextColor(levelUncompletedColor);
            binding.LevelStars.setTextColor(levelUncompletedColor);
            binding.LevelName.setTextColor(levelUncompletedColor);
            binding.LevelDescription.setTextColor(levelUncompletedColor);

            canPlay = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}