package engine.soundEngine;

import engine.helpers.ReferenceList;

import java.net.URL;
import java.util.HashMap;

public class SoundEngine {

    private final ReferenceList ref;
    private final Sound music = new Sound("/Sounds/Music/Sweden.wav");
    HashMap<String, Sound> soundCollection = new HashMap<>();

    public SoundEngine(ReferenceList referenceList) {
        this.ref = referenceList;
        soundCollection.put("backgroundMusic", music);
        soundCollection.put("Forest_Day", new Sound("/Sounds/Environment/Forest_Day.wav"));
        soundCollection.put("Forest_Night", new Sound("/Sounds/Environment/Forest_Night.wav"));
        soundCollection.put("sleep", new Sound("/Sounds/SoundEffects/sleep.wav"));
        soundCollection.put("footsteps", new Sound("/Sounds/SoundEffects/footsteps.wav"));
        soundCollection.put("footsteps_sprinting", new Sound("/Sounds/SoundEffects/footsteps_sprinting.wav"));
        soundCollection.put("stairs", new Sound("/Sounds/SoundEffects/stairs.wav"));
        soundCollection.put("door_open", new Sound("/Sounds/SoundEffects/door_open.wav"));
        soundCollection.put("fishing_cast", new Sound("/Sounds/SoundEffects/fishing_cast.wav"));
        soundCollection.put("fishing_caught", new Sound("/Sounds/SoundEffects/fishing_caught.wav"));
        soundCollection.put("fishing_received", new Sound("/Sounds/SoundEffects/fishing_received.wav"));
        soundCollection.put("npc_speak", new Sound("/Sounds/SoundEffects/npc_speak.wav"));
    }


    public void playMusic(String soundName) {
        soundCollection.get(soundName).setFile();
        soundCollection.get(soundName).setVolume(-30.0f);
        soundCollection.get(soundName).play();
        soundCollection.get(soundName).loop();
    }
    public void playEnvironment(String soundName) {
        soundCollection.get(soundName).setFile();
        soundCollection.get(soundName).play();
        soundCollection.get(soundName).setVolume(-30.0f);
        soundCollection.get(soundName).loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(String soundName){
        soundCollection.get(soundName).setFile();
        soundCollection.get(soundName).play();
    }

    public void loopSE(String soundName) {
        soundCollection.get(soundName).setFile();
        soundCollection.get(soundName).play();
        soundCollection.get(soundName).loop();
    }

    public void stopSE(String soundName) {
        soundCollection.get(soundName).stop();
    }

}
