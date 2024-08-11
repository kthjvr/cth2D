package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp){
        super(gp);

        UtilityTool uTool = new UtilityTool();

        name = "Boots";
        spriteNum = 1;
        direction = "down";
        down1 = setup("/objects/boots");

        down1 = uTool.scaleImage(down1, gp.tileSize*2/3, gp.tileSize*2/3);
    }
}
