package engine;

import assets.Asset;
import assets.dynamicEntity.monster.MON_GreenSlime;
import assets.dynamicEntity.npc.NPC_OldMan;
import engine.helpers.ReferenceList;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;

public class AssetManager {

    private final ReferenceList ref;
    private final ArrayList<Asset> allAssets = new ArrayList<>();
    private final HashMap<String, Asset> assetMap = new HashMap<>();

    public AssetManager(ReferenceList referenceList) {
        this.ref = referenceList;
    }

    public ArrayList<Asset> getAllAssets() {return this.allAssets;}

    public void addAsset(Asset asset) {
        allAssets.add(asset);
        assetMap.put(asset.uuid, asset);
    }

    public Asset getAssetByUUID(String uuid) {return assetMap.get(uuid);}


    public void spawnAsset(String name, int worldX, int worldY) {
        Asset asset = null;

        switch(name) {
            case "Old Man":
                asset = new NPC_OldMan(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                break;
            case "Green Slime":
                asset = new MON_GreenSlime(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                break;
        }

        if(asset != null) {
            addAsset(asset);
        }
    }


}
