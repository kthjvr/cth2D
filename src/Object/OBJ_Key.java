package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){
        super(gp);

        UtilityTool uTool = new UtilityTool();

        name = "KeyGold";
        spriteNum = 1;
        direction = "down";
        down1 = setup("/objects/key_golden");

        down1 = uTool.scaleImage(down1, gp.tileSize*2/3, gp.tileSize*2/3);
    }
}
