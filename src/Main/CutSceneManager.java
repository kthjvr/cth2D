package Main;

import java.awt.*;

public class CutSceneManager {

    GamePanel gp;
    Graphics2D g2;

    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;

    String endCredit;

    //scene number
    public final int NA = 0;
    public final int ending = 1;

    public CutSceneManager(GamePanel gp){
        this.gp = gp;

        endCredit = "Game Developers\n\n" +
                "Kathleen Javier\n" +
                "\n\n\n\n\n\n\n\n\n\n\n" +
                "Special Thanks to\n" +
                "\n" +
                "RyiSnow\n" +
                "Visit his youtube channel for tutorials like this game\n" +
                "https://www.youtube.com/@RyiSnow\n"+
                "\n\n\n" +
                "For Game Assets and Music\n" +
                "\n"+
                "RyiSnow\n" +
                "itch.io\n" +
                "\n\n\n\n\n\n" +
                "Thank you for playing!";
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch (sceneNum){
            case ending -> scene_ending();
        }
    }

    public void scene_ending(){
        if (scenePhase == 0){
            gp.stopMusic();
            scenePhase++;
        }
        if (scenePhase == 1){
            gp.ui.drawWinScreen();
            if (counterReached(300)){ //300
                scenePhase++;
            }
        }
        if (scenePhase == 2){
            gp.playSE(9);
            scenePhase++;
        }
        if (scenePhase == 3){
            if (counterReached(300)){ //300
                scenePhase++;
            }
        }
        if (scenePhase == 4){
            alpha += 0.005f;
            if (alpha > 1f){
                alpha = 1f;
            }
            drawBlackBg(alpha);
            if (alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5){
            drawBlackBg(1f);

            alpha += 0.005f;
            if (alpha > 1f){
                alpha = 1f;
            }

            String text = "After exploring the dungeon together and surviving many obstacles, \n" +
                    "the two adventurers found the hidden treasure of the dungeon \n" +
                    "However, this is not the end of their journey.\n" +
                    "The Cat treasure hunt has just begun. \n" +
                    "Go travel the world and collect countless treasure together!\n" +
                    "Until next time, adventurers~";
            drawString(alpha, 34, 200, text, 70);
            if (counterReached(600)){ //600
//                gp.playMusic(10);
                scenePhase++;
            }
        }
        if (scenePhase == 6){
            drawBlackBg(alpha);
            drawString(1f, 120f, gp.screenHeight/2, "Cat Treasure Hunt", 40);
            if (counterReached(300)){ //300
                scenePhase++;
            }

        }
        if (scenePhase == 7){
            drawBlackBg(alpha);
            y = gp.screenHeight/2;
            drawString(1f, 40f, y, endCredit, 40);
            if (counterReached(480)){ //480
                scenePhase++;
            }
        }
        if (scenePhase == 8){
            drawBlackBg(alpha);
            y--;
            drawString(1f, 40f, y, endCredit, 40);
            if (counterReached(1800)){ //1800
                gp.stopMusic();
                scenePhase++;
            }
        } if (scenePhase == 9){
            sceneNum = 0;
            gp.reset();
//            gp.playMusic(0);
            gp.gameState = gp.titleState;
//            System.exit(0);
        }

    }

    public boolean counterReached(int target){
        boolean counterReached = false;
        counter++;
        if (counter > target){
            counterReached = true;
            counter = 0;
        }

        return counterReached;
    }

    public void drawBlackBg(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line: text.split("\n")){
            int x = gp.ui.getXForCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }


}
