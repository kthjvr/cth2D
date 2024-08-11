package Object;

import Entity.Entity;
import Main.GamePanel;


public class OBJ_Door_Amethyst extends Entity {

    public OBJ_Door_Amethyst(GamePanel gp){
        super(gp);
        name = "DoorAmethyst";
        down1 = setup("/objects/door_amethyst");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
