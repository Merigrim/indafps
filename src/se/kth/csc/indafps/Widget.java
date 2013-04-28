package se.kth.csc.indafps;

/**
 * An abstract widget class. Should be inherited by widgets such as buttons.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Widget implements GameComponent {
    // The dimensions of the widget
    protected Rect rect;

    /**
     * Returns the dimensions of the widget as a rectangle.
     * 
     * @return The dimensions of the widget as a rectangle
     */
    public Rect getRect() {
        return rect;
    }

    @Override
    public abstract void update(float dt);

    @Override
    public abstract void render(Renderer renderer);

    @Override
    public abstract void handleInput();

}
