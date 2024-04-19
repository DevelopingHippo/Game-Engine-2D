package engine.world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collidable = false;
    public boolean interactable = true;
    public boolean fishable = false;
    public Rectangle interactZone = new Rectangle(-32, -32, 64, 64);
}
