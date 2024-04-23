package engine.renderer.ui;

import assets.items.Item;
import engine.helpers.ReferenceList;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    private Font monica;
    private final ReferenceList ref;
    private Graphics2D g2;
    public int commandNum = 0;
    public String currentDialogue = "";
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(ReferenceList ref) {
        this.ref = ref;
        try {
            InputStream is = getClass().getResourceAsStream("/Font/Monica.ttf");
            assert is != null;
            monica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch(FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(monica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if(ref.settings.gameState == ref.settings.playState){
            drawPlayState();
        }
        else if(ref.settings.gameState == ref.settings.titleState) {
            drawTitleState();
        }
    }

    private void drawTitleState() {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, ref.settings.screenWidth, ref.settings.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));

        // TITLE NAME
        String text = "Tales of Adventure";
        int x = getXforCenteredText(text);
        int y = ref.settings.tileSize * 3;

        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // MAIN CHARACTER IMAGE
        x = (ref.settings.screenWidth / 2) - (ref.settings.tileSize*2) / 2;
        y += ref.settings.tileSize * 2;
        g2.drawImage(ref.player.down1, x, y, ref.settings.tileSize * 2, ref.settings.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "SOLO GAME";
        x = getXforCenteredText(text);
        y += (int) (ref.settings.tileSize * 3.5);
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - ref.settings.tileSize, y);
        }

        text = "OPTIONS";
        x = getXforCenteredText(text);
        y += ref.settings.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - ref.settings.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += ref.settings.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x - ref.settings.tileSize, y);
        }

    }


    private void drawPlayState() {
        if(ref.settings.gameDrawState == ref.settings.characterDrawState){
            characterState();
        }
        if (ref.settings.gameDrawState == ref.settings.dialogueDrawState) {
            drawDialogueState();
        }
    }
    private void drawDialogueState() {
        drawDialogueScreen();
    }

    private void characterState() {
        drawCharacterScreen();
        drawBackpackScreen();
    }



    private void drawDialogueScreen() {
        int x = ref.settings.tileSize * 2;
        int y = ref.settings.tileSize / 2;
        int width = ref.settings.screenWidth - (ref.settings.tileSize * 4);
        int height = ref.settings.tileSize * 4;
        drawSubWindow(x, y, width, height);
        x+= ref.settings.tileSize;
        y+= ref.settings.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
        if(currentDialogue != null){
            for(String line : currentDialogue.split("\n")) {
                g2.drawString(line, x, y);
                y+= 40;
            }
        }
    }

    private void drawMessage() {
        int messageX = ref.settings.tileSize;
        int messageY = ref.settings.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));
        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i) > 420) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    private void drawCharacterScreen() {
        // CREATE FRAME
        final int frameX = ref.settings.tileSize;
        final int frameY = ref.settings.tileSize;
        final int frameWidth = ref.settings.tileSize * 5;
        final int frameHeight = ref.settings.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + ref.settings.tileSize;
        final int lineHeight = 35;

        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Health", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("XP", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Armor", textX, textY);


        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + ref.settings.tileSize;
        String value;

        value = String.valueOf(ref.player.stats.level);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = ref.player.stats.currentHealth + "/" + ref.player.stats.maxHealth;
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(ref.player.stats.strength);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(ref.player.stats.dexterity);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(ref.player.stats.attackPower);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(ref.player.stats.defensePower);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = ref.player.stats.totalXP + "/" + ref.player.stats.xpToNextLevel;
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(ref.player.stats.currency);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += ref.settings.tileSize;

        g2.drawImage(ref.player.stats.currentWeapon.image, tailX - ref.settings.tileSize, textY - 27, null);
        textY += ref.settings.tileSize;

        g2.drawImage(ref.player.stats.currentArmor.image, tailX - ref.settings.tileSize, textY - 27, null);
    }


    private void drawBackpackScreen() {
        int frameX = (int) (ref.settings.tileSize * 13.5);
        int frameY = ref.settings.tileSize;
        int frameWidth = ref.settings.tileSize * 6;
        int frameHeight = ref.settings.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOTS
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = ref.settings.tileSize;

        // DRAW CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = slotSize;
        int cursorHeight = slotSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        for(Item item : ref.player.backpack.getBackpack()){
            if(item != null){
                g2.drawImage(item.image, slotX, slotY, null);
                slotX += slotSize;
                if(slotX >= slotXstart + (slotSize * 5)) {
                    slotY += slotSize;
                    slotX = slotXstart;
                }
            }
        }

        // DRAW DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = ref.settings.tileSize * 3;

        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + ref.settings.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));

        if(getHoveredItem() != 999){
            for(String line : ref.player.backpack.getItemFromBackpack(getHoveredItem()).description.split("\n")){
                g2.drawString(line, textX,textY);
                textY += 26;
            }
        }
    }

    private int getHoveredItem() {
        int itemIndex = slotCol + (slotRow * 5);
        if(itemIndex < ref.player.backpack.getBackpack().size()) {
            return itemIndex;
        }
        return 999;
    }


    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width,height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    private int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (ref.settings.screenWidth / 2) - (length / 2);
    }
    private int getXforAlignToRight(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
