package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key_Silver extends Entity {

    public OBJ_Key_Silver(GamePanel gp){
        super(gp);

        UtilityTool uTool = new UtilityTool();

        direction = "down";
        name = "KeySilver";
        down1 = setup("/objects/key_silver");
        down1 = uTool.scaleImage(down1, gp.tileSize*2/3, gp.tileSize*2/3);
    }
}
