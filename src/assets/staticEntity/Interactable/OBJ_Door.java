package assets.staticEntity.Interactable;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Door extends InteractableEntity {


    public OBJ_Door(ReferenceList ref) {
        super(ref);
        name = "Door";
        direction = "down";
        collidable = true;
        active = false;

        interactBoxDefaultX = 0;
        interactBoxDefaultY = -ref.settings.tileSize;
        interactBox = new Rectangle(interactBoxDefaultX, interactBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize * 3);

        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);
        setupImages();
    }
    public void setupImages() {
        image = Utils.setupImage("/Images/Assets/Items/door_closed", ref.settings.tileSize, ref.settings.tileSize);
        image2 = Utils.setupImage("/Images/Assets/Items/door_open", ref.settings.tileSize, ref.settings.tileSize);
    }

    public void interact() {
        active = !active;
        collidable = !collidable;
        ref.soundEngine.playSE("door_open");
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage renderImage = image;

        if(active) {
            renderImage = image2;
        }
        if(isInPlayerVision()) {
            g2.drawImage(renderImage, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            if(ref.settings.debug){
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, collisionBox.width, collisionBox.height);
                g2.setColor(Color.green);
                g2.drawRect(screenX + interactBox.x, screenY + interactBox.y, interactBox.width, interactBox.height);
            }
        }
    }
}
