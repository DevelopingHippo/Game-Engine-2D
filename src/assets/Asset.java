package assets;

import engine.helpers.ReferenceList;

import java.awt.*;

public class Asset {

    public final ReferenceList ref;
    public int worldX = 0, worldY = 0;
    public final String uuid;
    public Asset (ReferenceList ref, String uuid) {
        this.ref = ref;
        this.uuid = uuid;
    }


    public void update() {
        System.out.println("Updating");
    }

    public void draw(Graphics2D g2) {

        System.out.println("Drawing Asset");

    }
}
