package assets.animations;
import assets.dynamicEntity.DynamicEntity;
import java.awt.image.BufferedImage;

public class Animation {

    private final DynamicEntity entity;
    public Animation(DynamicEntity entity) {
        this.entity = entity;
    }

    public BufferedImage walkAnimation3f() {
        BufferedImage image = null;
        switch (entity.direction) {
            case "up":
                if (entity.spriteNum == 1) { image = entity.up1; }
                if (entity.spriteNum == 2) { image = entity.up2; }
                if (entity.spriteNum == 3) { image = entity.up1; }
                if (entity.spriteNum == 4) { image = entity.up3; }
                break;
            case "down":
                if(entity.spriteNum == 1) { image = entity.down1; }
                if(entity.spriteNum == 2) { image = entity.down2; }
                if (entity.spriteNum == 3) { image = entity.down1; }
                if (entity.spriteNum == 4) { image = entity.down3; }
                break;
            case "left":
                if (entity.spriteNum == 1) { image = entity.left1; }
                if (entity.spriteNum == 2) { image = entity.left2; }
                if (entity.spriteNum == 3) { image = entity.left1; }
                if (entity.spriteNum == 4) { image = entity.left3; }
                break;
            case "right":
                if (entity.spriteNum == 1) { image = entity.right1; }
                if (entity.spriteNum == 2) { image = entity.right2; }
                if (entity.spriteNum == 3) { image = entity.right1; }
                if (entity.spriteNum == 4) { image = entity.right3; }
                break;
            case "idle":
                image = entity.idle;
        }
        return image;
    }

    public BufferedImage walkAnimation2f() {
        BufferedImage image = null;
        switch(entity.direction) {
            case "up":
                if (entity.spriteNum == 1) { image = entity.up1; }
                if (entity.spriteNum == 2) { image = entity.up2; }
                if (entity.spriteNum == 3) { image = entity.up1; }
                if (entity.spriteNum == 4) { image = entity.up2; }
                break;
            case "down":
                if(entity.spriteNum == 1) { image = entity.down1; }
                if(entity.spriteNum == 2) { image = entity.down2; }
                if (entity.spriteNum == 3) { image = entity.down1; }
                if (entity.spriteNum == 4) { image = entity.down2; }
                break;
            case "left":
                if (entity.spriteNum == 1) { image = entity.left1; }
                if (entity.spriteNum == 2) { image = entity.left2; }
                if (entity.spriteNum == 3) { image = entity.left1; }
                if (entity.spriteNum == 4) { image = entity.left2; }
                break;
            case "right":
                if (entity.spriteNum == 1) { image = entity.right1; }
                if (entity.spriteNum == 2) { image = entity.right2; }
                if (entity.spriteNum == 3) { image = entity.right1; }
                if (entity.spriteNum == 4) { image = entity.right2; }
                break;
            case "idle":
                image = entity.idle;
        }
        return image;
    }

    public BufferedImage idleAnimation() {
        return switch (entity.direction) {
            case "up" -> entity.up1;
            case "down" -> entity.down1;
            case "left" -> entity.left1;
            case "right" -> entity.right1;
            case "idle" -> entity.idle;
            default -> null;
        };
    }

}
