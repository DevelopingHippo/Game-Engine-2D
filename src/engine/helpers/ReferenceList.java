package engine.helpers;

import assets.dynamicEntity.player.Player;
import engine.AssetManager;
import engine.Engine;
import engine.renderer.Renderer;
import engine.collision.CollisionChecker;
import engine.io.UserControls;
import engine.soundEngine.SoundEngine;
import engine.world.TileManager;
import engine.world.World;

public class ReferenceList {
    public Engine engine;
    public Renderer renderer = new Renderer(this);
    public Settings settings = new Settings();
    public UserControls userControls = new UserControls(this);
    public AssetManager assetManager = new AssetManager(this);
    public World world = new World(this);
    public TileManager tileManager = new TileManager(this);
    public SoundEngine soundEngine = new SoundEngine(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Player player = new Player(this);

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, shiftPressed;

}
