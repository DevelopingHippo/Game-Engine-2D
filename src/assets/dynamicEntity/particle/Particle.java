package assets.dynamicEntity.particle;

import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;
import assets.Asset;

import java.awt.*;

public class Particle extends DynamicEntity {

    public Asset generator;
    public Color color;
    public int xd;
    public int yd;
    public int size;
    public int durationMax;
    public int durationLeft;
    public int worldX;
    public int worldY;
    public boolean isAlive;
    public int speed;

    public Particle(ReferenceList ref, Asset generator, Color color, int size, int speed, int maxDuration, int xd, int yd) {
        super(ref);
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.xd = xd;
        this.yd = yd;
        worldX = generator.worldX + generator.collisionBoxDefaultX;
        worldY = generator.worldY + generator.collisionBoxDefaultY;
        isAlive = true;
        this.durationMax = maxDuration;
        this.durationLeft = maxDuration;
        this.speed = speed;
    }

    public void update() {
        durationLeft--;
        worldX += xd*speed;
        worldY += yd*speed;
        if(durationLeft == 0){
            isAlive = false;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;
        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }


}
