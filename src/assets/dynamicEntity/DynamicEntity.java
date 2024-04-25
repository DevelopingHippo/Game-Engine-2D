package assets.dynamicEntity;

import assets.Asset;
import assets.animations.Animation;
import assets.dynamicEntity.player.Player;
import engine.helpers.ReferenceList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public class DynamicEntity extends Asset {

    public int moveSpeed = 3;
    public BufferedImage idle, up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage  up_fishing1, up_fishing2, down_fishing1, down_fishing2, left_fishing1, left_fishing2, right_fishing1, right_fishing2;
    public BufferedImage receiveGoldfish;
    public String direction = "idle";
    public Animation animation = new Animation(this);
    public boolean isMoving = false;
    public int spriteNum = 1;
    public int spriteCounter = 0;
    public int spriteFrameTime = 36;

    public int actionLockCounter = 0;

    public boolean collisionOn = false;
    //public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public DynamicEntity(ReferenceList ref) {
        super(ref);
        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);
    }

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == 288) {
            Random random = new Random();
            int i = random.nextInt(125)+1;
            if(i <= 25) {direction = "up";}
            else if(i <= 50) {direction = "down";}
            else if(i <= 75) {direction = "left";}
            else if(i <= 100){direction = "right";}
            else {direction = "idle";}
            actionLockCounter = 0;
        }
    }

    public void animationFrames() {
        spriteCounter++;
        if (spriteCounter > spriteFrameTime) {
            if(spriteNum == 4){
                spriteNum = 0;
            }
            spriteNum++;
            spriteCounter = 0;
        }
    }


    public void moveDynamicEntity() {
        switch (direction) {
            case "up":
                worldY = worldY - moveSpeed;
                break;
            case "down":
                worldY = worldY + moveSpeed;
                break;
            case "left":
                worldX = worldX - moveSpeed;
                break;
            case "right":
                worldX = worldX + moveSpeed;
                break;
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        ref.collisionChecker.checkTile(this);
        ref.collisionChecker.checkCollisionWithPlayer(this);
        if(!collisionOn){
            moveDynamicEntity();
        }
        animationFrames();
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage image;
        if (isInPlayerVision()) {
            image = animation.walkAnimation2f();
            g2.drawImage(image, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            if (ref.settings.debug) {
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, collisionBox.width, collisionBox.height);
            }
        }
    }
}
