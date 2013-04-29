package se.kth.csc.indafps;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * The Engine class is responsible for relaying update and render calls as well
 * as window events to the state manager instance, which in turn handles the
 * current game state.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Engine {
    private StateManager manager;
    private Renderer renderer;

    /**
     * Default constructor.
     */
    public Engine() {
        // TODO Possible constructor work
    }

    /**
     * Does OpenGL-related initialization.
     * 
     * @return Whether the initialization was successful or not.
     */
    private boolean initGL() {
        GL11.glClearColor(0.25f, 0.25f, 0.25f, 1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        renderer = new Renderer();

        return true;
    }

    /**
     * Initializes the engine, creating the game window and setting up OpenGL.
     * 
     * @return Whether the initialization was successful or not.
     */
    private boolean init() {
        // TODO The initialization procedure
        try {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            return false;
        }

        if (!initGL()) {
            return false;
        }

        manager = new StateManager();
        // GameState gameState = new GameState("levels/test.lvl");
        // manager.pushState(gameState);
        manager.pushState(new MainMenuState());

        return true;
    }

    /**
     * Starts the game by initializing the engine and then entering the main
     * game loop.
     */
    public void run() {
        if (!init()) {
            return;
        }
        long lastTime = System.nanoTime();
        while (!Display.isCloseRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            manager.handleInput();

            long currentTime = System.nanoTime();
            float dt = (currentTime - lastTime) / 1000000000.0f;
            lastTime = currentTime;
            manager.update(dt);
            manager.render(renderer);

            Display.update();
            Display.sync(60);
        }
        close();
    }

    /**
     * Handles the engine cleanup process.
     */
    private void close() {
        // TODO The cleanup code
        Display.destroy();
    }
}
