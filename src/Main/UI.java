package Main;

import Entity.Entity;
import Object.OBJ_Heart;
import Object.OBJ_Key;
import Object.OBJ_Key_Silver;
import Object.OBJ_Key_Amethyst;
import Object.OBJ_Door;
import Object.OBJ_Door_Silver;
import Object.OBJ_Door_Amethyst;
import Object.OBJ_Boots;
import Object.OBJ_Goo;
import Object.OBJ_Potion;
import Object.OBJ_Hole;
import Object.OBJ_Spike;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font Fortzilla;

    BufferedImage heart_full, heart_blank, heart_half, keyGold, keySil, keyAmy, doorGold, doorSil, doorAmy, boots, goo, potion, tel, spike;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;

    public String currentDialogue = "";

    int subState = 0;

    public int slotCol = 0;
    public int slotRow = 0;
    float alpha = 0f;

    public UI(GamePanel gp) {
        this.gp = gp;
        InputStream is = getClass().getResourceAsStream("/font/Fortzilla-Regular.ttf");
        try {
            assert is != null;
            Fortzilla = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        //create hud object
        Entity heart  = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity keyG = new OBJ_Key(gp);
        keyGold = keyG.down1;
        Entity keyS = new OBJ_Key_Silver(gp);
        keySil = keyS.down1;
        Entity keyA = new OBJ_Key_Amethyst(gp);
        keyAmy = keyA.down1;

        Entity doorG = new OBJ_Door(gp);
        doorGold = doorG.down1;
        Entity doorS = new OBJ_Door_Silver(gp);
        doorSil = doorS.down1;
        Entity doorA = new OBJ_Door_Amethyst(gp);
        doorAmy = doorA.down1;

        Entity Goo = new OBJ_Goo(gp);
        goo = Goo.down1;
        Entity bootss = new OBJ_Boots(gp);
        boots = bootss.down1;
        Entity hole = new OBJ_Hole(gp);
        tel = hole.down1;
        Entity pot = new OBJ_Potion(gp);
        potion = pot.down1;
        Entity spikes = new OBJ_Spike(gp);
        spike = spikes.down1;





    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(Fortzilla);
        g2.setColor(Color.white);

        //-----------------> Title STATE
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //-----------------> PLAY STATE
        if (gp.gameState == gp.playState){
            drawPlayerLife();
            Inventory();

            Inventory2();
            drawPlayer2Life();
        }
        //-----------------> PAUSE STATE
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //-----------------> DIALOGUE STATE
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        //-----------------> DIALOGUE STATE
        if (gp.gameState == gp.optionState){
            drawOptionsScreen();
        }
        //-----------------> GAME OVER STATE
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        //-----------------> HELP STATE PAGE 1
        if (gp.gameState == gp.helpState){
            drawHelpScreen();
        }
        //-----------------> HELP STATE PAGE 2
        if (gp.gameState == gp.helpState_Page2){
            drawHelpScreen_Page2();
        }

        if (messageOn){
            drawSubWindow(410, gp.tileSize, 400, 50);
            g2.setFont(g2.getFont().deriveFont(16f));
            g2.drawString(message, 430, gp.tileSize+33);

            messageCounter++;
            if (messageCounter > 120){
                messageCounter=0;
                messageOn=false;
            }
        }
    }

    private void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;

        String text;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "Game Over";
        //shadow
        g2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x-40, y);
        }

        //back to title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x-40, y);
        }

    }

    private void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));

        //sub window
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //sub state
        switch (subState){
            case 0: options_top(frameX, frameY);
                break;
            case 1: options_fullScreenNotification(frameX, frameY);
                break;
            case 2: options_control(frameX, frameY);
                break;
            case 3: options_endgameConfirmation(frameX, frameY);
                break;
        }

        gp.keyH.enterPressed = false;
    }

    private void options_endgameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Do you want to quit \nthe game and go back \nto title screen?";

        for (String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY+=40;
        }

        //yes
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        //no
        text = "No";
        textX = getXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 5;
            }
        }
    }

    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24f));

        //title
        String text = "OPTIONS";
        textX = frameX + gp.tileSize*3;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;

        //music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 2){
            g2.drawString(">", textX-25, textY);
        }

        //sound effect
        textY += gp.tileSize;
        g2.drawString("Sound Effects", textX, textY);
        if (commandNum == 3){
            g2.drawString(">", textX-25, textY);
        }

        //control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 4){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }

        //end game
        textY += gp.tileSize;
        g2.drawString("Quit Game", textX, textY);
        if (commandNum == 5){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        //back
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 6){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        textX = frameX +gp.tileSize*5;
        textY = (int) (frameY + gp.tileSize*1.5);

        //music volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //sound effects volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.soundEffects.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);


    }

    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        //title
        String text = "Control";
        textX = frameX + gp.tileSize*3;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Player Controls ONE", textX, textY);

        textY += gp.tileSize;
        g2.drawString("-> WASD", textX, textY);


        textY+=gp.tileSize;
        g2.drawString("Player Controls TWO", textX, textY);
        textY += gp.tileSize;
        g2.drawString("-> IJKL", textX, textY);


        textY+=gp.tileSize;
        g2.drawString("VilLain Controls", textX, textY);

        textY += gp.tileSize;
        g2.drawString("-> UP, DOWN,", textX, textY);
        textY += gp.tileSize;
        g2.drawString("-> LEFT, RIGHT", textX, textY);

        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }

    }

    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        for (String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY+=40;
        }

        //back
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed){
                subState = 0;
            }
        }
    }

    public void Inventory(){
        drawSubWindow(650, 1, 500, 50);
        int x = gp.tileSize*16;
        int y = gp.tileSize;
//        int i = 0;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.setColor(Color.white);
        String text = "Inventory:";
        g2.drawString(text, 310, 35);

        //slot
        final int slotXstart = (int) (gp.tileSize*8.5);
        final int slotYstart = gp.tileSize/5;
        int slotx = slotXstart;
        int sloty = slotYstart;

        //player items
        for (int i = 0; i<gp.player.inventory.size();  i++){
            g2.drawImage(gp.player.inventory.get(i).down1, slotx, sloty+3, null);
            slotx += gp.tileSize/3;
        }

    }
    public void Inventory2(){
        drawSubWindow(650, 1, 500, 50);
        int x = gp.tileSize*16;
        int y = gp.tileSize;
//        int i = 0;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.setColor(Color.white);
        String text = "Inventory:";
        g2.drawString(text, 913, 35);


        //slot
        final int slotXstart = (int) (gp.tileSize*21.2);
        final int slotYstart = gp.tileSize/5;
        int slotx = slotXstart;
        int sloty = slotYstart;

        //player items
        for (int i = 0; i<gp.player2.inventory.size();  i++){
            g2.drawImage(gp.player2.inventory.get(i).down1, slotx, sloty+3, null);
            slotx += gp.tileSize/3;
        }

    }

    public int getItemIndexOnSlot(){
        return slotCol + (slotRow*5);
    }

    public void drawPlayerLife(){

        drawSubWindow(45, 1, 500, 50);

        int x = (int) (gp.tileSize*3.3);
        int y = gp.tileSize/5;
        int i = 0;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.setColor(Color.white);
        String text = "Player One";
        g2.drawString(text, 60, 35);

        //draw max life
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //reset
        x = (int) (gp.tileSize*3.3);
        y = gp.tileSize/5;
        i=0;

        //draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPlayer2Life(){

        int x = (int) (gp.tileSize*16);
        int y = gp.tileSize/5;
        int i = 0;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.setColor(Color.white);
        String text = "Player Two";
        g2.drawString(text, 660, 35);

        //draw max life
        while(i < gp.player2.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //reset
        x = (int) (gp.tileSize*16);
        y = gp.tileSize/5;
        i=0;

        //draw current life
        while(i < gp.player2.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player2.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawHelpScreen(){
        //background
        g2.setColor(new Color(195, 169, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "Game Instructions";
        int x = getXForCenteredText(text);
        int y = gp.tileSize;
        //shadow color
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);


        text = "The main goal of this game is to get the hidden treasure of the dungeon\n"+
                "The dungeon has five levels, each has different difficulties and obstacle\n" +
                "find way to get to the final level and collect the treasure!";
        drawInstructions(26, 80, 100, text, 40);

        text="Player 1 Key inputs:\n" +
                "[W] Move up,  [A] Move left,  [S] Move down,  [D] Move Right\n\n" +
                "Player 2 Key inputs:\n" +
                "[I] Move up,  [J] Move left,  [K] Move down,  [L] Move Right\n\n" +
                "Monster Key inputs:\n" +
                "ArrowUp,  ArrowLeft,  ArrowDown,  ArrowLeft\n";
        drawInstructions(26, 180, 250, text, 45);

        //player image
        y = 200;
        g2.drawImage(gp.player.down1, 70, y, gp.tileSize*2, gp.tileSize*2, null);
        y += gp.tileSize*3;
        g2.drawImage(gp.player2.down1, 70, y, gp.tileSize*2, gp.tileSize*2, null);
        y += gp.tileSize*3;
        g2.drawImage(gp.villain.down1, 70, y, gp.tileSize*2, gp.tileSize*2, null);

        y += gp.tileSize*2.5;
        g2.drawImage(gp.menuObj.next, 700, y, gp.tileSize, gp.tileSize, null);
        if (commandNum == 0){
            g2.drawImage(gp.menuObj.nextA, 700, y, gp.tileSize, gp.tileSize, null);
        }
        g2.drawImage(gp.menuObj.back, 450, y, gp.tileSize, gp.tileSize, null);
        if (commandNum == 1){
            g2.drawImage(gp.menuObj.backA, 450, y, gp.tileSize, gp.tileSize, null);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        text = "Go back";
        g2.drawString(text, 440, y+75);
        text = "Next Page";
        g2.drawString(text, 680, y+75);


    }

    private void drawHelpScreen_Page2() {
        g2.setColor(new Color(195, 169, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "Game Items and Traps";
        int x = getXForCenteredText(text);
        int y = gp.tileSize;
        //shadow color
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text="Teleports to other area\n\n" +
                "Decreases players life\n\n"+
                "Adds half life\n\n" +
                "Player speed increases\n\n";
        drawInstructions(24, 240, 155, text, 35);

        text= "Player speed decreases\n\n" +
                "Key for \n\n" +
                "Key for \n\n" +
                "Key for \n\n";
        drawInstructions(24, 680, 155, text, 35);

        //player image
        y = 120;
        g2.drawImage(tel, 180, y, gp.tileSize, gp.tileSize, null);
        y += gp.tileSize*1.5;
        g2.drawImage(spike, 180, y-15, gp.tileSize, gp.tileSize, null);
        y += gp.tileSize*1.5;
        g2.drawImage(potion, 180, y, gp.tileSize, gp.tileSize, null);
        y += gp.tileSize*1.5;
        g2.drawImage(boots, 180, y, gp.tileSize, gp.tileSize, null);

        y = 120;
        g2.drawImage(goo, 620, y, gp.tileSize, gp.tileSize, null);
        y += gp.tileSize*1.5;
        g2.drawImage(keySil, 620, y, gp.tileSize, gp.tileSize, null);
        g2.drawImage(doorSil, 790, y, gp.tileSize, gp.tileSize, null);
        y += gp.tileSize*1.5;
        g2.drawImage(keyGold, 620, y, gp.tileSize, gp.tileSize, null);
        g2.drawImage(doorGold, 790, y, gp.tileSize, gp.tileSize, null);
        y += gp.tileSize*1.5;
        g2.drawImage(keyAmy, 620, y, gp.tileSize, gp.tileSize, null);
        g2.drawImage(doorAmy, 790, y, gp.tileSize, gp.tileSize, null);

        text= "SOME KEY INSTRUCTIONS";
        drawInstructions(24, 180, 455, text, 35);

        text= "*To pause the game, click ESC key.\n" +
                "*To play the game again after pausing, re-click ESC key.\n" +
                "*To access game menu during play state, click SHIFT key.\n";
        drawInstructions(24, 250, 490, text, 35);


        y = 500;
        y += gp.tileSize*2.3;
        g2.drawImage(gp.menuObj.back, 570, y, gp.tileSize, gp.tileSize, null);
        if (commandNum == 1) {
            g2.drawImage(gp.menuObj.backA, 570, y, gp.tileSize, gp.tileSize, null);
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        text = "Go back";
        g2.drawString(text, 560, y+75);
    }

    public void drawInstructions(float fontSize, int x, int y, String text, int lineHeight){
        g2.setColor(new Color(47, 76, 120));
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line: text.split("\n")){
            g2.drawString(line, x, y);
            y += lineHeight;
        }
    }

    private void drawTitleScreen() {
        //background
        g2.setColor(new Color(195, 169, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Cat Treasure Hunt";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;
        //shadow color
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //player image
        x = (gp.screenWidth/2)- (gp.tileSize*6)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
        g2.drawImage(gp.player2.down1, x+(x/2), y, gp.tileSize*2, gp.tileSize*2, null);
        g2.drawImage(gp.villain.down1, x+(x/2)/2, y, gp.tileSize*2, gp.tileSize*2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.setColor(new Color(47, 76, 120));

        y+=gp.tileSize*5;
        g2.drawImage(gp.menuObj.playMenu, x+50, y-35, gp.tileSize*4, gp.tileSize+10, null);
        if (commandNum == 1){
            g2.drawImage(gp.menuObj.playOn, x+50, y-35, gp.tileSize*4, gp.tileSize+10, null);
        }
        text = "Play Game";
        g2.drawString(text, x+65, y+5);


        y+=gp.tileSize*1.5;
        g2.drawImage(gp.menuObj.helpMenu, x+50, y-(y/35)*2, gp.tileSize*4, gp.tileSize+10, null);
        if (commandNum == 2){
            g2.drawImage(gp.menuObj.helpOn, x+50, y-(y/35)*2, gp.tileSize*4, gp.tileSize+10, null);
        }
        text = "Help";
        g2.drawString(text, x+115, y+10);


        y+=gp.tileSize*2.5;
        g2.drawImage(gp.menuObj.quitMenu, x+50, y-(y/35)*4, gp.tileSize*4, gp.tileSize+10, null);
        if (commandNum == 3){
            g2.drawImage(gp.menuObj.quitOn, x+50, y-(y/35)*4, gp.tileSize*4, gp.tileSize+10, null);
        }
        text = "Quit";
        g2.drawString(text, x+115, y-30);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,12F));
        g2.setColor(Color.white);
        text = "Kolehiyo ng Lungsod ng Lipa | CS4D | 2023";
        g2.drawString(text, 930, 230*3);
        text = "Kathleen";
        g2.drawString(text, 20, 230*3);
        text = "Cyree";
        g2.drawString(text, 75, 230*3);
        text = "Adonis";
        g2.drawString(text, 112, 230*3);
        text = "Alexis";
        g2.drawString(text, 152, 230*3);
        text = "Christian";
        g2.drawString(text, 187, 230*3);

    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        //-----------------> DIALOGUE WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize + (gp.tileSize*5);
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*2;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y +=40;
        }
    }

    public void drawWinScreen(){
        //-----------------> DIALOGUE WINDOW
        int x = gp.tileSize*4;
        int y = gp.tileSize + (gp.tileSize*4);
        int width = gp.screenWidth - (gp.tileSize*9);
        int height = gp.tileSize*6;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        y += gp.tileSize;
        String text = "Dear adventurers,";
        g2.drawString(text, x, y);

        y += gp.tileSize;
        text = "After so many hardships, you have reached the end of this dungeon.";
        g2.drawString(text, x, y);

        y += gp.tileSize;
        text = "You found the treasure! However, your adventure has just begun.";
        g2.drawString(text, x, y);


    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x,y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); //defines the width of outlines of graphics
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
}
