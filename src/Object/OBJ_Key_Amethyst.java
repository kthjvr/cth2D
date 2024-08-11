package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;

public class OBJ_Key_Amethyst extends Entity {

    public OBJ_Key_Amethyst(GamePanel gp){
        super(gp);

        UtilityTool uTool = new UtilityTool();

        direction = "down";
        name = "KeyAmethyst";
        down1 = setup("/objects/key_amethyst");
        down1 = uTool.scaleImage(down1, gp.tileSize*2/3, gp.tileSize*2/3);
    }
}
