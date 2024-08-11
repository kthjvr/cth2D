package Entity;

import Main.GamePanel;
import Main.KeyHandler3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Villain2 extends Entity {

    KeyHandler3 keyH3;

    public Villain2(GamePanel gp){
        super(gp);

        solidArea = new Rectangle(); //for setting a solid area on player character
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 11;
        playerSpeed = 6;
        direction = "down";

        playerNumber = 3;
    }

    public void setDefaultPosition(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 11;
        playerSpeed = 6;
        direction = "down";
    }

    public void getPlayerImage(){
        up1 = setup("/villain2/u1");
        up2 = setup("/villain2/u2");
        up3 = setup("/villain2/u3");
        down1 = setup("/villain2/d1");
        down2 = setup("/villain2/d2");
        down3 = setup("/villain2/d3");
        left1 = setup("/villain2/l1");
        left2 = setup("/villain2/l2");
        left3 = setup("/villain2/l3");
        right1 = setup("/villain2/r1");
        right2 = setup("/villain2/r2");
        right3 = setup("/villain2/r3");
    }

    public void update(){

        setAction();

            //--------> CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //---------> CHECK MONSTER COLLISION
            int killPlayer1 = gp.cChecker.checkEntity(this, gp.player);
            hitPlayer1(killPlayer1);
            int killPlayer2 = gp.cChecker.checkEntity(this, gp.player2);
            hitPlayer2(killPlayer2);

            //--------> IF COLLISION IS FALSE PLAYER CAN MOVE
            if (!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= playerSpeed;
                    case "down" -> worldY += playerSpeed;
                    case "left" -> worldX -= playerSpeed;
                    case "right" -> worldX += playerSpeed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 5){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter == 30){
            Random random = new Random();
            int i = random.nextInt(100)+1; //get random number from 1-100

            if (i <= 25){
                direction = "up";
            }
            if (i > 25){
                direction = "down";
            }
            if (i>50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    private void hitPlayer1(int i) {
        if (i != 999){
            if (!invincible){
                gp.player.life -= 1;
                invincible = true;
                gp.playSE(2);
            }

        }
    }

    private void hitPlayer2(int i) {
        if (i != 999){
            if (!invincible){
                gp.player2.life -= 1;
                invincible = true;
                gp.playSE(2);
            }

        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
            }
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
