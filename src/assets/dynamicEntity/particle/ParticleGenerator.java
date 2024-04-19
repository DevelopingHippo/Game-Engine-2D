package assets.dynamicEntity.particle;

import assets.Asset;
import engine.helpers.ReferenceList;

import javax.security.auth.login.CredentialNotFoundException;
import java.awt.*;

public class ParticleGenerator {

    private ReferenceList ref;

    public ParticleGenerator(ReferenceList ref) {
        this.ref = ref;
    }



    public void generateGrassParticle(Asset asset) {
        Particle p1 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, 1, 1);
        Particle p2 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, -1, 1);
        Particle p3 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, 1, -1);
        Particle p4 = new Particle(ref, asset, Color.decode("#7bc470"), 5, 1, 12, -1, -1);

        ref.assetManager.getParticleList().add(p1);
        ref.assetManager.getParticleList().add(p2);
        ref.assetManager.getParticleList().add(p3);
        ref.assetManager.getParticleList().add(p4);
    }




}
