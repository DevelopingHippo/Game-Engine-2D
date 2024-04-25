package assets.dynamicEntity.npc;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;

public class NPC_OldMan extends NPC {
    public NPC_OldMan(ReferenceList ref) {
        super(ref);
        name = "Old Man";
        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        moveSpeed = 1;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);
        getOldManImages();
        setDialogue();
    }

    private void getOldManImages() {
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
}
