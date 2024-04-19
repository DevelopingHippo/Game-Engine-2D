package assets.dynamicEntity.player;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DynamicEntity {

    public boolean isSprinting = false;
    private boolean footstepsSE = false;
    private boolean sprintingSE = false;
    private boolean interacted = false;
    public boolean isFishing = false;
    public boolean caughtFish = false;
    public boolean receiveFish = false;
    public int receiveFishLock = 0;
    public int fishLockCount = 0;
    private int interactLock = 0;
    public PlayerActions actions;
    public boolean speakSE = false;

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
        if(ref.enterPressed){
            ref.settings.gameDrawState = ref.settings.playDrawState;
            if(speakSE) {
                ref.soundEngine.stopSE("npc_speak");
                speakSE = false;
            }
        }
        if(ref.ePressed) {
            if(!interacted) {
                if(ref.collisionChecker.checkInteractZone()) {
                    interacted = true;
                }
                else if(ref.collisionChecker.checkNPC()) {
                    ref.settings.gameDrawState = ref.settings.dialogueDrawState;
                    if(!speakSE){
                        ref.soundEngine.playSE("npc_speak");
                        speakSE = true;
                    }
                    interacted = true;
                }
                else if(ref.collisionChecker.checkInteractTile() && !isFishing) {
                    interacted = true;
                    isFishing = true;
                    ref.soundEngine.playSE("fishing_cast");
                }
            }
        }

        if(isFishing) {
            actions.catchingFishAction();
        }

        if(ref.upPressed || ref.downPressed || ref.leftPressed || ref.rightPressed || ref.spacePressed) {

            actions.checkPlayerMovement();

            actions.checkExtraActions();

            if(!collisionOn) {
                movePlayer();
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
        ref.enterPressed = false;
    }


    public void draw(Graphics2D g2) {
        BufferedImage image;
        int tempScreenX = ref.settings.screenX;
        int tempScreenY = ref.settings.screenY;

        if(isMoving) {
            image = animation.walkAnimation3f();
        }
        else if(isFishing && !receiveFish) {
            if(direction.equals("up")){
                tempScreenY = ref.settings.screenY - ref.settings.tileSize;

            }
            else if(direction.equals("left")) {
                tempScreenX = ref.settings.screenX - ref.settings.tileSize;
            }
            image = animation.fishingAnimation(caughtFish);
        }
        else if(receiveFish) {
            image = animation.receiveFish();
        }
        else {
            image = animation.idleAnimation();
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        if(ref.settings.debug){
            g2.setColor(Color.red);
            g2.drawRect(collisionBox.x + tempScreenX, collisionBox.y + tempScreenY, collisionBox.width, collisionBox.height);
        }
    }

}
