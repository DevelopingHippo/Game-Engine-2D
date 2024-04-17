package engine;

import assets.Asset;
import engine.helpers.ReferenceList;

import java.sql.Ref;
import java.util.ArrayList;

public class AssetManager {

    private final ReferenceList ref;
    private ArrayList<Asset> allAssets = new ArrayList<>();

    public AssetManager(ReferenceList referenceList) {
        this.ref = referenceList;
    }

    public ArrayList<Asset> getAllAssets() {
        return this.allAssets;
    }
}
