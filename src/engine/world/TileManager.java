package engine.world;

import engine.helpers.ReferenceList;
import engine.helpers.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TileManager {
    private final ReferenceList ref;
    public HashMap<String, Integer> tileList;
    public Tile[] tiles;
    public int[][] mapTileNum;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(ReferenceList ref) {
        this.ref = ref;
        InputStream is = getClass().getResourceAsStream(ref.world.tile_data_path);
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;

        try {
            while((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        tiles = new Tile[fileNames.size()];
        tileList = new HashMap<>();
        is = getClass().getResourceAsStream(ref.world.map_path);
        br = new BufferedReader(new InputStreamReader(is));
        try {
            line = br.readLine();
            String[] maxTile = line.split(" ");
            ref.world.maxWorldCol = maxTile.length;
            ref.world.maxWorldRow = maxTile.length;
            mapTileNum = new int[ref.world.maxWorldCol][ref.world.maxWorldRow];
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        for(int i = 0; i < fileNames.size(); i++) {
            String fileName;
            boolean collision = false;
            fileName = fileNames.get(i);
            if(collisionStatus.get(i).equals("true")){
                collision = true;
            }
            setup(i, fileName, collision);
        }
    }

    public void setup(int i, String name, boolean collision) {

        try {
            String filePath = "/World/Tiles/images/" + name;
            Tile tile = new Tile();
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
            tile.image = Utils.scaleImage(tile.image, ref.settings.tileSize, ref.settings.tileSize);
            tile.collision = collision;
            tiles[i] = tile;
            tileList.put(name, i);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream(ref.world.map_path);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < ref.world.maxWorldCol && row < ref.world.maxWorldRow) {
                String line = br.readLine();
                while (col < ref.world.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int tileType = (Integer.parseInt(numbers[col]));
                    mapTileNum[col][row] = tileType;
                    col++;
                }
                if (col == ref.world.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < ref.world.maxWorldCol && worldRow < ref.world.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * ref.settings.tileSize;
            int worldY = worldRow * ref.settings.tileSize;
            int screenX = worldX - ref.player.worldX + ref.settings.screenX;
            int screenY = worldY - ref.player.worldY + ref.settings.screenY;

            if (worldX + ref.settings.tileSize > ref.player.worldX - ref.settings.screenX &&
                    worldX - ref.settings.tileSize < ref.player.worldX + ref.settings.screenX &&
                    worldY + ref.settings.tileSize > ref.player.worldY - ref.settings.screenX &&
                    worldY - ref.settings.tileSize < ref.player.worldY + ref.settings.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if (worldCol == ref.world.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
//            if(game.ui.debug) {
//                g2.setColor(Color.red);
//                g2.drawRect(screenX, screenY, game.tileSize, game.tileSize);
//            }
        }
    }
}
