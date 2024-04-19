package assets.dynamicEntity.npc;

import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

import java.awt.*;

public class NPC extends DynamicEntity {

    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;

    public Rectangle interactBox = new Rectangle(-32, -32, 64, 64);
    public int interactBoxDefaultX = -32;
    public int interactBoxDefaultY = -32;

    public NPC(ReferenceList ref) {
        super(ref);
    }

    public void setDialogue() {
        dialogues[0] = "Hello, adventurer!";
        dialogues[1] = "You should continue adventuring!";
        dialogues[2] = "Why are you still here...";
        dialogues[3] = "Leave!";
        dialogues[4] = "My Social Security check is late again...\nFuck the government!";
    }

    public void speak() {
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
