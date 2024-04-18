package engine;

import assets.Asset;
import assets.dynamicEntity.particle.Particle;
import engine.helpers.ReferenceList;
import engine.helpers.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Engine extends JPanel implements Runnable  {

    public ReferenceList ref = new ReferenceList();
    private Thread engineThread;

    Iterator<Particle> iterParticle;

    public Engine() {
        ref.engine = this;

        this.setPreferredSize(new Dimension(ref.settings.screenWidth, ref.settings.screenHeight));
        this.setBackground(Color.decode(ref.settings.backgroundColor));
        this.setDoubleBuffered(true);

        this.addKeyListener(ref.userControls);
        this.setFocusable(true);
    }

    public void setup() {
        ref.assetManager.spawnAsset("Old Man", 27, 13);
        ref.assetManager.spawnAsset("Door", 11, 27);
        ref.assetManager.spawnStairs(12, 21, 12, 238);
        ref.soundEngine.playMusic("backgroundMusic");
        ref.soundEngine.playEnvironment("Forest_Day");
    }

    public void startEngineThread() {
        engineThread = new Thread(this);
        engineThread.start();
    }


    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / ref.settings.fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(engineThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        ref.player.update();

        for(Asset asset : ref.assetManager.getAllAssets()){
            if(asset != null) {
                asset.update();
            }
        }
        iterParticle = ref.assetManager.getParticleList().iterator();
        while (iterParticle.hasNext()) {
            Particle particle = iterParticle.next();
            if (particle != null) {
                if (particle.isAlive) {
                    particle.update();
                }
                else {
                    iterParticle.remove();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ref.renderer.render(g2);
    }
}
