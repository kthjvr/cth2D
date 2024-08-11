package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Goo extends Entity {

    public OBJ_Goo(GamePanel gp){
        super(gp);
        direction = "down";
        name = "Goo";
        down1 = setup("/objects/Goo");
    }
}
