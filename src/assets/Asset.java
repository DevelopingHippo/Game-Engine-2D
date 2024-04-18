package assets;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;

public class Asset {

    public final ReferenceList ref;
    public int worldX = 0, worldY = 0;
    public final String uuid = Utils.generateUUID();
    public String name;
    public boolean collidable = false;
    public Rectangle collisionBox;
    public int collisionBoxDefaultX, collisionBoxDefaultY;
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
