package com.anmol.musicdash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.anmol.musicdash.databinding.FragmentGameBinding;
import com.anmol.musicdash.maingame.GameView;
import com.anmol.musicdash.maingame.levels.AbstractLevel;
import com.anmol.musicdash.maingame.levels.Levels;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;

        ((MainActivity) requireActivity()).soundPlayer.pause();

        GameView gameView = new GameView(requireContext());
        AbstractLevel level = Levels.levels[getArguments().getInt("levelIndex")];
        gameView.activity = ((MainActivity) requireActivity());
        gameView.level = level;
        level.implement(gameView);

        binding.getRoot().addView(gameView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) requireActivity()).soundPlayer.play();
        binding = null;
    }
}