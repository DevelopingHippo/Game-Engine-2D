package assets.staticEntity;

import assets.Asset;
import engine.helpers.ReferenceList;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticEntity extends Asset {

    public BufferedImage image, image2, image3, image4;
    public String direction = "down";
    public StaticEntity(ReferenceList ref) {
        super(ref);
        type = "StaticEntity";
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;
        if(isInPlayerVision()) {
            g2.drawImage(image, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            if(ref.settings.debug){
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, collisionBox.width, collisionBox.height);
            }
        }
    }
}
