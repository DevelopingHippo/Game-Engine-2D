package assets.dynamicEntity.player;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DynamicEntity {

    private boolean isSprinting = false;
    private boolean footstepsSE = false;
    private boolean sprintingSE = false;

    public Player(ReferenceList ref) {
        super(ref);
        name = "Player";
        worldX = ref.settings.playerDefaultWorldX * ref.settings.tileSize;
        worldY = ref.settings.playerDefaultWorldY * ref.settings.tileSize;
        collisionBoxDefaultX = 8;
        collisionBoxDefaultY = 20;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, (int) (ref.settings.tileSize / 1.5), ref.settings.tileSize / 2);
        getPlayerImages();
    }

    private void getPlayerImages() {
        up1 = Utils.setupImage("/Images/Assets/Player/Walking/up1", ref.settings.tileSize, ref.settings.tileSize);
        up2 = Utils.setupImage("/Images/Assets/Player/Walking/up2", ref.settings.tileSize, ref.settings.tileSize);
        up3 = Utils.setupImage("/Images/Assets/Player/Walking/up3", ref.settings.tileSize, ref.settings.tileSize);

        down1 = Utils.setupImage("/Images/Assets/Player/Walking/down1", ref.settings.tileSize, ref.settings.tileSize);
        down2 = Utils.setupImage("/Images/Assets/Player/Walking/down2", ref.settings.tileSize, ref.settings.tileSize);
        down3 = Utils.setupImage("/Images/Assets/Player/Walking/down3", ref.settings.tileSize, ref.settings.tileSize);

        left1 = Utils.setupImage("/Images/Assets/Player/Walking/left1", ref.settings.tileSize, ref.settings.tileSize);
        left2 = Utils.setupImage("/Images/Assets/Player/Walking/left2", ref.settings.tileSize, ref.settings.tileSize);
        left3 = Utils.setupImage("/Images/Assets/Player/Walking/left3", ref.settings.tileSize, ref.settings.tileSize);

        right1 = Utils.setupImage("/Images/Assets/Player/Walking/right1", ref.settings.tileSize, ref.settings.tileSize);
        right2 = Utils.setupImage("/Images/Assets/Player/Walking/right2", ref.settings.tileSize, ref.settings.tileSize);
        right3 = Utils.setupImage("/Images/Assets/Player/Walking/right3", ref.settings.tileSize, ref.settings.tileSize);
        idle = down1;
    }

    public void movePlayer() {
        if(ref.upPressed) {
            worldY = worldY - moveSpeed;
        }
        if(ref.downPressed) {
            worldY = worldY + moveSpeed;
        }
        if(ref.leftPressed) {
            worldX = worldX - moveSpeed;
        }
        if(ref.rightPressed) {
            worldX = worldX + moveSpeed;
        }
    }


    public void update() {
        isMoving = false;
        if(ref.upPressed || ref.downPressed || ref.leftPressed || ref.rightPressed || ref.spacePressed) {
            if(ref.upPressed){
                direction = "up";
                isMoving = true;
            }
            if(ref.downPressed){
                direction = "down";
                isMoving = true;
            }
            if(ref.leftPressed){
                direction = "left";
                isMoving = true;
            }
            if(ref.rightPressed){
                direction = "right";
                isMoving = true;
            }
            if(ref.shiftPressed){
                spriteCounter++;
                isSprinting = true;
                moveSpeed = ref.settings.playerSprintSpeed;
            }
            else {
                moveSpeed = ref.settings.playerDefaultMoveSpeed;
                isSprinting = false;
            }

            collisionOn = false;
            ref.collisionChecker.playerCheckTile(this);

            if(!collisionOn) {

                movePlayer();

                if(!isSprinting && !footstepsSE) {
                    if(sprintingSE){
                        ref.soundEngine.stopSE("footsteps_sprinting");
                        sprintingSE = false;
                    }
                    ref.soundEngine.loopSE("footsteps");
                    footstepsSE = true;
                }
                if(isSprinting && !sprintingSE){
                    if(footstepsSE) {
                        ref.soundEngine.stopSE("footsteps");
                        footstepsSE = false;
                    }
                    ref.soundEngine.loopSE("footsteps_sprinting");
                    sprintingSE = true;
                }
            }
        }
        else {
            if(footstepsSE){
                ref.soundEngine.stopSE("footsteps");
                footstepsSE = false;
            }
            if(sprintingSE) {
                ref.soundEngine.stopSE("footsteps_sprinting");
                sprintingSE = false;
            }
        }
        spriteCounter++;
        if (spriteCounter > spriteFrameTime) {
            if(spriteNum == 4){
                spriteNum = 0;
            }
            spriteNum++;
            spriteCounter = 0;
        }

        //ref.enterPressed = false;
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = ref.settings.screenX;
        int tempScreenY = ref.settings.screenY;

        if(isMoving) { image = animation.walkAnimation3f(); }
        else { image = animation.idleAnimation(); }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        if(ref.settings.debug){
            g2.setColor(Color.red);
            g2.drawRect(collisionBox.x + tempScreenX, collisionBox.y + tempScreenY, collisionBox.width, collisionBox.height);
        }
    }

}
