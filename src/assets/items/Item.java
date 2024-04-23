package assets.items;

import engine.helpers.ReferenceList;

import java.awt.image.BufferedImage;
import java.lang.ref.Reference;
import java.util.UUID;

public class Item {


    public ReferenceList ref;
    public final String type;
    public BufferedImage image, image2, image3;
    public String name;
    public String description = "";
    public String uuid = UUID.randomUUID().toString();
    public Item(ReferenceList ref, String type) {
        this.ref = ref;
        this.type = type;
    }



}
