package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Hole extends Entity {


    public OBJ_Hole(GamePanel gp){
        super(gp);

        direction = "down";
        name = "Hole";
        down1 = setup("/objects/portal3");

    }
}
