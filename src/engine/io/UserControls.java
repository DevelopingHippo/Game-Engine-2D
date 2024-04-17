package engine.io;

import engine.helpers.ReferenceList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserControls implements KeyListener {

    private final ReferenceList ref;
    public UserControls(ReferenceList ref) {
        this.ref = ref;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(ref.settings.gameState == ref.settings.playState) {
            playStateKeyPressed(code);
        }
        else if(ref.settings.gameState == ref.settings.titleState) {
            titleStateKeyPressed(code);
        }


    }
    private void titleStateKeyPressed(int code) {
        if(code == KeyEvent.VK_W) {
            ref.upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            ref.downPressed = true;
        }
        if(code == KeyEvent.VK_ENTER) {
            ref.enterPressed = true;
        }

    }

    private void playStateKeyPressed(int code) {

        if(code == KeyEvent.VK_W) {
            ref.upPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            ref.leftPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            ref.downPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            ref.rightPressed = true;
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            ref.upPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            ref.leftPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            ref.downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            ref.rightPressed = false;
        }
    }





    @Override
    public void keyTyped(KeyEvent e) {}
}