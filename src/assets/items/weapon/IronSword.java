package assets.items.weapon;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

public class IronSword extends Weapon{

    public IronSword(ReferenceList ref) {
        super(ref);
        name = "Iron Sword";
        description = "[" + name +"]\nAn Iron Sword";
        setup();
    }

    private void setup() {
        image = Utils.setupImage("/Images/Assets/Items/iron_sword", ref.settings.tileSize, ref.settings.tileSize);
    }
}
