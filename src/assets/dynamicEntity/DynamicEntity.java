package assets.dynamicEntity;

import assets.Asset;
import assets.animations.Animation;
import engine.helpers.ReferenceList;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DynamicEntity extends Asset {

    public int moveSpeed = 0;
    public BufferedImage idle, up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction = "down";
    public Animation animation = new Animation(this);
    public boolean isMoving = false;
    public int spriteNum = 1;
    public int spriteCounter = 0;
    public int spriteFrameTime = 24;

    public boolean collisionOn = false;
    //public Rectangle attackArea = new Rectangle(0, 0, 0, 0);


    public DynamicEntity(ReferenceList ref, String uuid) {
        super(ref, uuid);
        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);
    }

}
