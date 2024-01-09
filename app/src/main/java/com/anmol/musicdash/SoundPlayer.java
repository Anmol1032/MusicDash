package com.anmol.musicdash;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.util.Random;

public class SoundPlayer {
    private static final int SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = AudioTrack.getMinBufferSize(
            SAMPLE_RATE, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
    private final AudioTrack audioTrack;
    boolean isPlaying;
    boolean buttonPressed;

    final float[] f = new float[33];

    public SoundPlayer() {
        for (int i = 0; i < f.length; i++) {
            f[i] = (float) (23.5 * (i + 6));
        }

        isPlaying = true;
        buttonPressed = false;

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

        start();
    }

    private void start() {
        new Thread(() -> {
            audioTrack.play();
            long a = 0;
            short[] buffer = new short[BUFFER_SIZE];
            while (isPlaying) {
                long time = System.currentTimeMillis();
                int t1 = (int) (time / 600 % (f.length));
                int F1 = new Random(t1).nextInt(f.length);

                for (int i = 0; i < BUFFER_SIZE / 2; ++i) {
                    a++;
                    double sampleLeft = 0;
                    double sampleRight = 0;

                    double sample = Math.sin(2 * Math.PI * a * f[F1] / SAMPLE_RATE) + Math.sin(2 * Math.PI * a * f[f.length - F1 - 1] / SAMPLE_RATE) * 1 / 3;
                    double angle = Math.sin(2 * Math.PI * 7000 / SAMPLE_RATE);
                    double volume = 0.75 + Math.sin(2 * Math.PI * 22000 / SAMPLE_RATE) * 0.25;
                    sampleLeft += sample / 23 * angle * volume;
                    sampleRight += sample / 27 * (1 - angle) * volume;

                    if (buttonPressed) {
                        sampleLeft += sample / 13 * (1 - angle);
                        sampleRight += sample / 13 * angle;
                    }

                    buffer[2 * i] = (short) (sampleLeft * Short.MAX_VALUE);
                    buffer[2 * i + 1] = (short) (sampleRight * Short.MAX_VALUE);
                }
                if (buttonPressed) {
                    buttonPressed = false;
                }
                try {
                    audioTrack.write(buffer, 0, BUFFER_SIZE);
                } catch (Exception ignored) {
                }
            }
        }).start();
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

    public void clickSound() {
        buttonPressed = true;
    }

    void destroy() {
        isPlaying = false;
        audioTrack.stop();
        audioTrack.release();
    }
}
