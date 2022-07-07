package com.example.fragmentstatechange;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private MediaPlayer mediaPlayer;
    int time =0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = MediaPlayer.create(getContext(),R.raw.nguoiemcodo);
        mediaPlayer.start();

        Log.d("Frag A:","On create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState !=null){
            int times = savedInstanceState.getInt("time",0);
            mediaPlayer.seekTo(times);
            mediaPlayer.start();
        }
        Log.d("Fragment A","Start on view create");
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        time = mediaPlayer.getCurrentPosition();
        outState.putInt("time",time);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragment A","On start");
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment A","On resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
        time = mediaPlayer.getCurrentPosition();
        Log.d("Fragment A","On pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragment A","On stop");
        mediaPlayer.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment A","On destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment A","On destroy");
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
