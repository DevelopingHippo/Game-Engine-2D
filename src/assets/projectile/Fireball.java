package assets.projectile;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

public class Fireball extends Projectile {
    public Fireball(ReferenceList ref) {
        super(ref);
        name = "Fireball";

        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox.x = collisionBoxDefaultX;
        collisionBox.y = collisionBoxDefaultY;
        collisionBox.width = ref.settings.tileSize;
        collisionBox.height = ref.settings.tileSize;

        moveSpeed = 3;
        maxDuration = 144;
        attackPower = 5;
        manaCost = 10;

        setupImages();
    }


    private void setupImages() {
        up1 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/up1", ref.settings.tileSize, ref.settings.tileSize);
        up2 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/up2", ref.settings.tileSize, ref.settings.tileSize);

        down1 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/down1", ref.settings.tileSize, ref.settings.tileSize);
        down2 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/down2", ref.settings.tileSize, ref.settings.tileSize);

        left1 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/left1", ref.settings.tileSize, ref.settings.tileSize);
        left2 = Utils.setupImage("/Images/Assets/Projectiles/Fireball//left2", ref.settings.tileSize, ref.settings.tileSize);

        right1 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/right1", ref.settings.tileSize, ref.settings.tileSize);
        right2 = Utils.setupImage("/Images/Assets/Projectiles/Fireball/right2", ref.settings.tileSize, ref.settings.tileSize);
        idle = down1;
    }




}
