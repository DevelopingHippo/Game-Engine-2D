package engine;

import assets.Asset;
import assets.dynamicEntity.monster.Monster;
import assets.dynamicEntity.particle.Particle;
import assets.projectile.Projectile;
import engine.helpers.ReferenceList;
import engine.helpers.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Engine extends JPanel implements Runnable  {

    public ReferenceList ref = new ReferenceList();
    private Thread engineThread;

    Iterator<Particle> iterParticle;
    Iterator<Projectile> iterProjectile;
    Iterator<Asset> iterAsset;

    public Engine() {
        ref.engine = this;

        this.setPreferredSize(new Dimension(ref.settings.screenWidth, ref.settings.screenHeight));
        this.setBackground(Color.decode(ref.settings.backgroundColor));
        this.setDoubleBuffered(true);

        this.addKeyListener(ref.userControls);
        this.setFocusable(true);
    }

    public void setup() {

        ref.assetManager.spawnTestAssets();

        //ref.assetManager.spawnAsset("Ollie", 26, 11);

        //ref.soundEngine.playMusic("backgroundMusic");
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

//        for(Asset asset : ref.assetManager.getAllAssets()){
//            if(asset != null) {
//                if(asset.isAlive) {
//                    asset.update();
//                }
//                else {
//                    ref.assetManager.removeAssetByUUID(asset.uuid);
//                }
//            }
//        }

        iterAsset = ref.assetManager.getAllAssets().iterator();
        while (iterAsset.hasNext()) {
            Asset asset = iterAsset.next();
            if (asset != null) {
                if (asset.isAlive) {
                    asset.update();
                }
                else {
                    iterAsset.remove();
                }
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

        iterProjectile = ref.assetManager.getProjectileList().iterator();
        while (iterProjectile.hasNext()) {
            Projectile projectile = iterProjectile.next();
            if (projectile != null) {
                if (projectile.isAlive) {
                    projectile.update();
                }
                else {
                    iterProjectile.remove();
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
