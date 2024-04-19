package engine.renderer.ui;

import engine.helpers.ReferenceList;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    private Font monica;
    private final ReferenceList ref;
    private Graphics2D g2;
    public int commandNum = 0;

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
        if(ref.settings.gameDrawState == ref.settings.playDrawState){

        }
        if(ref.settings.gameDrawState == ref.settings.characterDrawState){

        }
    }


    private void characterState() {

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
