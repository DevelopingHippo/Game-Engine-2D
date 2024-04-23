package assets.dynamicEntity.animal;

import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

public class Animal extends DynamicEntity {

    public Animal(ReferenceList ref) {
        super(ref);
        type = "Animal";
    }
}
