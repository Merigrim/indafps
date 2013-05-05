package se.kth.csc.indafps;

import org.lwjgl.input.Mouse;

/**
 * A simple button with a callback which can be used to program interaction.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Button extends Image {
    private String text;
    // The button callback associated with this button
    private ButtonCallback callback;

    public Button(String text, Rect rect, ButtonCallback callback) {
        super();
        setText(text);
        setCallback(callback);
        setTexture(TextureManager.get("data/button.jpg"));
        setRect(rect);
        System.out.println("Rect: " + this.rect + ", " + rect);
    }

    public Button(String text, Rect rect) {
        this(text, rect, null);
    }

    /**
     * Sets the text displayed on this button.
     * 
     * @param text The text to display.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the associated callback.
     * 
     * @param callback The new callback to use
     */
    public void setCallback(ButtonCallback callback) {
        this.callback = callback;
        this.callback.setButton(this);
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
        if (EventHandler.wasButtonPressed(0)) {
            Vec2 mp = EventHandler.getMousePosition();
            if (mp.getX() >= rect.left && mp.getY() >= rect.top
                    && mp.getX() < rect.right && mp.getY() < rect.bottom) {
                callback.onPress(mp);
            }
        }
    }

    public String getText() {
        return this.text;
    }
}
