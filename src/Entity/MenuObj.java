package Entity;

import Main.GamePanel;

public class MenuObj extends Entity{

    GamePanel gp;

    public MenuObj(GamePanel gp){
        super(gp);

        getMenuImage();
    }

    public void getMenuImage(){
        playMenu = setup("/menu/Button1");
        playOn = setup("/menu/Button2");
        quitMenu = setup("/menu/Button3");
        quitOn = setup("/menu/Button2");
        helpMenu = setup("/menu/Button1");
        helpOn = setup("/menu/Button2");
        next = setup("/menu/next1");
        nextA = setup("/menu/nextA");
        back = setup("/menu/back");
        backA = setup("/menu/backA");
        box = setup("/menu/box");



    }


}
