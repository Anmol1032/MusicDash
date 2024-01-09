package com.anmol.musicdash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.anmol.musicdash.databinding.FragmentStartBinding;


public class StartFragment extends Fragment {

    private FragmentStartBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("ClickableViewAccessibility")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.startButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ((MainActivity) requireActivity()).soundPlayer.clickSound();
                    animateButton(v);
                    break;
                }
                case MotionEvent.ACTION_UP:
                    new Handler(Looper.getMainLooper()).postDelayed(() -> NavHostFragment.findNavController(StartFragment.this).navigate(R.id.action_startFragment_to_levelsFragment), 110);

                    v.performClick();
                    break;
            }
            return true;
        });

    }

    private void animateButton(View v) {
        Animation anim = new ScaleAnimation(
                (float) 1.0, (float) 1.5,
                (float) 1.0, (float) 1.5,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        anim.setFillAfter(true);
        anim.setDuration(100);
        v.startAnimation(anim);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}