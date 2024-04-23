package assets;

import engine.helpers.ReferenceList;
import java.awt.*;
import java.util.UUID;

public class Asset {

    public final ReferenceList ref;
    public int worldX = 0, worldY = 0;
    public final String uuid = UUID.randomUUID().toString();
    public String name;
    public String type;
    public boolean isAlive = true;
    public boolean collidable = false;
    public Rectangle collisionBox;
    public int collisionBoxDefaultX, collisionBoxDefaultY;


    public int particlesGenerated = 0;
    public int maxParticles = 10;
    public int particleLockCount = 0;
    public boolean particleGenerated = false;

    public Asset (ReferenceList ref) {
        this.ref = ref;
    }

    public boolean isInPlayerVision() {
        return worldX + ref.settings.tileSize > ref.player.worldX - ref.settings.screenX &&
                worldX - ref.settings.tileSize < ref.player.worldX + ref.settings.screenX &&
                worldY + ref.settings.tileSize > ref.player.worldY - ref.settings.screenX &&
                worldY - ref.settings.tileSize < ref.player.worldY + ref.settings.screenY;
    }

    public void update() {
    }



    public void draw(Graphics2D g2) {
        System.out.println("Drawing Asset");
    }
}
