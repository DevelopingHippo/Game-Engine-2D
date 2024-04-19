package assets.dynamicEntity.player;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DynamicEntity {

    private boolean isSprinting = false;
    private boolean footstepsSE = false;
    private boolean sprintingSE = false;
    private boolean interacted = false;
    private boolean isFishing = false;
    private boolean caughtFish = false;
    private boolean receiveFish = false;
    private int receiveFishLock = 0;
    private int fishLock = 0;
    private int fishLockCount = 0;
    private int interactLock = 0;
    public PlayerActions actions;

    public Player(ReferenceList ref) {
        super(ref);
        name = "Player";
        actions = new PlayerActions(ref, this);
        worldX = ref.settings.playerDefaultWorldX * ref.settings.tileSize;
        worldY = ref.settings.playerDefaultWorldY * ref.settings.tileSize;
        collisionBoxDefaultX = 8;
        collisionBoxDefaultY = 20;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, (int) (ref.settings.tileSize / 1.5), ref.settings.tileSize / 2);
        actions.getPlayerImages();
    }



    public void movePlayer() {
        if(!particleGenerated){
            ref.particleGenerator.generateGrassParticle(this);
        }
        if(ref.upPressed) {
            worldY = worldY - moveSpeed;
        }
        if(ref.downPressed) {
            worldY = worldY + moveSpeed;
        }
        if(ref.leftPressed) {
            worldX = worldX - moveSpeed;
        }
        if(ref.rightPressed) {
            worldX = worldX + moveSpeed;
        }
    }

    public void counterIncrease() {
        if(interacted){
            interactLock++;
            if(interactLock > 72){
                interacted = false;
                interactLock = 0;
            }
        }
        if(particleGenerated) {
            particleLockCount++;
            if(particleLockCount > 72){
                particleLockCount = 0;
                particleGenerated = false;
            }
        }
        if(receiveFish){
            receiveFishLock++;
            if(isMoving){
                receiveFishLock = 0;
                receiveFish = false;
            }
            else if(receiveFishLock > 288){
                receiveFishLock = 0;
                receiveFish = false;
            }
        }
    }


    public void update() {
        isMoving = false;
        counterIncrease();

        if(ref.ePressed) {
            if(!interacted) {
                if(ref.collisionChecker.checkInteractZone()) {
                    interacted = true;
                }
                else if(ref.collisionChecker.checkInteractTile()) {
                    interacted = true;
                    isFishing = true;
                }
            }
        }

        if(isFishing) {
            if(caughtFish){
                if(!particleGenerated){
                    ref.particleGenerator.generateFishSplash(this);
                }
                fishLockCount++;
                if(ref.ePressed && !receiveFish){
                    receiveFish = true;
                    fishLockCount = 0;
                    caughtFish = false;
                    System.out.println("RECEIVED A FISH");
                }
                if(fishLockCount > 144){
                    fishLockCount = 0;
                    caughtFish = false;
                }
            }
            else {
                caughtFish = actions.catchingFish();
                if(caughtFish) {
                    System.out.println("A FISH IS BITING");
                }
            }
        }

        if(ref.upPressed || ref.downPressed || ref.leftPressed || ref.rightPressed || ref.spacePressed) {
            if(ref.upPressed){
                direction = "up";
                isMoving = true;
            }
            if(ref.downPressed){
                direction = "down";
                isMoving = true;
            }
            if(ref.leftPressed){
                direction = "left";
                isMoving = true;
            }
            if(ref.rightPressed){
                direction = "right";
                isMoving = true;
            }
            if(ref.shiftPressed){
                spriteCounter++;
                isSprinting = true;
                moveSpeed = ref.settings.playerSprintSpeed;
            }
            else {
                moveSpeed = ref.settings.playerDefaultMoveSpeed;
                isSprinting = false;
            }

            collisionOn = false;
            ref.collisionChecker.playerCheckTile(this);
            String collidedEntity = ref.collisionChecker.checkStaticEntity(this, true);

            if(collidedEntity != null){
                collisionOn = true;
            }

            if(isMoving){
                isFishing = false;
            }

            if(!collisionOn) {
                movePlayer();
                if(!isSprinting && !footstepsSE) {
                    if(sprintingSE){
                        ref.soundEngine.stopSE("footsteps_sprinting");
                        sprintingSE = false;
                    }
                    ref.soundEngine.loopSE("footsteps");
                    footstepsSE = true;
                }
                if(isSprinting && !sprintingSE){
                    if(footstepsSE) {
                        ref.soundEngine.stopSE("footsteps");
                        footstepsSE = false;
                    }
                    ref.soundEngine.loopSE("footsteps_sprinting");
                    sprintingSE = true;
                }
            }
        }
        else {
            if(footstepsSE){
                ref.soundEngine.stopSE("footsteps");
                footstepsSE = false;
            }
            if(sprintingSE) {
                ref.soundEngine.stopSE("footsteps_sprinting");
                sprintingSE = false;
            }
        }

        animationFrames();
        //ref.enterPressed = false;
    }


    public void draw(Graphics2D g2) {
        BufferedImage image;
        int tempScreenX = ref.settings.screenX;
        int tempScreenY = ref.settings.screenY;

        if(isMoving) { image = animation.walkAnimation3f(); }
        else if(isFishing) {

            if(direction.equals("up")){
                tempScreenY = ref.settings.screenY - ref.settings.tileSize;

            }
            else if(direction.equals("left")) {
                tempScreenX = ref.settings.screenX - ref.settings.tileSize;
            }
            image = animation.fishingAnimation(caughtFish);
            if(receiveFish){
                image = animation.receiveFish();
            }
        }
        else { image = animation.idleAnimation(); }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        if(ref.settings.debug){
            g2.setColor(Color.red);
            g2.drawRect(collisionBox.x + tempScreenX, collisionBox.y + tempScreenY, collisionBox.width, collisionBox.height);
        }
    }

}
