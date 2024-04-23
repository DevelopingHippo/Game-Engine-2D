package assets.projectile;

import assets.Asset;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

public class Projectile extends DynamicEntity {


    public int maxDuration = 0;
    public int duration = 0;
    public int attackPower = 0;
    public int manaCost = 0;
    public boolean isAlive = false;

    public DynamicEntity caster;


    public Projectile(ReferenceList ref) {
        super(ref);
        type = "Projectile";
    }

    public void shoot(int worldX, int worldY, String direction, boolean alive, DynamicEntity caster) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.isAlive = alive;
        this.caster = caster;
        this.duration = maxDuration;
    }
    public void update() {
        String monsterHit;
        if(caster.type.equals("Player")){
            monsterHit = ref.collisionChecker.checkMonster(this, true);
        }
        else {
            monsterHit = ref.collisionChecker.checkMonster(this, false);
        }
        if(monsterHit != null){
            ref.assetManager.getMonsterByUUID(monsterHit).takeDamage(this, true);
            isAlive = false;
        }
        if(isAlive){
            switch(direction) {
                case "up": worldY-=moveSpeed; break;
                case "down": worldY+=moveSpeed; break;
                case "left": worldX-=moveSpeed; break;
                case "right": worldX+=moveSpeed; break;
            }
            duration--;
            if(duration < 0) {
                isAlive = false;
            }
            animationFrames();
        }
    }

}
