package engine.helpers;

public class Settings {



    public int fps = 144;

    public String backgroundColor = "#3B8FCA";

    public int gameState = 1;
    public int titleState = 0;
    public int playState = 1;

    public int playerDefaultWorldX = 1, playerDefaultWorldY = 1;


    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16px x 16px tile (Character/Map/Textures)
    final int scale = 3; // Scales tile size * scale
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels
    public int screenX = screenHeight / 2 - (tileSize / 2);
    public int screenY = screenHeight / 2 - (tileSize / 2);


}