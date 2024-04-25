package assets.items.food;

import assets.items.Item;
import engine.helpers.ReferenceList;

public class Food extends Item {


    public int healthIncrease = 0;
    public int manaIncrease = 0;
    public int staminaIncrease = 0;

    public Food(ReferenceList ref) {
        super(ref, "Food");
    }


    public void useItem() {
        eatOrDrink();
    }

    public void eatOrDrink() {
        ref.player.effects.increaseHealth(healthIncrease);
        ref.player.effects.increaseStamina(staminaIncrease);
        ref.player.effects.increaseMana(manaIncrease);
    }

}
