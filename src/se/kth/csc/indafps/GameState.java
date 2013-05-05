package se.kth.csc.indafps;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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
    private Player p, p2;

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
        hud = new Hud((Player)level.getEntities("Player").iterator().next());
        // p = new Player(new Vec3(0, 0, 0));
        // p2 = new Player(new Vec3(5, 0, 0));
        Mouse.setGrabbed(true);
        // TODO
    }

    @Override
    public void update(float dt) {
        test += dt / 10;
        // p.setRotation(new Vec3(0, test, 0));

        level.update(dt);
        hud.update(dt);
        level.removeDesignatedEntities();
    }

    @Override
    public void render(Renderer renderer) {
        /*
         * renderer.setCamera(p2.getCamera()); renderer.render(p);
         */

        level.render(renderer);
        hud.render(renderer);
    }

    @Override
    public void handleInput() {
        if (EventHandler.wasKeyReleased(Keyboard.KEY_ESCAPE)) {
            manager.popState();
            return;
        }
        level.handleInput();
        hud.handleInput();
        // p2.handleInput();
        Mouse.setGrabbed(true);
    }
}
