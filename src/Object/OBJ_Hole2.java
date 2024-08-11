package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Hole2 extends Entity {


    public OBJ_Hole2(GamePanel gp){
        super(gp);
        direction = "down";
        name = "Hole2";
        down1 = setup("/objects/portal2");
    }
}
