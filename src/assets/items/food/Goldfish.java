package assets.items.food;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

public class Goldfish extends Food{
    public Goldfish(ReferenceList ref) {
        super(ref);
        name = "Goldfish";
        description = "[" + name +"]"+"\nFresh Goldfish from\nthe lake!";
        healthIncrease = 10;
        manaIncrease = 10;
        staminaIncrease = 10;
        setup();
    }




    private void setup() {
        image = Utils.setupImage("/Images/Assets/Items/goldfish", ref.settings.tileSize, ref.settings.tileSize);
    }
}
