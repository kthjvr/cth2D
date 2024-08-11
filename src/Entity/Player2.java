package Entity;

import Main.GamePanel;
import Main.KeyHandler2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import Object.OBJ_Key;
import Object.OBJ_Key_Silver;
import Object.OBJ_Key_Amethyst;

public class Player2 extends Entity{

    KeyHandler2 keyH2;

    public int hasKeyGold = 0;
    public int hasKeySilver = 0;
    public int hasKeyAmethyst = 0;

    public ArrayList<Entity> inventory = new ArrayList<>();

    public Player2(GamePanel gp, KeyHandler2 keyH2){
        super(gp);
        this.keyH2 = keyH2;

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
        worldX = gp.tileSize * 4;
        worldY = gp.tileSize * 3;
        playerSpeed = 4;
        direction = "down";

        //player life state
        maxLife = 6;
        life = maxLife;

        playerNumber = 2;
    }
    public void setDefaultPosition(){
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 3;
        playerSpeed = 4;
        direction = "down";
    }
    public void restoreLife(){
        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage(){
            up1 = setup("/cat3/u1");
            up2 = setup("/cat3/u2");
            up3 = setup("/cat3/u3");
            down1 = setup("/cat3/d1");
            down2 = setup("/cat3/d2");
            down3 = setup("/cat3/d3");
            left1 = setup("/cat3/l1");
            left2 = setup("/cat3/l2");
            left3 = setup("/cat3/l3");
            right1 = setup("/cat3/r1");
            right2 = setup("/cat3/r2");
            right3 = setup("/cat3/r3");
    }

    public void getPlayerDieImage(){
        up1 = setup("/catDie/u1");
        up2 = setup("/catDie/u2");
        up3 = setup("/catDie/u3");
        down1 = setup("/catDie/d1");
        down2 = setup("/catDie/d2");
        down3 = setup("/catDie/d3");
        left1 = setup("/catDie/l1");
        left2 = setup("/catDie/l2");
        left3 = setup("/catDie/l3");
        right1 = setup("/catDie/r1");
        right2 = setup("/catDie/r2");
        right3 = setup("/catDie/r3");
    }
    public void update(){
        if (keyH2.upPressed2 || keyH2.downPressed2 || keyH2.leftPressed2 || keyH2.rightPressed2) {
            if (keyH2.upPressed2) {
                direction = "up";
            } else if (keyH2.downPressed2) {
                direction = "down";
            } else if (keyH2.leftPressed2) {
                direction = "left";
            } else {
                direction = "right";
            }

            if (gp.player2.life <= 0){
                invincible = true;
            }

            //--------> CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //---------> CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            if(life >= 1){
                pickupObject(objIndex);
            }

            //---------> CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.villain);
            int monsterIndex2 = gp.cChecker.checkEntity(this, gp.villain2);
            int monsterIndex3 = gp.cChecker.checkEntity(this, gp.villain3);
            if(life >= 1){
                contactMonster(monsterIndex);
                contactMonster(monsterIndex2);
                contactMonster(monsterIndex3);
            }

//            gp.cChecker.checkEntity(this, gp.player);


            //---------> CHECK EVENT
            if(life >= 1){
                gp.eHandler.checkEvent2();
            }


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
        }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (gp.player.life <= 0 && gp.player2.life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(6);
        }
    }

    private void contactMonster(int i) {
        if (i != 999){
            if (!invincible){
                life -= 1;
                invincible = true;
                gp.playSE(2);
            }
        }
    }

    public void pickupObject(int i){
        if (i != 999){
            String objectName = gp.obj[gp.currentMap][i].name;
            int itemIndex = gp.ui.getItemIndexOnSlot();

            switch (objectName){
                case "KeySilver":
                    gp.playSE(1);
                    hasKeySilver++;
                    inventory.add(new OBJ_Key_Silver(gp));
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Player 2 got the silver key!");
                    break;
                case "KeyGold":
                    gp.playSE(1);
                    hasKeyGold++;
                    inventory.add(new OBJ_Key(gp));
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Player 2 got the gold key!");
                    break;
                case "KeyAmethyst":
                    gp.playSE(1);
                    hasKeyAmethyst++;
                    inventory.add(new OBJ_Key_Amethyst(gp));
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Player 2 got the amethyst key!");
                    break;
                case "DoorGold":
                    if (hasKeyGold > 0){
                        gp.playSE(3);
                        inventory.remove(itemIndex);
                        gp.obj[gp.currentMap][i] = null;
                        hasKeyGold--;
                        gp.ui.showMessage("Player 2 opened the golden door!");
                    } else {
                        gp.playSE(12);
                        gp.ui.showMessage("You don't have the key!");
                    }
                    break;
                case "DoorSilver":
                    if (hasKeySilver > 0){
                        gp.playSE(3);
                        inventory.remove(itemIndex);
                        gp.obj[gp.currentMap][i] = null;
                        hasKeySilver--;
                        gp.ui.showMessage("Player 2 opened the silver door!");
                    } else {
                        gp.playSE(12);
                        gp.ui.showMessage("You don't have the key!");
                    }
                    break;
                case "DoorAmethyst":
                    if (hasKeyAmethyst > 0){
                        gp.playSE(3);
                        inventory.remove(itemIndex);
                        gp.obj[gp.currentMap][i] = null;
                        hasKeyAmethyst--;
                        gp.ui.showMessage("Player 2 opened the amethyst door!");
                    } else {
                        gp.playSE(12);
                        gp.ui.showMessage("You don't have the key!");
                    }
                    break;
                case "Ruby":
                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;
                    gp.player.hasRuby--;
                    if (gp.player.hasRuby==0){
                        gp.gameState = gp.cutscene;
                        gp.csManager.sceneNum = gp.csManager.ending;
                        gp.ui.showMessage("Player 2 obtained the treasure!");
                    }
                    break;
                case "Potion":
                    gp.playSE(8);
                    gp.obj[gp.currentMap][i] = null;
                    life+=1;
                    gp.ui.showMessage("Player 2 obtained health potion!");
                    break;
                case "Spike":
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Player 2 step on a spike! Life -1!");
                    break;
                case "Boots":
                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;
                    playerSpeed++;
                    gp.ui.showMessage("Player 2 obtained buff! +1 increase in speed!");
                    break;
                case "Goo":
                    gp.playSE(11);
                    gp.obj[gp.currentMap][i] = null;
                    playerSpeed--;
                    gp.ui.showMessage("Player 2 step on a goo! Speed -1!");
                    break;
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
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if (gp.player2.life == 0){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
