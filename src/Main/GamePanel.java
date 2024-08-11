package Main;
import Entity.*;
import Tile.tileManager;
import Entity.Villain;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //----------> SCREEN SETTINGS //done
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 25; //the screen width
    public final int maxScreenRow = 15; //the screen height
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //---------> WORLD PANEL
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 15;
    public final int worldWidth = tileSize + maxWorldCol;
    public final int worldHeight = tileSize + maxWorldRow;

    //for full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullscreenOn = false;

    //map
    public final int maxMap = 10;
    public int currentMap = 0;

    //------------> FRAME PER SECOND
    int FPS = 60;


    //----------> SYSTEM
    tileManager tileM = new tileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    KeyHandler2 keyH2 = new KeyHandler2(this);
    KeyHandler3 keyH3 = new KeyHandler3(this);
    MenuObj menuObj = new MenuObj(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public CutSceneManager csManager = new CutSceneManager(this);
    public Sound music = new Sound();
    public Sound soundEffects = new Sound();



    Thread gameThread; //something you can start and stop, keeps your program running until you stop it


    //-----------> Entity and Objects
    public Player player = new Player(this, keyH);
    public Player2 player2 = new Player2(this, keyH2);
    public Villain villain = new Villain(this);
    public Villain2 villain2 = new Villain2(this);
    public Villain3 villain3 = new Villain3(this);

    public Entity[][] obj = new Entity[maxMap][90];
//    public Entity[][] npc = new Entity[maxMap][10];
//    public Entity[][] monster = new Entity[maxMap][5];
    ArrayList<Entity> entityList = new ArrayList<>();


    //---------> Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int optionState = 4;
    public final int gameOverState = 5;
    public final int cutscene = 6;
    public final int helpState = 7;
    public final int helpState_Page2 = 8;



    //------------> Set the size of this class (JPanel) //done
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //improves game rendering performance
        this.setFocusable(true); //with this the game panel can be "focused" to receive input
        this.addKeyListener(keyH); //add the key handler
        this.addKeyListener(keyH2);
        this.addKeyListener(keyH3);
    }

    //-----------> Set Objects
    public void setupGame(){
        aSetter.setObject();
        gameState = titleState;
//        playMusic(0);
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        setFullscreen();
    }

    public void reset(){
        currentMap = 0;
        villain.setDefaultValues();
        villain.setDefaultPosition();
        villain2.setDefaultValues();
        villain2.setDefaultPosition();
        villain3.setDefaultValues();
        villain3.setDefaultPosition();
        player.setDefaultValues();
        player.setDefaultPosition();
        player.restoreLife();
        player2.setDefaultValues();
        player2.setDefaultPosition();
        player2.restoreLife();
        aSetter.setObject();
    }

    //-----------> Set Fullscreen
    public void setFullscreen(){

        //get monitor environment/local screen device size
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        gd.setFullScreenWindow(Main.window);

        //get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    //-----------> Start the thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta>=1){
                update();
//                repaint();
                drawToTempScreen(); //draw everything to the buffered image
                drawToScreen(); //draw the buffered image to the screen
                delta--;
            }

        }
    }

    public void update(){
        if (gameState == playState) {
            player.update();
            player2.update();
            villain.update();
            villain2.update();
            villain3.update();
        }
        if (gameState == pauseState){
            ui.drawPauseScreen();
        }

    }

    public void drawToTempScreen(){
        if (gameState == titleState){
            ui.draw(g2);
        }
        else {
            //tile first before player
            tileM.draw(g2);

            //entitylist
            entityList.add(player);
            entityList.add(player2);
            entityList.add(villain);
            entityList.add(villain2);
            entityList.add(villain3);

            //objects
            for (int i=0; i < obj[1].length; i++){
                if (obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });


            //draw entities
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            //empty entity
            entityList.clear();

            //cutscene
            csManager.draw(g2);

            //draw ui
            ui.draw(g2);
        }
    }

    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        soundEffects.setFile(i);
        soundEffects.play();
    }
}
