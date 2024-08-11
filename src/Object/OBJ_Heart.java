package Object;

import Entity.Entity;
import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);

        UtilityTool uTool = new UtilityTool();

        name = "Heart";
        direction = "down";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");
        image = uTool.scaleImage(image, gp.tileSize*2/3, gp.tileSize*2/3);
        image2 = uTool.scaleImage(image2, gp.tileSize*2/3, gp.tileSize*2/3);
        image3 = uTool.scaleImage(image3, gp.tileSize*2/3, gp.tileSize*2/3);
    }
}
