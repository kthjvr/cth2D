package Main;
import Entity.Villain;
import Object.OBJ_Key;
import Object.OBJ_Key_Silver;
import Object.OBJ_Key_Amethyst;
import Object.OBJ_Door;
import Object.OBJ_Door_Silver;
import Object.OBJ_Door_Amethyst;
import Object.OBJ_Hole;
import Object.OBJ_Hole2;
import Object.OBJ_Ruby;
import Object.OBJ_Spike;
import Object.OBJ_Potion;
import Object.OBJ_Boots;
import Object.OBJ_Goo;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 4 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18+ gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Hole(gp);
        gp.obj[mapNum][i].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Hole(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Hole2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Hole(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        //-----------> FOR MAP #2
        mapNum = 1;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 4 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 2 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18+ gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;

        //-----------> FOR MAP #3
        mapNum = 2;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18+ gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 3 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 1 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;

        //-----------> FOR MAP #4
        mapNum = 3;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18+ gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Amethyst(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Amethyst(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 2 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = 2 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Goo(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Goo(gp);
        gp.obj[mapNum][i].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Goo(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 3 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;

        //-----------> FOR MAP #5 Final Map
        mapNum = 4;
        gp.obj[mapNum][i] = new OBJ_Ruby(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Ruby(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18+ gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Amethyst(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 3 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][i].worldY = gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door_Silver(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 4 * gp.tileSize-5;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = 18 + gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Amethyst(gp);
        gp.obj[mapNum][i].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key_Silver(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Spike(gp);
        gp.obj[mapNum][i].worldX = 6 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Spike(gp);
        gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][i].worldY = gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Spike(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Spike(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Spike(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Goo(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Goo(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 3 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = 2 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 4 * gp.tileSize+20;
        gp.obj[mapNum][i].weight = (int) (gp.tileSize/1.5);
        gp.obj[mapNum][i].height = (int) (gp.tileSize/1.5);
        i++;

    }

    public void setMonsters(){
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new Villain(gp);
        gp.villain.update();
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        gp.obj[mapNum][i].weight = gp.tileSize;
        gp.obj[mapNum][i].height = gp.tileSize;
        i++;
    }
}
