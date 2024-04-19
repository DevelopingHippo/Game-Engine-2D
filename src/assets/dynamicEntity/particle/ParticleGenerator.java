package assets.dynamicEntity.particle;

import assets.Asset;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

import java.awt.*;

public class ParticleGenerator {

    private ReferenceList ref;

    public ParticleGenerator(ReferenceList ref) {
        this.ref = ref;
    }



    public void generateGrassParticle(Asset asset) {
        asset.particleGenerated = true;
        Particle p1 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, 1, 1, 0, 0);
        Particle p2 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, -1, 1, 0, 0);
        Particle p3 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, 1, -1, 0, 0);
        Particle p4 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, -1, -1, 0, 0);

        ref.assetManager.getParticleList().add(p1);
        ref.assetManager.getParticleList().add(p2);
        ref.assetManager.getParticleList().add(p3);
        ref.assetManager.getParticleList().add(p4);
    }


    public void generateFishSplash(DynamicEntity asset) {
        asset.particleGenerated = true;

        Particle p1 = null;
        Particle p2 = null;
        Particle p3 = null;
        Particle p4 = null;

        switch(asset.direction) {
            case "up":
                p1 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, 1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY - (ref.settings.tileSize));
                p2 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, 1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY - (ref.settings.tileSize));
                p3 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, -1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY - (ref.settings.tileSize));
                p4 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, -1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY - (ref.settings.tileSize));
                break;
            case "down":
                p1 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, 1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY + (ref.settings.tileSize * 2) - 5);
                p2 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, 1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY + (ref.settings.tileSize * 2) - 5);
                p3 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, -1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY + (ref.settings.tileSize * 2) - 5);
                p4 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, -1, asset.worldX + (ref.settings.tileSize / 2), asset.worldY + (ref.settings.tileSize * 2) - 5);
                break;
            case "left":
                p1 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, 1, asset.worldX - (ref.settings.tileSize), asset.worldY + (ref.settings.tileSize) - 5);
                p2 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, 1, asset.worldX - (ref.settings.tileSize), asset.worldY + (ref.settings.tileSize) - 5);
                p3 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, -1, asset.worldX - (ref.settings.tileSize), asset.worldY + (ref.settings.tileSize) - 5);
                p4 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, -1, asset.worldX - (ref.settings.tileSize), asset.worldY + (ref.settings.tileSize) -5);
                break;
            case "right":
                p1 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, 1, (asset.worldX + (ref.settings.tileSize * 2)) - 5, asset.worldY + (ref.settings.tileSize) - 5);
                p2 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, 1, asset.worldX + (ref.settings.tileSize * 2) - 5, asset.worldY + (ref.settings.tileSize) - 5);
                p3 = new Particle(ref, asset, Color.blue, 4, 1, 6, 1, -1, asset.worldX + (ref.settings.tileSize * 2) - 5, asset.worldY + (ref.settings.tileSize) - 5);
                p4 = new Particle(ref, asset, Color.blue, 4, 1, 6, -1, -1, asset.worldX + (ref.settings.tileSize * 2) - 5, asset.worldY + (ref.settings.tileSize) -5);
                break;
        }

        if(p1 != null) {
            ref.assetManager.getParticleList().add(p1);
            ref.assetManager.getParticleList().add(p2);
            ref.assetManager.getParticleList().add(p3);
            ref.assetManager.getParticleList().add(p4);
        }
    }




}
