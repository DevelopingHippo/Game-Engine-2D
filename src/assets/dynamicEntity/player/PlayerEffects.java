package assets.dynamicEntity.player;

public class PlayerEffects {

    private final Player player;
    private int regenCounter = 0;

    public PlayerEffects(Player player) {
        this.player = player;
    }


    public void increaseHealth(int h) {
        if(player.stats.currentHealth + h > player.stats.maxHealth) {
            player.stats.currentHealth = player.stats.maxHealth;
        }
        else {
            player.stats.currentHealth += h;
        }
    }
    public void increaseStamina(int s) {
        if(player.stats.currentStamina + s > player.stats.maxStamina) {
            player.stats.currentStamina = player.stats.maxStamina;
        }
        else {
            player.stats.currentStamina += s;
        }
    }
    public void increaseMana(int m) {
        if(player.stats.currentMana + m > player.stats.maxMana) {
            player.stats.currentMana = player.stats.maxMana;
        }
        else {
            player.stats.currentMana += m;
        }
    }


    public void regenResources() {
        regenCounter++;
        if(regenCounter % 72 == 0) {
//            if(player.stats.currentHealth < player.stats.maxHealth){
//                player.stats.currentHealth++;
//            }
            if(player.stats.currentStamina < player.stats.maxStamina){
                if(!player.isSprinting){
                    player.stats.currentStamina++;
                }
            }
            if(player.stats.currentMana < player.stats.maxMana){
                player.stats.currentMana++;
            }
            if(player.stats.currentMana > player.stats.maxMana) {
                player.stats.currentMana = player.stats.maxMana;
            }
            if(player.stats.currentHealth > player.stats.maxHealth) {
                player.stats.currentHealth = player.stats.maxHealth;
            }
            if(player.stats.currentStamina > player.stats.maxStamina){
                player.stats.currentStamina = player.stats.maxStamina;
            }
        }
        if(regenCounter > 144) {
            regenCounter = 0;
        }
    }

}
