package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler3 implements KeyListener {

    GamePanel gp;
    public boolean upPressed3, rightPressed3, leftPressed3, downPressed3;


    public KeyHandler3(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


            if (code == KeyEvent.VK_UP) {
                upPressed3 = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed3 = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed3 = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed3 = true;
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

        if (code==KeyEvent.VK_UP) {
            upPressed3 = false;
        }
        if (code==KeyEvent.VK_DOWN){
            downPressed3 = false;
        }
        if (code==KeyEvent.VK_LEFT){
            leftPressed3 = false;
        }
        if (code==KeyEvent.VK_RIGHT) {
            rightPressed3 = false;
        }
    }
}
