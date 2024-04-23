package assets.staticEntity.Interactable;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Stairs extends InteractableEntity {

    private final boolean goDownstairs;
    private final int teleportWorldX, teleportWorldY;
    public OBJ_Stairs(ReferenceList ref, boolean goDownstairs, int teleportWorldX, int teleportWorldY) {
        super(ref);
        name = "Door";
        direction = "down";
        collidable = false;
        this.goDownstairs = goDownstairs;
        this.teleportWorldX = teleportWorldX * ref.settings.tileSize;
        this.teleportWorldY = teleportWorldY * ref.settings.tileSize;

        interactBoxDefaultX = 0;
        interactBoxDefaultY = 0;
        interactBox = new Rectangle(interactBoxDefaultX, interactBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);

        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);
        setupImages();
    }
    public void setupImages() {
        image = Utils.setupImage("/Images/Assets/Items/stairs_up", ref.settings.tileSize, ref.settings.tileSize);
        image2 = Utils.setupImage("/Images/Assets/Items/stairs_down", ref.settings.tileSize, ref.settings.tileSize);
    }

    public void interact() {
        ref.soundEngine.playSE("stairs");
        ref.player.worldX = teleportWorldX;
        ref.player.worldY = teleportWorldY;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage renderImage = image;

        if(!goDownstairs) {
            renderImage = image2;
        }
        if(isInPlayerVision()) {
            g2.drawImage(renderImage, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            if(ref.settings.debug){
                g2.setColor(Color.red);
                g2.drawRect(screenX + collisionBox.x, screenY + collisionBox.y, collisionBox.width, collisionBox.height);
                g2.setColor(Color.green);
                g2.drawRect(screenX + interactBox.x, screenY + interactBox.y, interactBox.width, interactBox.height);
            }
        }
    }


}
