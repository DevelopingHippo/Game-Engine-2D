package assets.dynamicEntity.player;

import assets.items.Item;
import assets.items.armor.Armor;
import assets.items.food.Food;
import assets.items.weapon.Weapon;

import java.util.ArrayList;

public class PlayerBackpack {

    private final Player player;

    public PlayerBackpack(Player player) {this.player = player;}


    public ArrayList<Item> backpack = new ArrayList<>();
    public ArrayList<Weapon> weaponBackpack = new ArrayList<>();
    public ArrayList<Armor> armorBackpack = new ArrayList<>();
    public ArrayList<Food> foodBackpack = new ArrayList<>();


    public void addItemToBackpack(Item item) {
        if(item.type.equals("Weapon")) {
            weaponBackpack.add((Weapon) item);
        }
        else if(item.type.equals("Armor")) {
            armorBackpack.add((Armor) item);
        }
        else if(item.type.equals("Food")) {
            foodBackpack.add((Food) item);
        }
        backpack.add(item);
    }


    public Item getItemFromBackpack(int i) {
        return backpack.get(i);
    }
    public Weapon getWeaponFromBackpack(int i) {
        return weaponBackpack.get(i);
    }
    public Armor getArmorFromBackpack(int i) {
        return armorBackpack.get(i);
    }
    public ArrayList<Item> getBackpack() {
        return backpack;
    }
    public void removeItemFromBackpack(Item item) {
        backpack.remove(item);
    }
    public void removeItemFromBackpack(int item) {
        backpack.remove(item);
    }
}
