package se.kth.csc.indafps;

/**
 * A simple button with a callback which can be used to program interaction.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Button extends Image {
    // The button callback associated with this button
    private ButtonCallback callback;

    /**
     * Sets the associated callback.
     * 
     * @param callback The new callback to use
     */
    public void setCallback(ButtonCallback callback) {
        this.callback = callback;
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(Renderer renderer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
    }
}
