package se.kth.csc.indafps;

/**
 * This interface declares common methods implemented by game components, such
 * as entities and game states.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public interface GameComponent {
    /**
     * Updates the game component. Examples of updating can be motion,
     * animation, etc.
     * 
     * @param dt The time difference since the last update in seconds
     */
    void update(float dt);

    /**
     * Renders the game component to the screen.
     * 
     * @param renderer The renderer instance to use to render
     */
    void render(Renderer renderer);

    /**
     * Handles user input and window events.
     */
    void handleInput();
}
