package engine.world;

import engine.helpers.ReferenceList;

public class World {


    private final ReferenceList ref;
    public int maxWorldCol = 30;
    public int maxWorldRow = 30;
    public String map_path = "/World/Maps/world.txt";
    public String tile_data_path = "/World/Tiles/data/tiledata.txt";

    public World(ReferenceList ref) {
        this.ref = ref;
    }


}
