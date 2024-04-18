package assets.staticEntity.Interactable;

import assets.staticEntity.StaticEntity;
import engine.helpers.ReferenceList;

import java.awt.*;

public class InteractableEntity extends StaticEntity {
    public InteractableEntity(ReferenceList ref) {
        super(ref);
    }

    public boolean active = false;

    public Rectangle interactBox = new Rectangle(0, 0, 0, 0);
    public int interactBoxDefaultX = 0;
    public int interactBoxDefaultY = 0;

    public void interact() {
        System.out.println("INTERACTED WITH: " + name);
    }

}
