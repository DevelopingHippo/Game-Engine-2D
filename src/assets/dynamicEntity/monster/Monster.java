package assets.dynamicEntity.monster;

import assets.Asset;
import assets.dynamicEntity.DynamicEntity;
import assets.dynamicEntity.player.Player;
import engine.helpers.ReferenceList;

import java.awt.*;

public class Monster extends DynamicEntity {

    public int currentHealth = 0;
    public int maxHealth = 0;
    public int currentMana = 0;
    public int maxMana = 0;
    public int dyingCounter = 0;
    public boolean isDying = false;
    public int hpBarCounter = 0;
    public boolean hpBarActive = false;
    public int xp;

    public Monster(ReferenceList ref) {
        super(ref);
        type = "Monster";
    }


    public void damageReaction() {
        actionLockCounter = 0;
        direction = ref.player.direction;
    }

    public void deathAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 15;
        if(dyingCounter <= i){changeAlpha(g2, 0f);}
        else if(dyingCounter <= i*2){changeAlpha(g2, 1f);}
        else if(dyingCounter <= i*3){changeAlpha(g2, 0f);}
        else if(dyingCounter <= i*4){changeAlpha(g2, 1f);}
        else if(dyingCounter <= i*5){changeAlpha(g2, 0f);}
        else if(dyingCounter <= i*6){changeAlpha(g2, 1f);}
        else if(dyingCounter <= i*7){changeAlpha(g2, 0f);}
        else if(dyingCounter <= i*8){changeAlpha(g2, 1f);}
        if(dyingCounter > i*9){
            isDying = false;
            isAlive = false;
            ref.assetManager.removeMonsterByUUID(uuid);
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public void takeDamage(Asset source, boolean player) {

        if(player) {
            if(source.type.equals("Projectile")) {
                if(currentHealth <= ref.player.stats.spellPower) {
                    isDying = true;
                    currentHealth = 0;
                }
                else {
                    currentHealth -= ref.player.stats.spellPower;
                    damageReaction();
                }
            }
        }
    }
}
