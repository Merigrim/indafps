package se.kth.csc.indafps;

/**
 * State is an abstract class which can be inherited by various game states,
 * such as a main menu or a gameplay state.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class State implements GameComponent {
    // The state manager associated with this state
    protected StateManager manager;

    @Override
    public abstract void update(float dt);

    @Override
    public abstract void render(Renderer renderer);

    @Override
    public abstract void handleInput();

    /**
     * Associates this specific state with the specified StateManager instance.
     * Unfortunately, Java doesn't have a concept of friend classes (Boo!) so
     * this method will have package-level access.
     * 
     * @param manager
     */
    void associateWithManager(StateManager manager) {
        this.manager = manager;
    }
}
