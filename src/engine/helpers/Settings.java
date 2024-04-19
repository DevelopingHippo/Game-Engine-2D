package engine.helpers;

public class Settings {



    public int fps = 144;

    public String backgroundColor = "#3B8FCA";

    public int gameState = 0;
    public int titleState = 0;
    public int playState = 1;
    public int gameDrawState = 0;
    public int titleDrawState = 0;
    public int playDrawState = 1;
    public int characterDrawState = 3;

    public boolean debug = false;

    public int playerDefaultWorldX = 24, playerDefaultWorldY = 11;
    public int playerDefaultMoveSpeed = 1;
    public int playerSprintSpeed = 2;

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16px x 16px tile (Character/Map/Textures)
    final int scale = 3; // Scales tile size * scale
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels
    public int screenX = screenWidth / 2 - (tileSize / 2);
    public int screenY = screenHeight / 2 - (tileSize / 2);
}
