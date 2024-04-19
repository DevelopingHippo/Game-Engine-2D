package assets.dynamicEntity.animal;

import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ANML_Ollie extends DynamicEntity {


    public ANML_Ollie(ReferenceList ref) {
        super(ref);

        name = "Ollie";

        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);

        getOllieImages();
    }


    private void getOllieImages() {
        up1 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/up1", ref.settings.tileSize, ref.settings.tileSize);
        up2 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/up2", ref.settings.tileSize, ref.settings.tileSize);

        down1 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/down1", ref.settings.tileSize, ref.settings.tileSize);
        down2 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/down2", ref.settings.tileSize, ref.settings.tileSize);

        left1 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/left1", ref.settings.tileSize, ref.settings.tileSize);
        left2 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/left2", ref.settings.tileSize, ref.settings.tileSize);

        right1 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/right1", ref.settings.tileSize, ref.settings.tileSize);
        right2 = Utils.setupImage("/Images/Assets/NPC/OldMan/Walking/right2", ref.settings.tileSize, ref.settings.tileSize);
        idle = down1;
    }

    public void update(){
        direction = ref.player.direction;
        this.worldX = ref.player.worldX + 25;
        this.worldY = ref.player.worldY - 25;

        spriteCounter++;
        if (spriteCounter > spriteFrameTime) {
            if(spriteNum == 4){
                spriteNum = 0;
            }
            spriteNum++;
            spriteCounter = 0;
        }


    }


    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage image;
        if (isInPlayerVision()) {

            if(ref.player.isMoving){
                image = animation.walkAnimation2f();
            }
            else {
                image = animation.idleAnimation();
            }

            g2.drawImage(image, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            if (ref.settings.debug) {
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, collisionBox.width, collisionBox.height);
            }
        }
    }



}
