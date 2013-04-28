package se.kth.csc.indafps;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

/**
 * The main game state. Handles the level which in turn handles all game objects
 * and their interactions.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class GameState extends State {
    // The current game level
    private Level level;

    // The heads-up display, which shows info about health, etc.
    private Hud hud;

    // TEST
    private float test = 0.0f;
    private Renderer renderer;
    private Player p;

    public GameState(String levelFilename) {
        init(levelFilename);
    }

    private void init(String levelFilename) {
        level = new Level();
        try {
            level.importLevel(levelFilename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hud = new Hud(null);
        renderer = new Renderer();
        p = new Player(new Vec3(0, 0, 0));
        // TODO
    }

    @Override
    public void update(float dt) {
        test += dt / 10;

        level.update(dt);
        hud.update(dt);
    }

    @Override
    public void render(Renderer renderer) {
        this.renderer.render(p);

        level.render(renderer);
        hud.render(renderer);
    }

    @Override
    public void handleInput() {
        level.handleInput();
        hud.handleInput();
    }
}
