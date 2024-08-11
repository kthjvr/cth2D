package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Potion extends Entity {

    public OBJ_Potion(GamePanel gp){
        super(gp);
        direction = "down";
        name = "Potion";
        down1 = setup("/objects/potion_red");
    }
}
