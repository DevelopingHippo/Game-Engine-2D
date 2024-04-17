package assets.dynamicEntity;

import assets.Asset;
import engine.helpers.ReferenceList;

import java.awt.image.BufferedImage;

public class DynamicEntity extends Asset {

    public int moveSpeed = 0;
    public BufferedImage idle, up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction = "down";

    public DynamicEntity(ReferenceList ref, String uuid) {
        super(ref, uuid);
    }

}
