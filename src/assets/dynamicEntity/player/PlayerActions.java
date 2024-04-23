package assets.dynamicEntity.player;

import assets.items.food.Goldfish;
import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.util.Random;

public class PlayerActions {

    private final ReferenceList ref;
    private final Player player;

    public boolean caughtFish = false;
    public Random rand = new Random();
    public int fishActionCounter = 0;
    public int fishActionMagicNumber;
    public int fishActionNum = 0;
    private boolean catchingFishSE = false;


    public PlayerActions(ReferenceList ref, Player player) {
        this.ref = ref;
        this.player = player;
    }


    public boolean catchingFish() {
        if(fishActionNum == 0){
            fishActionMagicNumber = rand.nextInt(3) + 4;
        }
        if(fishActionCounter > 144) {
            fishActionNum++;
            fishActionCounter = 0;
            if(fishActionNum == fishActionMagicNumber){
                fishActionNum = 0;
                return true;
            }
        }
        fishActionCounter++;
        return false;
    }

    public void catchingFishAction() {
        if(caughtFish){
            if(!catchingFishSE){
                ref.soundEngine.playSE("fishing_caught");
                catchingFishSE = true;
            }
            if(!player.particleGenerated){
                ref.particleGenerator.generateFishSplash(player);
            }
            player.fishLockCount++;
            if(ref.ePressed && !player.receiveFish && !player.isMoving){
                ref.soundEngine.stopSE("fishing_caught");
                ref.soundEngine.playSE("fishing_received");
                player.backpack.addItemToBackpack(new Goldfish(ref));
                player.receiveFish = true;
                player.fishLockCount = 0;
                caughtFish = false;
                catchingFishSE = false;
            }
            if(player.fishLockCount > 244){
                player.fishLockCount = 0;
                caughtFish = false;
                catchingFishSE = false;
                ref.soundEngine.stopSE("fishing_caught");
            }
        }
        else {
            caughtFish = catchingFish();
        }
    }

    public void checkPlayerMovement() {
        if(ref.upPressed){
            player.direction = "up";
            player.isMoving = true;
        }
        if(ref.downPressed){
            player.direction = "down";
            player.isMoving = true;
        }
        if(ref.leftPressed){
            player.direction = "left";
            player.isMoving = true;
        }
        if(ref.rightPressed){
            player.direction = "right";
            player.isMoving = true;
        }
        if(ref.shiftPressed){
            player.spriteCounter++;
            player.isSprinting = true;
            player.moveSpeed = ref.settings.playerSprintSpeed;
        }
        else {
            player.moveSpeed = ref.settings.playerDefaultMoveSpeed;
            player.isSprinting = false;
        }
    }

    public void checkExtraActions() {
        player.collisionOn = false;
        ref.collisionChecker.playerCheckTile(player);

        String collidedEntity = ref.collisionChecker.checkStaticEntity(player, true);

        if(collidedEntity != null){
            player.collisionOn = true;
        }
        if(player.isMoving){
            player.isFishing = false;
        }
    }


    public void getPlayerImages() {
        player.up1 = Utils.setupImage("/Images/Assets/Player/Walking/up1", ref.settings.tileSize, ref.settings.tileSize);
        player.up2 = Utils.setupImage("/Images/Assets/Player/Walking/up2", ref.settings.tileSize, ref.settings.tileSize);
        player.up3 = Utils.setupImage("/Images/Assets/Player/Walking/up3", ref.settings.tileSize, ref.settings.tileSize);

        player.down1 = Utils.setupImage("/Images/Assets/Player/Walking/down1", ref.settings.tileSize, ref.settings.tileSize);
        player.down2 = Utils.setupImage("/Images/Assets/Player/Walking/down2", ref.settings.tileSize, ref.settings.tileSize);
        player.down3 = Utils.setupImage("/Images/Assets/Player/Walking/down3", ref.settings.tileSize, ref.settings.tileSize);

        player.left1 = Utils.setupImage("/Images/Assets/Player/Walking/left1", ref.settings.tileSize, ref.settings.tileSize);
        player.left2 = Utils.setupImage("/Images/Assets/Player/Walking/left2", ref.settings.tileSize, ref.settings.tileSize);
        player.left3 = Utils.setupImage("/Images/Assets/Player/Walking/left3", ref.settings.tileSize, ref.settings.tileSize);

        player.right1 = Utils.setupImage("/Images/Assets/Player/Walking/right1", ref.settings.tileSize, ref.settings.tileSize);
        player.right2 = Utils.setupImage("/Images/Assets/Player/Walking/right2", ref.settings.tileSize, ref.settings.tileSize);
        player.right3 = Utils.setupImage("/Images/Assets/Player/Walking/right3", ref.settings.tileSize, ref.settings.tileSize);

        player.up_fishing1 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_up_1", ref.settings.tileSize, ref.settings.tileSize * 2);
        player.up_fishing2 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_up_2", ref.settings.tileSize, ref.settings.tileSize * 2);

        player.down_fishing1 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_down_1", ref.settings.tileSize, ref.settings.tileSize * 2);
        player.down_fishing2 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_down_2", ref.settings.tileSize, ref.settings.tileSize * 2);

        player.left_fishing1 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_left_1", ref.settings.tileSize * 2, ref.settings.tileSize);
        player.left_fishing2 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_left_2", ref.settings.tileSize * 2, ref.settings.tileSize);

        player.right_fishing1 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_right_1", ref.settings.tileSize * 2, ref.settings.tileSize);
        player.right_fishing2 = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_right_2", ref.settings.tileSize * 2, ref.settings.tileSize);

        player.receiveGoldfish = Utils.setupImage("/Images/Assets/Player/Fishing/fishing_receive_goldfish", ref.settings.tileSize, ref.settings.tileSize);

        player.idle = player.down1;
    }


}
