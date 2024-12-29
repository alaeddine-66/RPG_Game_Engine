package com.engine.model.resource;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import java.util.HashMap;

public class AudioManager implements AudioPlayer {
    private static volatile AudioManager instance ;
    private static HashMap<String, Sound> soundEffects;
    private static Music backgroundMusic;

    private AudioManager() {
        soundEffects = new HashMap<>();
    }

    public static AudioManager getInstance() {
        if (AudioManager.instance == null) {
            synchronized(AudioManager.class) {
                if (AudioManager.instance == null) {
                    AudioManager.instance = new AudioManager();
                }
            }
        }
        return AudioManager.instance;
    }

    public void loadSounds(String name , String path) {
          soundEffects.put(name, Gdx.audio.newSound(Gdx.files.internal(path)));
    }

    public void loadBackGroundMusic(String path){
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        backgroundMusic.setLooping(true);
    }

    @Override
    public void playSound(String soundKey , float volume) {
        Sound sound = soundEffects.get(soundKey);
        if (sound != null) {
            sound.play(volume);
        }
    }

    public void playMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(0.1f); // Volume à 10%
            backgroundMusic.play();
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null) backgroundMusic.stop();
    }
}

