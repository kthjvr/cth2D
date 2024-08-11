package Main;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol){
                col = 0;
                row++;

                if (row == gp.maxWorldRow){
                    row =0;
                    map++;
                }
            }
        }

    }

    public void checkEvent(){

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldX - previousEventX);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }

        if (canTouchEvent){
            if (hit(4, 6, 5, "any")){
                damagePit(6, 5);
            }
            else if (hit(4, 9, 1, "any")){
                damagePit(9, 1);
            }
            else if (hit(4, 14, 9, "any")){
                damagePit(14, 9);
            }
            else if (hit(4, 22, 11, "any")){
                damagePit(22, 11);
            }
            else if (hit(4, 20, 1, "any")){
                damagePit(20, 1);
            }
            else if (hit2(0, 23, 13, "any") && hit(0, 23, 13, "any")){
                teleportMap(1, 1, 13);
                teleportMap2(1, 1, 13);
            }
            else if (hit2(1, 18, 2, "any") && hit(1, 18, 2, "any")){
                teleportMap(2, 11, 13);
                teleportMap2(2, 12, 13);
            }
            else if (hit2(2, 22, 10, "any") && hit(2, 22, 10, "any")){
                teleportMap(3, 11, 13);
                teleportMap2(3, 12, 13);
            }
            else if (hit2(3, 14, 4, "any") && hit(3, 14, 4, "any")){
                teleportMap(4, 23, 7);
                teleportMap2(4, 23, 6);
            }
        }

        if (hit(0, 6, 8, "right")){
            gp.playSE(4);
            teleport();
        }
        if (hit(0, 19, 1, "right")){
            gp.playSE(4);
            teleport2();
        }
        if (hit(0, 1, 13, "left")){
            gp.playSE(4);
            teleport3();
        }
    }



    public void checkEvent2(){
        int xDistance = Math.abs(gp.player2.worldX - previousEventX);
        int yDistance = Math.abs(gp.player2.worldX - previousEventX);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }

        if (canTouchEvent){
            if (hit2(4, 6, 5, "any")){
                p2damagePit(6, 5);
            }
            else if (hit2(4, 9, 1, "any")){
                p2damagePit(9, 1);
            }
            else if (hit2(4, 14, 9, "any")){
                p2damagePit(14, 9);
            }
            else if (hit2(4, 22, 11, "any")){
                p2damagePit(22, 11);
            }
            else if (hit2(4, 20, 1, "any")){
                p2damagePit(20, 1);
            }
            else if (hit2(0, 23, 13, "any") && hit(0, 23, 13, "any")){
                teleportMap(1, 1, 13);
                teleportMap2(1, 1, 13);
            }
            else if (hit2(1, 18, 2, "any") && hit(1, 18, 2, "any")){
                teleportMap(2, 11, 13);
                teleportMap2(2, 12, 13);
            }
            else if (hit2(2, 22, 10, "any") && hit(2, 22, 10, "any")){
                teleportMap(3, 11, 13);
                teleportMap2(3, 12, 13);
            }
            else if (hit2(3, 22, 13, "any") && hit(3, 22, 13, "any")){
                teleportMap(4, 23, 6);
                teleportMap2(4, 23, 8);
            }
        }

        if (hit2(0, 6, 8, "right")){
            gp.playSE(4);
            p2teleport();
        }
        if (hit2(0, 19, 1, "right")){
            gp.playSE(4);
            p2teleport2();
        }
        if (hit2(0, 1, 13, "left")){
            gp.playSE(4);
            p2teleport3();
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;

        if (map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                if (gp.player.direction.contentEquals(reqDirection)  || reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }

    public boolean hit2(int map, int col, int row, String reqDirection){
        boolean hit = false;

        if (map == gp.currentMap){
            gp.player2.solidArea.x = gp.player2.worldX + gp.player2.solidArea.x;
            gp.player2.solidArea.y = gp.player2.worldY + gp.player2.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player2.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                if (gp.player2.direction.contentEquals(reqDirection)  || reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player2.worldX;
                    previousEventY = gp.player2.worldY;
                }
            }

            gp.player2.solidArea.x = gp.player2.solidAreaDefaultX;
            gp.player2.solidArea.y = gp.player2.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }
        return hit;
    }

    //----------> EVENTS

    //----->TELEPORT
    public void teleport(){
        gp.player.worldX = gp.tileSize*18;
        gp.player.worldY = gp.tileSize;
    }
    public void teleport2(){
        gp.player.worldX = gp.tileSize;
        gp.player.worldY = gp.tileSize*13;
    }
    public void teleport3(){
        gp.player.worldX = gp.tileSize*6;
        gp.player.worldY = gp.tileSize*8;
    }
    public void p2teleport(){
        gp.player2.worldX = gp.tileSize*18;
        gp.player2.worldY = gp.tileSize;
    }
    public void p2teleport2(){
        gp.player2.worldX = gp.tileSize;
        gp.player2.worldY = gp.tileSize*13;
    }
    public void p2teleport3(){
        gp.player2.worldX = gp.tileSize*6;
        gp.player2.worldY = gp.tileSize*8;
    }

    public void teleportMap(int map, int col, int row){
        gp.currentMap = map;
        gp.villain.setDefaultPosition();
        gp.villain.playerSpeed +=1;
        gp.villain2.playerSpeed +=1;
        gp.villain3.playerSpeed +=1;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        gp.player.playerSpeed = 4;
        gp.player2.playerSpeed = 4;
        canTouchEvent = false;
        gp.playSE(7);
    }
    public void teleportMap2(int map, int col, int row){
        gp.currentMap = map;
        gp.villain.playerSpeed +=1;
        gp.villain2.playerSpeed +=1;
        gp.villain3.playerSpeed +=1;
        gp.villain.setDefaultPosition();
        gp.player2.worldX = gp.tileSize * col;
        gp.player2.worldY = gp.tileSize * row;
        previousEventX = gp.player2.worldX;
        previousEventY = gp.player2.worldY;
        gp.player.playerSpeed = 4;
        gp.player2.playerSpeed = 4;
        canTouchEvent = false;
        gp.playSE(7);
    }
    //----->DAMAGE PIT
    public void damagePit(int col, int row){
        gp.playSE(2);
        gp.player.life-=1;
        canTouchEvent = false;
        eventRect[gp.currentMap][col][row].eventDone = true;
    }
    public void p2damagePit(int col, int row){
        gp.playSE(2);
        gp.player2.life-=1;
        canTouchEvent = false;
        eventRect[gp.currentMap][col][row].eventDone = true;
    }

}
