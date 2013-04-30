package se.kth.csc.indafps;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
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
        GL11.glEnable(GL11.GL_CULL_FACE);

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

            handleInput();

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

    private void toggleFullscreen() {
        try {
            DisplayMode target = null;
            if (Display.isFullscreen()) {
                target = new DisplayMode(1280, 720);
            } else {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                DisplayMode desktop = Display.getDesktopDisplayMode();
                int freq = 0;
                for (DisplayMode mode : modes) {
                    if (mode.getWidth() == desktop.getWidth()
                            && mode.getHeight() == desktop.getHeight()) {
                        if (target == null || mode.getFrequency() > freq) {
                            if (target == null
                                    || mode.getBitsPerPixel() > target
                                            .getBitsPerPixel()) {
                                target = mode;
                                freq = target.getFrequency();
                            }
                        }
                        if (mode.getBitsPerPixel() == desktop.getBitsPerPixel()
                                && mode.getFrequency() == desktop
                                        .getFrequency()) {
                            target = mode;
                            break;
                        }
                    }
                }
            }
            if (target == null) {
                System.err
                        .println("Couldn't set display mode. No compatible modes found.");
                return;
            }
            Display.setDisplayMode(target);
            Display.setFullscreen(!Display.isFullscreen());
            GL11.glViewport(0, 0, target.getWidth(), target.getHeight());
        } catch (LWJGLException e) {
            System.err.println("Couln't set display mode.");
        }
    }

    private void handleInput() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_F11
                    && Keyboard.getEventKeyState()) {
                toggleFullscreen();
            }
        }
        manager.handleInput();
    }

    /**
     * Handles the engine cleanup process.
     */
    private void close() {
        // TODO The cleanup code
        Display.destroy();
    }
}
