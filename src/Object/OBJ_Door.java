package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;


public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "DoorGold";
        down1 = setup("/objects/door_golden");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
