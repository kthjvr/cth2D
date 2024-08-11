package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, rightPressed, leftPressed, downPressed, enterPressed;


    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState){
            titleState(code);
        } else if (gp.gameState == gp.playState) {
            playState(code);
        } else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } else if (gp.gameState == gp.optionState) {
            optionState(code);
        } else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        } else if (gp.gameState == gp.helpState) {
            helpScreen(code);
        } else if (gp.gameState == gp.helpState_Page2) {
            helpScreen2(code);
        }
    }

    private void gameOverState(int code) {

        if (code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
            gp.playSE(5);
        }
        if (code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
            gp.playSE(5);
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.reset();
                gp.playMusic(0);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.reset();
                gp.playMusic(0);
            }
        }
    }

    public void optionState(int code){
        if (code == KeyEvent.VK_SHIFT){
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 6;
                break;
            case 3: maxCommandNum = 1;
        }
        if (code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            gp.playSE(5);
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            gp.playSE(5);
            if (gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_LEFT){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNum == 2 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(5);
                }
                if (gp.ui.commandNum == 3 && gp.soundEffects.volumeScale > 0){
                    gp.soundEffects.volumeScale--;
                    gp.playSE(5);
                }
            }
        }
        if (code == KeyEvent.VK_RIGHT){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNum == 2 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(5);
                }
                if (gp.ui.commandNum == 3 && gp.soundEffects.volumeScale < 5){
                    gp.soundEffects.volumeScale++;
                    gp.playSE(5);
                }
            }
        }

    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_SHIFT){
            gp.gameState = gp.optionState;
        }
    }

    public void titleState(int code){
        if (code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            gp.playSE(5);
            if (gp.ui.commandNum < 1){
                gp.ui.commandNum = 3;
            }
        }
        if (code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            gp.playSE(5);
            if (gp.ui.commandNum > 3){
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 1){
                gp.playSE(5);
//                gp.reset();
                gp.gameState = gp.playState;
            }
            if (gp.ui.commandNum == 2){
                gp.playSE(5);
                gp.gameState = gp.helpState;
            }
            if (gp.ui.commandNum == 3){
                gp.playSE(5);
                System.exit(0);
            }
        }
    }

    public void helpScreen(int code){
        if (code == KeyEvent.VK_LEFT){
            gp.ui.commandNum--;
            gp.playSE(5);
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_RIGHT){
            gp.ui.commandNum++;
            gp.playSE(5);
            if (gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 0){
                gp.playSE(5);
//                gp.reset();
                gp.gameState = gp.helpState_Page2;
            }
            if (gp.ui.commandNum == 1){
                gp.playSE(5);
                gp.gameState = gp.titleState;
            }
        }
    }

    public void helpScreen2(int code){
        if (code == KeyEvent.VK_LEFT){
            gp.ui.commandNum--;
            gp.playSE(5);
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_RIGHT){
            gp.ui.commandNum++;
            gp.playSE(5);
            if (gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 1){
                gp.playSE(5);
                gp.gameState = gp.helpState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code==KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code==KeyEvent.VK_S){
            downPressed = false;
        }
        if (code==KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code==KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
