package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class NumberFragment extends Fragment {

    public NumberFragment() {
        // Required empty public constructor
    }

    MediaPlayer mMediaPlayer;
    //    private Function of the completion listener
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mMediaPlayer) {
            releaseResource();

        }
    };

    //    initializing the AudioManager interface
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                pause the playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                RESUME THE PLAYBACK
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseResource();
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        //        providing the value to the Audio manager
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<>();
//initializing the  Word.java file

        words.add(new Word("lutti", "one", R.drawable.donut, R.raw.number_one));
        words.add(new Word("otikko", "two", R.drawable.eclair, R.raw.number_two));
        words.add(new Word("toolokasu", "three", R.drawable.froyo, R.raw.number_three));
        words.add(new Word("oyissa", "four", R.drawable.donut, R.raw.number_four));
        words.add(new Word("masooka", "five", R.drawable.eclair, R.raw.number_five));
        words.add(new Word("temokka", "six", R.drawable.froyo, R.raw.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.donut, R.raw.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.eclair, R.raw.number_eight));
        words.add(new Word("wo e", "nine", R.drawable.donut, R.raw.number_nine));
        words.add(new Word("na achha", "ten", R.drawable.froyo, R.raw.number_ten));


        wordAdapter Adapter = new wordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(Adapter);
//        always initialize this kinds of changes after the setting up of the adapter
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordVar = words.get(position);
                releaseResource();
//                USE THE AUDIO MANAGER TO GET THE AUDIO FOCUS ONLY AFTER THE RESOURCES ARE RELEASED
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    mMediaPlayer = MediaPlayer.create(getActivity(), wordVar.getmAudioResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseResource();
    }

    private void releaseResource() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}