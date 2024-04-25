package assets.dynamicEntity.npc;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dan extends NPC {


    public Dan(ReferenceList ref) {
        super(ref);
        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize * 2);
        getDanImages();
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Have you seen any flies?!";
        dialogues[1] = "I think I hear one...";
        dialogues[2] = "DAMN IT! You spooked it!";
        dialogues[3] = "Leave!";
        dialogues[4] = "You are looking kind of tasty...";
    }

    public void update() {
        animationFrames();
    }

    private void getDanImages() {
        up1 = Utils.setupImage("/Images/Assets/NPC/Dan/Standing/dan_open", ref.settings.tileSize, ref.settings.tileSize * 2);
        up2 = Utils.setupImage("/Images/Assets/NPC/Dan/Standing/dan_half", ref.settings.tileSize, ref.settings.tileSize * 2);
        up3 = Utils.setupImage("/Images/Assets/NPC/Dan/Standing/dan_full", ref.settings.tileSize, ref.settings.tileSize * 2);
        idle = up1;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage image;
        if (isInPlayerVision()) {
            image = animation.upBlink3f();
            g2.drawImage(image, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize * 2, null);
            if (ref.settings.debug) {
                g2.setColor(Color.green);
                g2.drawRect(screenX + interactBox.x, screenY + interactBox.y, interactBox.width, interactBox.height);
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, collisionBox.width, collisionBox.height);
            }
        }
    }

}
