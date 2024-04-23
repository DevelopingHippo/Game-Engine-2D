package assets.dynamicEntity.monster;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_GreenSlime extends Monster {
    public MON_GreenSlime(ReferenceList ref) {
        super(ref);
        name = "Green Slime";
        getSlimeImages();

        collidable = true;
        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = (ref.settings.tileSize / 2);
        collisionBox.x = collisionBoxDefaultX;
        collisionBox.y = collisionBoxDefaultY;
        collisionBox.width = ref.settings.tileSize;
        collisionBox.height = ref.settings.tileSize / 2;

        maxHealth = 25;
        currentHealth = maxHealth;
        xp = 5;
    }

    private void getSlimeImages() {
        down1 = Utils.setupImage("/Images/Assets/Monster/Mobs/GreenSlime/down1", ref.settings.tileSize, ref.settings.tileSize);
        down2 = Utils.setupImage("/Images/Assets/Monster/Mobs/GreenSlime/down2", ref.settings.tileSize, ref.settings.tileSize);
        idle = down1;
    }

    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 288) {
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25) {
                direction = "up";
            }
            else if(i <= 50) {
                direction = "down";
            }
            else if(i <= 75) {
                direction = "left";
            }
            else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage image;
        if (isInPlayerVision()) {
            image = animation.walkAnimationDown2f();
            if (ref.settings.debug) {
                g2.setColor(Color.red);
                g2.drawRect(screenX + collisionBox.x, screenY + collisionBox.y, collisionBox.width, collisionBox.height);
            }
            double oneScale = (double) ref.settings.tileSize/maxHealth;
            double hpBarValue = oneScale * currentHealth;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(screenX - 1, screenY - 16, ref.settings.tileSize + 2, 12);

            g2.setColor(new Color(255, 0 ,30, 200));
            g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

            hpBarCounter++;

            if(hpBarCounter > 1440 ){
                hpBarCounter = 0;
                hpBarActive = false;
            }


            if(isDying){
                deathAnimation(g2);
            }


            g2.drawImage(image, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            changeAlpha(g2, 1f);
        }

    }


}
