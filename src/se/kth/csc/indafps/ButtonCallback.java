package se.kth.csc.indafps;

/**
 * This interface should be implemented by anonymous callbacks that are passed
 * to the button class' <code>setCallback</code>-method.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public interface ButtonCallback {
    /**
     * Executes actions following the button being pressed.
     * 
     * @param pos The mouse cursor's coordinates
     */
    void onPress(Vec2 pos);

    /**
     * Executes actions following the button being pressed and released.
     * 
     * @param pos The mouse cursor's coordinates
     */
    void onRelease(Vec2 pos);

    /**
     * Executes actions following the button being hovered over by the cursor.
     * 
     * @param pos The mouse cursor's coordinates
     */
    void onHover(Vec2 pos);
}
