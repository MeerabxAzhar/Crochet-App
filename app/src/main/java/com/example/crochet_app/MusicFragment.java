package com.example.crochet_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MusicFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        Button startMusic = view.findViewById(R.id.startMusic);
        Button stopMusic = view.findViewById(R.id.stopMusic);

        // Start Music Service
        startMusic.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MusicService.class);
            getActivity().startService(intent);
        });

        // Stop Music Service
        stopMusic.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MusicService.class);
            getActivity().stopService(intent);
        });

        return view;
    }
}
