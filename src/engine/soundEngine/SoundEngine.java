package engine.soundEngine;

import engine.helpers.ReferenceList;

public class SoundEngine {

    private final ReferenceList ref;
    private final Sound music = new Sound();
    private final Sound soundEffect = new Sound();
    private final Sound environmentSound = new Sound();


    public SoundEngine(ReferenceList referenceList) {
        this.ref = referenceList;
    }




    public void playMusic(String soundName) {
        music.setFile(soundName);
        music.setVolume(-30.0f);
        music.play();
        music.loop();
    }
    public void playEnvironment(String soundName) {
        environmentSound.setFile(soundName);
        environmentSound.play();
        environmentSound.setVolume(-30.0f);
        environmentSound.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(String soundName){
        soundEffect.setFile(soundName);
        soundEffect.play();
    }

}
