package assets.staticEntity;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import java.awt.*;

public class OBJ_Door extends StaticEntity {
    public OBJ_Door(ReferenceList ref) {
        super(ref);

        collisionBoxDefaultX = 0;
        collisionBoxDefaultY = 0;
        collisionBox = new Rectangle(collisionBoxDefaultX, collisionBoxDefaultY, ref.settings.tileSize, ref.settings.tileSize);
    }


}
