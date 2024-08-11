package Object;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class OBJ_Ruby extends Entity {

    GamePanel gp;

    public OBJ_Ruby(GamePanel gp){
        super(gp);
        this.gp = gp;
        direction = "down";
        name = "Ruby";
        down1 = setup("/objects/Ruby");
    }

}
