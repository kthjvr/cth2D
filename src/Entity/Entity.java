package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Entity {

    //THIS STORES ALL THE VARIABLES THAT WILL BE USED IN PLAYER AND MONSTER

    GamePanel gp;

    public int worldX, worldY;
    public int playerSpeed;
    public int weight, height;
    public BufferedImage up1, up2, up3, down1, down2, down3,
            left1, left2, left3, right1, right2, right3, nextA, playMenu, helpMenu, helpOn, next, backA, back, quitMenu, playOn, quitOn, box, image, image2, image3;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY; //for setting a solid area on player character
    public boolean collisionOn = false;
    public  boolean collision = false;
    public String name;
//    String dialogues[] = new String[20];
//    int dialogueIndex = 0;
//    int dialogueSet = 0;

    public int  actionLockCounter = 0;

    public boolean invincible = false;
    public int invincibleCounter = 0;

    public int playerNumber = 0;

    //character status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void update(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkEntity(this, gp.villain);

        //--------> IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn){
            switch (direction) {
                case "up" -> worldY -= playerSpeed;
                case "down" -> worldY += playerSpeed;
                case "left" -> worldX -= playerSpeed;
                case "right" -> worldX += playerSpeed;
            }
        }

        spriteCounter++;
        if (spriteCounter>12){
            if (spriteNum==1){
                spriteNum=2;
            }
            else if (spriteNum==2){
                spriteNum=1;
            }
            spriteCounter = 0;
        }
    }

//    public void setAction(){}

//    public void speak(){
//        if (dialogues[dialogueIndex] == null){
//            dialogueIndex = 0;
//        }
//
//        gp.ui.currentDialogue = dialogues[dialogueIndex]; //display text
//        dialogueIndex++;
//
//        switch (direction) {
//            case "up" -> direction = "down";
//            case "down" -> direction = "up";
//            case "left" -> direction = "right";
//            case "right" -> direction = "left";
//        }
//    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

//        int screenX = worldX - gp.player.worldX;
//        int screenY = worldY - gp.player.worldY;

//        if (worldX + gp.tileSize > gp.player.worldX&&
//                worldX - gp.tileSize < gp.player.worldX&&
//                worldY + gp.tileSize > gp.player.worldY &&
//                worldY - gp.tileSize < gp.player.worldY ){

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
            }
            g2.drawImage(image, worldX, worldY, weight, height, null);
        }


    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png"))));

//            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }



}
