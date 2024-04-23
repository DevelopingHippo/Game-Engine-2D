package assets.dynamicEntity.player;

import assets.items.armor.Armor;
import assets.items.armor.IronArmor;
import assets.items.weapon.IronSword;
import assets.items.weapon.Weapon;

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

    // Player Resources
    public int currentHealth = 25;
    public int maxHealth = 25;
    public int currentStamina = 25;
    public int maxStamina = 25;
    public int currentMana = 25;
    public int maxMana = 25;

    // Player Stats
    public int defensePower = 10;
    public int attackPower = 10;

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


        this.currentWeapon = player.backpack.getWeaponFromBackpack(0);
        this.currentArmor = player.backpack.getArmorFromBackpack(0);
    }
}
