package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler2 implements KeyListener {

    GamePanel gp;
    public boolean upPressed2, rightPressed2, leftPressed2, downPressed2;

    public KeyHandler2(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

            if (code == KeyEvent.VK_I) {
                upPressed2 = true;
            }
            if (code == KeyEvent.VK_K) {
                downPressed2 = true;
            }
            if (code == KeyEvent.VK_J) {
                leftPressed2 = true;
            }
            if (code == KeyEvent.VK_L) {
                rightPressed2 = true;
            }
            if (code == KeyEvent.VK_ESCAPE){
                if (gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                } else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }

        }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code==KeyEvent.VK_I) {
            upPressed2 = false;
        }
        if (code==KeyEvent.VK_K){
            downPressed2 = false;
        }
        if (code==KeyEvent.VK_J){
            leftPressed2 = false;
        }
        if (code==KeyEvent.VK_L) {
            rightPressed2 = false;
        }
    }
}
