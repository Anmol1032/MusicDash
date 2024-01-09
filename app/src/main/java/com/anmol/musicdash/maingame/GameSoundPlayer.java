package com.anmol.musicdash.maingame;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.util.ArrayList;

public class GameSoundPlayer {
    private static final int SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = AudioTrack.getMinBufferSize(
            SAMPLE_RATE, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
    private static final double TWO_PI = 2.0 * Math.PI;
    final ArrayList<Sound> sounds;
    boolean isPlaying;
    float pTime;
    private AudioTrack audioTrack;

    public GameSoundPlayer() {
        sounds = new ArrayList<>();
        pTime = 0;
        isPlaying = true;
        start();
    }

    private void start() {
        new Thread(() -> {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            AudioFormat audioFormat = new AudioFormat.Builder()
                    .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(SAMPLE_RATE)
                    .build();

            audioTrack = new AudioTrack(
                    audioAttributes,
                    audioFormat,
                    BUFFER_SIZE,
                    AudioTrack.MODE_STREAM,
                    AudioManager.MODE_NORMAL
            );

            audioTrack.play();

            short[] out = new short[BUFFER_SIZE];
            long a = 0;
            while (isPlaying) {
                for (int i = 0; i < BUFFER_SIZE / 2; ++i) {
                    a += 1;
                    double sampleLeft = 0;
                    double sampleRight = 0;

                    try {
                        for (int j = 0; j < sounds.size(); j++) {
                            Sound sound = sounds.get(j);
                            double sample = Math.sin(TWO_PI * 2 * a * sound.frequency / SAMPLE_RATE);
                            sampleLeft += sample * sound.volumeL / 3;
                            sampleRight += sample * sound.volumeR / 3;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    out[2 * i] = (short) (sampleLeft * Short.MAX_VALUE);
                    out[2 * i + 1] = (short) (sampleRight * Short.MAX_VALUE);
                }
                try {
                    audioTrack.write(out, 0, BUFFER_SIZE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void playSound(Sound sound) {
        sounds.add(sound);
    }

    public void update(float time) {
        for (int i = 0; i < sounds.size(); i++) {
            Sound sound = sounds.get(i);
            if (sound.duration <= 0) {
                sounds.remove(sound);
            }
            sound.duration -= time - pTime;
        }
        pTime = time;
    }

    void destroy() {
        isPlaying = false;
        audioTrack.stop();
        audioTrack.release();
    }

    public void pause() {
        if (isPlaying) {
            audioTrack.pause();
            isPlaying = false;
        }
    }

    public void play() {
        if (!isPlaying) {
            isPlaying = true;
            start();
        }
    }

    public static class Sound {
        public final float frequency;
        public final float volumeL;
        public final float volumeR;
        public int duration;

        public Sound() {
            this(0, 0, 0);
        }

        public Sound(float frequency, float volume, int duration) {
            this(frequency, volume, volume, duration);
        }

        public Sound(float frequency, float volumeL, float volumeR, int duration) {
            this.frequency = frequency;
            this.volumeL = volumeL;
            this.volumeR = volumeR;
            this.duration = duration;
        }
    }
}

