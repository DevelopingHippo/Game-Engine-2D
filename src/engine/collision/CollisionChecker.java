package engine.collision;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

import java.util.Objects;

public class CollisionChecker {
    private final ReferenceList ref;
    public CollisionChecker(ReferenceList ref) {
        this.ref = ref;
    }

    public void checkTile(DynamicEntity entity) {
        int entityLeftWorldX = entity.worldX + entity.collisionBox.x;
        int entityRightWorldX = entity.worldX + entity.collisionBox.x + entity.collisionBox.width;
        int entityTopWorldY = entity.worldY + entity.collisionBox.y;
        int entityBottomWorldY = entity.worldY + entity.collisionBox.y + entity.collisionBox.height;

        int entityLeftCol = entityLeftWorldX / ref.settings.tileSize;
        int entityRightCol = entityRightWorldX / ref.settings.tileSize;
        int entityTopRow = entityTopWorldY / ref.settings.tileSize;
        int entityBottomRow = entityBottomWorldY / ref.settings.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void playerCheckTile(DynamicEntity entity) {
        int entityLeftWorldX = entity.worldX + entity.collisionBox.x;
        int entityRightWorldX = entity.worldX + entity.collisionBox.x + entity.collisionBox.width;
        int entityTopWorldY = entity.worldY + entity.collisionBox.y;
        int entityBottomWorldY = entity.worldY + entity.collisionBox.y + entity.collisionBox.height;

        int entityLeftCol = entityLeftWorldX / ref.settings.tileSize;
        int entityRightCol = entityRightWorldX / ref.settings.tileSize;
        int entityTopRow = entityTopWorldY / ref.settings.tileSize;
        int entityBottomRow = entityBottomWorldY / ref.settings.tileSize;

        int tileNum1, tileNum2;

        if(ref.upPressed){
            entityTopRow = (entityTopWorldY - entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityTopRow];
            if(ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                entity.collisionOn = true;
            }
        }
        if(ref.downPressed) {
            entityBottomRow = (entityBottomWorldY + entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if (ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                entity.collisionOn = true;
            }
        }
        if(ref.leftPressed) {
            entityLeftCol = (entityLeftWorldX - entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            if (ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                entity.collisionOn = true;
            }
        }
        if(ref.rightPressed) {
            entityRightCol = (entityRightWorldX + entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if (ref.tileManager.tiles[tileNum1].collidable || ref.tileManager.tiles[tileNum2].collidable) {
                entity.collisionOn = true;
            }
        }
    }

    public String checkStaticEntity(DynamicEntity entity, boolean player) {
        String index = null;
        for(int i = 0; i < ref.assetManager.getStaticAssets().size(); i++) {
            if(ref.assetManager.getStaticAssets().get(i) != null) {
                if(ref.assetManager.getStaticAssets().get(i).isInPlayerVision()){
                    entity.collisionBox.x += entity.worldX;
                    entity.collisionBox.y += entity.worldY;
                    ref.assetManager.getStaticAssets().get(i).collisionBox.x += ref.assetManager.getStaticAssets().get(i).worldX;
                    ref.assetManager.getStaticAssets().get(i).collisionBox.y += ref.assetManager.getStaticAssets().get(i).worldY;
                    checkDirection(entity);
                    if(entity.collisionBox.intersects(ref.assetManager.getStaticAssets().get(i).collisionBox)) {
                        if(ref.assetManager.getStaticAssets().get(i).collidable) {
                            entity.collisionOn = true;
                            if(player) {
                                index = ref.assetManager.getStaticAssets().get(i).uuid;
                            }
                        }
                    }
                    entity.collisionBox.x = entity.collisionBoxDefaultX;
                    entity.collisionBox.y = entity.collisionBoxDefaultY;
                    ref.assetManager.getStaticAssets().get(i).collisionBox.x = ref.assetManager.getStaticAssets().get(i).collisionBoxDefaultX;
                    ref.assetManager.getStaticAssets().get(i).collisionBox.y = ref.assetManager.getStaticAssets().get(i).collisionBoxDefaultY;
                }
            }
        }
        return index;
    }

    private void checkDirection(DynamicEntity entity) {
        if(Objects.equals(entity.name, "Player")){
            if(ref.upPressed){entity.collisionBox.y -= entity.moveSpeed;}
            if(ref.downPressed){entity.collisionBox.y += entity.moveSpeed;}
            if(ref.leftPressed){entity.collisionBox.x -= entity.moveSpeed;}
            if(ref.rightPressed){entity.collisionBox.x += entity.moveSpeed;}
        }
        else {
            switch(entity.direction) {
                case "up": entity.collisionBox.y -= entity.moveSpeed;break;
                case "down": entity.collisionBox.y += entity.moveSpeed;break;
                case "left": entity.collisionBox.x -= entity.moveSpeed;break;
                case "right": entity.collisionBox.x += entity.moveSpeed;break;
            }
        }
    }

    public boolean checkInteractZone() {
        boolean interact = false;
        for(int i = 0; i < ref.assetManager.getInteractableAssets().size(); i++) {
            if(ref.assetManager.getInteractableAssets().get(i) != null) {
                if(ref.assetManager.getInteractableAssets().get(i).isInPlayerVision()) {
                    ref.player.collisionBox.x += ref.player.worldX;
                    ref.player.collisionBox.y += ref.player.worldY;
                    ref.assetManager.getInteractableAssets().get(i).interactBox.x += ref.assetManager.getInteractableAssets().get(i).worldX;
                    ref.assetManager.getInteractableAssets().get(i).interactBox.y += ref.assetManager.getInteractableAssets().get(i).worldY;
                    if(ref.player.collisionBox.intersects(ref.assetManager.getInteractableAssets().get(i).interactBox)) {
                        ref.assetManager.getInteractableAssets().get(i).interact();
                        ref.player.collisionBox.x = ref.player.collisionBoxDefaultX;
                        ref.player.collisionBox.y = ref.player.collisionBoxDefaultY;
                        ref.assetManager.getInteractableAssets().get(i).interactBox.x = ref.assetManager.getInteractableAssets().get(i).interactBoxDefaultX;
                        ref.assetManager.getInteractableAssets().get(i).interactBox.y = ref.assetManager.getInteractableAssets().get(i).interactBoxDefaultY;
                        return true;
                    }
                    ref.player.collisionBox.x = ref.player.collisionBoxDefaultX;
                    ref.player.collisionBox.y = ref.player.collisionBoxDefaultY;
                    ref.assetManager.getInteractableAssets().get(i).interactBox.x = ref.assetManager.getInteractableAssets().get(i).interactBoxDefaultX;
                    ref.assetManager.getInteractableAssets().get(i).interactBox.y = ref.assetManager.getInteractableAssets().get(i).interactBoxDefaultY;
                }
            }
        }
        return interact;
    }
}
