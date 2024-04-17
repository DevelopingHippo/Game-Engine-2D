package assets.dynamicEntity.monster;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

public class MON_GreenSlime extends Monster {
    public MON_GreenSlime(ReferenceList ref) {
        super(ref);
        name = "Green Slime";
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


}
