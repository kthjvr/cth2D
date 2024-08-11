package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class tileManager {

    GamePanel gp;
    public tile[] tile;

    public int[][][] mapTileNum;

    public tileManager(GamePanel gp){
        this.gp = gp;
        tile = new tile[60];

        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow]; //put all the numbers in map into arrays

        getTileImage();
        loadMap("/maps/map1", 0);
        loadMap("/maps/map2", 1);
        loadMap("/maps/map3", 2);
        loadMap("/maps/map4", 3);
        loadMap("/maps/map5", 4);
    }

    public void getTileImage(){
        setup(0, "blue", true);
        setup(1, "blue", false);
        setup(2, "blue", false);
        setup(3, "blue", false);
        setup(4, "blue", false);
        setup(5, "blue", false);
        setup(6, "blue", false);
        setup(7, "blue", false);
        setup(8, "blue", false);
        setup(9, "blue", false);

        setup(10, "10", false);
        setup(11, "11", false);

        setup(12, "12", false);
        setup(13, "13", false);
        setup(14, "14", false);
        setup(15, "15", false);
        setup(16, "16", false);
        setup(17, "17", true);
        setup(18, "18", true);
        setup(19, "19", true);
        setup(20, "20", true);

        setup(21, "21", false);
        setup(22, "00", true);
        setup(23, "00", true);
        setup(24, "24", false);
        setup(25, "25", true);
        setup(26, "26", true);
        setup(27, "27", true);
        setup(28, "28", true);
        setup(29, "29", true);
        setup(30, "30", true);
        setup(31, "31", false);
        setup(32, "32", false);
        setup(33, "33", false);
    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" +imageName+ ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath, int map){
        try {
            InputStream is = getClass().getResourceAsStream(filePath); //load txt file or map
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //read the txt file

            int col =0;
            int row=0;

            while (col<gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine(); //read a single line of text

                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" "); //splits the string around matches regular expression //split a string at a space
                    int num = Integer.parseInt(numbers[col]); //convert string to integer

                    mapTileNum[map][col][row] = num;
                    col++;

                } if (col == gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();

        }catch (Exception ignored){
        }
    }

    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[gp.currentMap][col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol){ //this is for mapping, if the column reaches the number of max screen then proceed to next row
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }


    }
}
