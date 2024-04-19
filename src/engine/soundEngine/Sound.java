package engine.soundEngine;

import engine.helpers.ReferenceList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.HashMap;

public class Sound {

    Clip clip;
    URL soundFile;
    float previousVolume = 0;
    float currentVolume = -25.0f;
    float defaultVolume = -25.0f;
    FloatControl fc;
    boolean mute = false;

    public Sound(String filePath) {
        soundFile = getClass().getResource(filePath);
    }

    public void setVolume(float volume) {
        currentVolume = volume;
        fc.setValue(volume);
    }

    public void setFile(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(currentVolume);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public void volumeUp() {
        currentVolume += 1.0f;
        if(currentVolume > 6.0f) {
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeDown() {
        currentVolume -= 1.0f;
        if(currentVolume < -80.0f) {
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeMute(){
        if(!mute){
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        }
        else {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }

    public void replaceSound(String filepath) {
        this.soundFile = getClass().getResource(filepath);
    }

}

