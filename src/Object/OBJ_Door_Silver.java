package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door_Silver extends Entity {


    public OBJ_Door_Silver(GamePanel gp){
        super(gp);
        name = "DoorSilver";
        direction = "down";
        down1 = setup("/objects/door_silver");
        collision = true;
    }
}
