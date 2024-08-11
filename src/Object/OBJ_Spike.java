package Object;

import Entity.Entity;
import Main.GamePanel;


public class OBJ_Spike extends Entity {

    public OBJ_Spike(GamePanel gp){
        super(gp);
        name = "Spike";
        down1 = setup("/objects/Spikes_Active");

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
