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
    HashMap<String, URL> soundCollection;
    float previousVolume = 0;
    float currentVolume = -30.0f;
    FloatControl fc;
    boolean mute = false;

    public Sound() {



        soundCollection = new HashMap<>();


        soundCollection.put("backgroundMusic", getClass().getResource("/Sounds/Music/Sweden.wav"));
        soundCollection.put("Forest_Day", getClass().getResource("/Sounds/Environment/Forest_Day.wav"));
        soundCollection.put("Forest_Night", getClass().getResource("/Sounds/Environment/Forest_Night.wav"));
        soundCollection.put("sleep", getClass().getResource("/Sounds/SoundEffects/sleep.wav"));

    }

    public void setVolume(float volume) {
        currentVolume = volume;
        fc.setValue(volume);
    }

    public void setFile(String soundName){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundCollection.get(soundName));
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

}

