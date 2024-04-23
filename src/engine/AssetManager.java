package engine;

import assets.Asset;
import assets.dynamicEntity.animal.ANML_Ollie;
import assets.dynamicEntity.monster.MON_GreenSlime;
import assets.dynamicEntity.npc.Dan;
import assets.dynamicEntity.npc.NPC;
import assets.dynamicEntity.npc.NPC_OldMan;
import assets.dynamicEntity.particle.Particle;
import assets.staticEntity.Interactable.InteractableEntity;
import assets.staticEntity.Interactable.OBJ_Door;
import assets.staticEntity.Interactable.OBJ_Stairs;
import assets.staticEntity.StaticEntity;
import engine.helpers.ReferenceList;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AssetManager {

    private final ReferenceList ref;

    private final ArrayList<Asset> allAssets = new ArrayList<>();
    private final HashMap<String, Asset> assetMap = new HashMap<>();

    private final ArrayList<NPC> npcAssets = new ArrayList<>();

    private final ArrayList<StaticEntity> staticAssets = new ArrayList<>();
    private final HashMap<String, StaticEntity> staticEntityAssetsMap = new HashMap<>();

    private final ArrayList<InteractableEntity> interactableAssets = new ArrayList<>();
    private final HashMap<String, InteractableEntity> interactableAssetMap = new HashMap<>();

    private final ArrayList<Particle> particleList = new ArrayList<>();


    public AssetManager(ReferenceList referenceList) {
        this.ref = referenceList;
    }

    public ArrayList<Asset> getAllAssets() {return this.allAssets;}

    public void addAsset(Asset asset) {
        allAssets.add(asset);
        assetMap.put(asset.uuid, asset);
    }

    public Asset getAssetByUUID(String uuid) {return assetMap.get(uuid);}
    public InteractableEntity getInteractableByUUID(String uuid){return interactableAssetMap.get(uuid);}
    public ArrayList<InteractableEntity> getInteractableAssets() {return interactableAssets;}
    public StaticEntity getStaticByUUID(String uuid){return staticEntityAssetsMap.get(uuid);}
    public ArrayList<StaticEntity> getStaticAssets() {return staticAssets;}
    public ArrayList<NPC> getNPCAssets() {return npcAssets;}

    public void spawnAsset(String name, int worldX, int worldY) {
        Asset asset = null;
        switch(name) {
            case "Old Man":
                asset = new NPC_OldMan(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                npcAssets.add((NPC) asset);
                break;
            case "Green Slime":
                asset = new MON_GreenSlime(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                break;
            case "Ollie":
                asset = new ANML_Ollie(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                break;
            case "Dan":
                asset = new Dan(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                npcAssets.add((NPC) asset);
                break;
            case "Door":
                asset = new OBJ_Door(ref);
                asset.worldX = worldX * ref.settings.tileSize;
                asset.worldY = worldY * ref.settings.tileSize;
                staticEntityAssetsMap.put(asset.uuid, (InteractableEntity) asset);
                staticAssets.add((InteractableEntity) asset);
                interactableAssets.add((InteractableEntity) asset);
                interactableAssetMap.put(asset.uuid, (InteractableEntity) asset);
                break;
        }
        if(asset != null) {
            addAsset(asset);
        }
    }

    public void spawnStairs(int upstairsX, int upstairsY, int downstairsX, int downstairsY) {
        OBJ_Stairs upstairs = new OBJ_Stairs(ref, true, downstairsX, downstairsY);
        upstairs.worldX = upstairsX * ref.settings.tileSize;
        upstairs.worldY = upstairsY * ref.settings.tileSize;

        allAssets.add(upstairs);
        assetMap.put(upstairs.uuid, upstairs);
        interactableAssets.add(upstairs);
        interactableAssetMap.put(upstairs.uuid, upstairs);
        staticAssets.add(upstairs);
        staticEntityAssetsMap.put(upstairs.uuid, upstairs);

        OBJ_Stairs downstairs = new OBJ_Stairs(ref, false, upstairsX, upstairsY);
        downstairs.worldX = downstairsX * ref.settings.tileSize;
        downstairs.worldY = downstairsY * ref.settings.tileSize;

        allAssets.add(downstairs);
        assetMap.put(downstairs.uuid, downstairs);
        interactableAssets.add(downstairs);
        interactableAssetMap.put(downstairs.uuid, downstairs);
        staticAssets.add(downstairs);
        staticEntityAssetsMap.put(downstairs.uuid, downstairs);
    }

    public void spawnParticle(int variation, Asset generator, Color color, int size, int speed, int maxDuration) {}

    public ArrayList<Particle> getParticleList() {
        return this.particleList;
    }


    public void spawnTestAssets() {

        spawnAsset("Old Man", 27, 13);
        spawnAsset("Door", 11, 27);
        spawnAsset("Dan", 20, 241);
        //ref.assetManager.spawnAsset("Ollie", 26, 11);

        spawnStairs(12, 21, 12, 238);

    }
}
