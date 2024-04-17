package assets.dynamicEntity.monster;

import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

import java.awt.*;

public class Monster extends DynamicEntity {

    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;


    public Monster(ReferenceList ref) {
        super(ref);
    }


}
