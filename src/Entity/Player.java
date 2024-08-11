package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Object.OBJ_Key;
import Object.OBJ_Key_Silver;
import Object.OBJ_Key_Amethyst;

public class Player extends Entity{

    KeyHandler keyH;

    public int hasKeyGold = 0;
    public int hasKeySilver = 0;
    public int hasKeyAmethyst = 0;
    int hasRuby = 2;
    public ArrayList<Entity> inventory = new ArrayList<>();



    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

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
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 3;
        playerSpeed = 4;
        direction = "down";

        //player life state
        maxLife = 6;
        life = maxLife;

        playerNumber = 1;
    }

    public void setDefaultPosition(){
        worldX = gp.tileSize * 4;
        worldY = gp.tileSize * 3;
        playerSpeed = 4;
        direction = "down";
    }

    public void restoreLife(){
        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage(){
        up1 = setup("/cat1/u1");
        up2 = setup("/cat1/u2");
        up3 = setup("/cat1/u3");
        down1 = setup("/cat1/d1");
        down2 = setup("/cat1/d2");
        down3 = setup("/cat1/d3");
        left1 = setup("/cat1/l1");
        left2 = setup("/cat1/l2");
        left3 = setup("/cat1/l3");
        right1 = setup("/cat1/r1");
        right2 = setup("/cat1/r2");
        right3 = setup("/cat1/r3");

    }

    public void update(){
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            if (gp.player.life <= 0){
                invincible = true;
            }
            
            //--------> CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //---------> CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            if(life >= 0){
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

//            gp.cChecker.checkEntity(this, gp.player2);

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
                    gp.ui.showMessage("Player 1 got the silver key!");
                    break;
                case "KeyGold":
                    gp.playSE(1);
                    hasKeyGold++;
                    inventory.add(new OBJ_Key(gp));
                    gp.obj[gp.currentMap][i] = null;
                    System.out.println("Gold Key: "+hasKeyGold);
                    gp.ui.showMessage("Player 1 got the gold key!");
                    break;
                case "KeyAmethyst":
                    gp.playSE(1);
                    hasKeyAmethyst++;
                    inventory.add(new OBJ_Key_Amethyst(gp));
                    gp.obj[gp.currentMap][i] = null;
                    System.out.println("Amethyst Key: "+hasKeyAmethyst);
                    gp.ui.showMessage("Player 1 got the amethyst key!");
                    break;
                case "DoorGold":
                    if (hasKeyGold > 0){
                        gp.playSE(3);
                        inventory.remove(itemIndex);
                        gp.obj[gp.currentMap][i] = null;
                        hasKeyGold--;
                        gp.ui.showMessage("Player 1 opened the golden door!");
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
                        gp.ui.showMessage("Player 1 opened the silver door!");
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
                        gp.ui.showMessage("Player 1 opened the amethyst door!");
                    } else {
                        gp.playSE(12);
                        gp.ui.showMessage("You don't have the key!");
                    }
                    break;
                case "Ruby":
                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;
                    hasRuby--;
                    if (hasRuby==0){
                        gp.gameState = gp.cutscene;
                        gp.csManager.sceneNum = gp.csManager.ending;
                        gp.ui.showMessage("Player 1 obtained the treasure!");
                    }
                    break;
                case "Potion":
                    gp.playSE(8);
                    gp.obj[gp.currentMap][i] = null;
                    life+=1;
                    gp.ui.showMessage("Player 1 obtained health potion!");
                    break;
                case "Spike":
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Player 1 step on a spike! Life -1!");
                    break;
                case "Boots":
                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;
                    playerSpeed++;
                    gp.ui.showMessage("Player 1 obtained buff! +1 increase in speed!");
                    break;
                case "Goo":
                    gp.playSE(11);
                    gp.obj[gp.currentMap][i] = null;
                    playerSpeed--;
                    gp.ui.showMessage("Player 1 step on a goo! Speed -1!");
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
        if (gp.player.life == 0){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //debug
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible"+invincibleCounter, 10, 400);
    }
}
