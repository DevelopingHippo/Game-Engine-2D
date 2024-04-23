package assets.dynamicEntity.player;

import assets.items.armor.Armor;
import assets.items.armor.IronArmor;
import assets.items.weapon.IronSword;
import assets.items.weapon.Weapon;
import assets.projectile.Fireball;
import assets.projectile.Projectile;

import java.util.ArrayList;

public class PlayerStats {
    private final Player player;


    public PlayerStats(Player player) {this.player = player;}

    // Player Weapon
    public Weapon currentWeapon;
    public ArrayList<Weapon> backpackWeapons = new ArrayList<>();

    // Player Armor
    public Armor currentArmor;
    public ArrayList<Armor> backpackArmors = new ArrayList<>();

    public Projectile currentProjectile;

    // Player Resources
    public int currentHealth = 25;
    public int maxHealth = 25;
    public int currentStamina = 25;
    public int maxStamina = 25;
    public int currentMana = 100;
    public int maxMana = 100;

    // Player Stats
    public int attackPower = 10;
    public int spellPower = 10;
    public int defensePower = 10;

    // Player Perks
    public int strength = 1;
    public int wisdom = 1;
    public int intelligence = 1;
    public int dexterity = 1;
    public int agility = 1;

    // Player Level
    public int level = 0;
    public int totalXP = 0;
    public int xpToNextLevel = 100;

    public int currency = 5;

    public void setupPlayerStats() {
        player.backpack.addItemToBackpack(new IronSword(this.player.ref));
        player.backpack.addItemToBackpack(new IronArmor(this.player.ref));

        this.currentProjectile = new Fireball(this.player.ref);
        this.currentWeapon = player.backpack.getWeaponFromBackpack(0);
        this.currentArmor = player.backpack.getArmorFromBackpack(0);
    }

    public void updatePlayerStats() {
        calculateAttackPower();
        calculateDefensePower();
        calculateSpellPower();
        checkLevelUp();
    }

    private void calculateAttackPower() {
        attackPower = (int) (strength * .5) + currentWeapon.attackRating;
    }
    private void calculateDefensePower() {
        defensePower = (int) (dexterity * .5) + currentArmor.defenseRating;
    }
    private void calculateSpellPower() {
        spellPower = (int) (wisdom * .5) + intelligence + currentWeapon.attackRating;
    }

    private void checkLevelUp() {
        if(totalXP >= xpToNextLevel) {
            level++;
            if(totalXP > xpToNextLevel){
                totalXP -= xpToNextLevel;
            }
            else {
                totalXP = 0;
            }
            xpToNextLevel = (int) (xpToNextLevel * 1.5);
        }
    }
}
