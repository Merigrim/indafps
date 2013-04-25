package se.kth.csc.indafps;

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
    /**
     * Default constructor.
     */
    public Engine() {
        // TODO Possible constructor work
    }

    /**
     * Initializes the engine, creating the game window and setting up OpenGL.
     * 
     * @return Whether the initialization was successful or not.
     */
    private boolean init() {
        // TODO The initialization procedure
        return false;
    }

    /**
     * Starts the game by initializing the engine and then entering the main
     * game loop.
     */
    public void run() {
        if (!init()) {
            return;
        }
        // TODO The main game loop
        close();
    }

    /**
     * Handles the engine cleanup process.
     */
    private void close() {
        // TODO The cleanup code
    }
}
