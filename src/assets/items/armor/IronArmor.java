package assets.items.armor;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

public class IronArmor extends Armor {

    public IronArmor(ReferenceList ref) {
        super(ref);
        name = "Iron Armor";
        description = "[" + name +"]\nIron Armor for Defense";
        setup();
    }

    private void setup() {
        image = Utils.setupImage("/Images/Assets/Items/iron_armor", ref.settings.tileSize, ref.settings.tileSize);
    }
}
