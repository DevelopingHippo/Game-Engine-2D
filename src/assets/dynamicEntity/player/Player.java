package assets.dynamicEntity.player;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DynamicEntity {



    public Player(ReferenceList ref, String uuid) {
        super(ref, uuid);
        worldX = ref.settings.playerDefaultWorldX;
        worldY = ref.settings.playerDefaultWorldY;
        getPlayerImages();
    }

    private void getPlayerImages() {
        up1 = Utils.setupImage("/Assets/Player/Walking/up1", ref.settings.tileSize, ref.settings.tileSize);
        up2 = Utils.setupImage("/Assets/Player/Walking/up2", ref.settings.tileSize, ref.settings.tileSize);
        up3 = Utils.setupImage("/Assets/Player/Walking/up3", ref.settings.tileSize, ref.settings.tileSize);

        down1 = Utils.setupImage("/Assets/Player/Walking/down1", ref.settings.tileSize, ref.settings.tileSize);
        down2 = Utils.setupImage("/Assets/Player/Walking/down2", ref.settings.tileSize, ref.settings.tileSize);
        down3 = Utils.setupImage("/Assets/Player/Walking/down3", ref.settings.tileSize, ref.settings.tileSize);


        left1 = Utils.setupImage("/Assets/Player/Walking/left1", ref.settings.tileSize, ref.settings.tileSize);
        left2 = Utils.setupImage("/Assets/Player/Walking/left2", ref.settings.tileSize, ref.settings.tileSize);
        left3 = Utils.setupImage("/Assets/Player/Walking/left3", ref.settings.tileSize, ref.settings.tileSize);

        right1 = Utils.setupImage("/Assets/Player/Walking/right1", ref.settings.tileSize, ref.settings.tileSize);
        right2 = Utils.setupImage("/Assets/Player/Walking/right2", ref.settings.tileSize, ref.settings.tileSize);
        right3 = Utils.setupImage("/Assets/Player/Walking/right3", ref.settings.tileSize, ref.settings.tileSize);

        idle = down1;
    }


    public void update() {

        if(ref.upPressed || ref.downPressed || ref.leftPressed || ref.rightPressed){
            if(ref.upPressed){
                direction = "up";
            }
            if(ref.downPressed){
                direction = "down";
            }
            if(ref.leftPressed){
                direction = "left";
            }
            if(ref.rightPressed){
                direction = "right";
            }
            switch (direction) {
                case "up": worldY = worldY - moveSpeed; break;
                case "down": worldY = worldY + moveSpeed; break;
                case "left": worldX = worldX - moveSpeed; break;
                case "right": worldX = worldX + moveSpeed; break;
            }
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image;
        int tempScreenX = ref.settings.screenX;
        int tempScreenY = ref.settings.screenY;

        worldX = ref.settings.tileSize * ref.settings.playerDefaultWorldX;
        worldY = ref.settings.tileSize * ref.settings.playerDefaultWorldY;




    }

}
