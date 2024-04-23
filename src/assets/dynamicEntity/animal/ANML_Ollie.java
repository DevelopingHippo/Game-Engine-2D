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
        up1 = Utils.setupImage("/Images/Assets/Animals/Ollie/ollie_up_1", ref.settings.tileSize, ref.settings.tileSize);

        down1 = Utils.setupImage("/Images/Assets/Animals/Ollie/ollie_down_1", ref.settings.tileSize, ref.settings.tileSize);

        left1 = Utils.setupImage("/Images/Assets/Animals/Ollie/ollie_left_1", ref.settings.tileSize, ref.settings.tileSize);

        right1 = Utils.setupImage("/Images/Assets/Animals/Ollie/ollie_right_1", ref.settings.tileSize, ref.settings.tileSize);
        idle = down1;
    }

    public void update(){
        direction = ref.player.direction;
        this.worldX = ref.player.worldX + 25;
        this.worldY = ref.player.worldY - 25;
        animationFrames();
    }


    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;

        BufferedImage image;
        if (isInPlayerVision()) {

            if(ref.player.isMoving){
                image = animation.walkAnimation1f();
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
