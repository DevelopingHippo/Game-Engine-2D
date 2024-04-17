package engine.collision;

import assets.Asset;
import assets.dynamicEntity.DynamicEntity;
import engine.helpers.ReferenceList;

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
                if(ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.moveSpeed) / ref.settings.tileSize;
                tileNum1 = ref.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
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
            if(ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        if(ref.downPressed) {
            entityBottomRow = (entityBottomWorldY + entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if (ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        if(ref.leftPressed) {
            entityLeftCol = (entityLeftWorldX - entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = ref.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            if (ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        if(ref.rightPressed) {
            entityRightCol = (entityRightWorldX + entity.moveSpeed) / ref.settings.tileSize;
            tileNum1 = ref.tileManager.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = ref.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if (ref.tileManager.tiles[tileNum1].collision || ref.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
    }

}
