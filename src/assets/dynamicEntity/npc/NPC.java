package assets.dynamicEntity.npc;

import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC extends DynamicEntity {

    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;
    public boolean isSpeaking = false;
    public Rectangle interactBox = new Rectangle(-48, -48, 144, 144);
    public int interactBoxDefaultX = -32;
    public int interactBoxDefaultY = -32;
    private int speakMoveLock = 0;

    public NPC(ReferenceList ref) {
        super(ref);
        type = "NPC";
    }

    public void setDialogue() {
        dialogues[0] = "Hello, adventurer!";
        dialogues[1] = "You should continue adventuring!";
        dialogues[2] = "Why are you still here...";
        dialogues[3] = "Leave!";
        dialogues[4] = "My Social Security check is late again...\nFuck the government!";
    }

    public void update() {
        setAction();
        collisionOn = false;
        ref.collisionChecker.checkTile(this);
        ref.collisionChecker.checkCollisionWithPlayer(this);
        if(!collisionOn && !isSpeaking){
            moveDynamicEntity();
        }
        animationFrames();
    }


    public void draw(Graphics2D g2) {
        int screenX = worldX - ref.player.worldX + ref.settings.screenX;
        int screenY = worldY - ref.player.worldY + ref.settings.screenY;
        BufferedImage image;
        if (isInPlayerVision()) {
            image = animation.walkAnimation2f();
            g2.drawImage(image, screenX, screenY, ref.settings.tileSize, ref.settings.tileSize, null);
            if (ref.settings.debug) {
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, collisionBox.width, collisionBox.height);
                g2.setColor(Color.green);
                g2.drawRect(screenX + interactBox.x, screenY + interactBox.y, interactBox.width, interactBox.height);
            }
        }
    }

    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 288) {
            Random random = new Random();
            int i = random.nextInt(125)+1;
            if(i <= 25) {direction = "up";}
            else if(i <= 50) {direction = "down";}
            else if(i <= 75) {direction = "left";}
            else if(i <= 100){direction = "right";}
            else {direction = "idle";}
            actionLockCounter = 0;

        }
        if(isSpeaking){
            speakMoveLock++;
            if(speakMoveLock > 288){
                isSpeaking = false;
                speakMoveLock = 0;
            }
        }


    }

    public void speak() {
        isSpeaking = true;
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        ref.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch(ref.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}
