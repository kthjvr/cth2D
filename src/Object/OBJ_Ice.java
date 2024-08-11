package Object;

import Entity.Entity;
import Main.GamePanel;


public class OBJ_Ice extends Entity {

    public OBJ_Ice(GamePanel gp){
        super(gp);
        name = "Ice";
        down1 = setup("/objects/Ice");

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
