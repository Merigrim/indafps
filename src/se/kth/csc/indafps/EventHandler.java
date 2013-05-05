package se.kth.csc.indafps;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class EventHandler {
    private static boolean[] pressed;
    private static boolean[] released;
    private static boolean[] mousePressed;
    private static boolean[] mouseReleased;

    private static void createKeyCodes() {
        if (pressed == null) {
            pressed = new boolean[256];
        }
        if (released == null) {
            released = new boolean[256];
        }
        if (mousePressed == null) {
            mousePressed = new boolean[10];
        }
        if (mouseReleased == null) {
            mouseReleased = new boolean[10];
        }
    }

    /**
     * Returns true if the specified key is currently pressed.
     * 
     * @param keyCode The key to check
     * @return True if the key is currently pressed, false otherwise
     */
    public static boolean isKeyDown(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }

    /**
     * Returns true if the specified key is not currently pressed.
     * 
     * @param keyCode THe key to check
     * @return True if the key is not currently pressed, false otherwise
     */
    public static boolean isKeyUp(int keyCode) {
        return !isKeyDown(keyCode);
    }

    /**
     * Returns true if the specified key was pressed this frame.
     * 
     * @param keyCode The key to check
     * @return True if the key was pressed this frame, false otherwise
     */
    public static boolean wasKeyPressed(int keyCode) {
        createKeyCodes();
        return pressed[keyCode];
    }

    /**
     * Returns true if the specified key was released this frame.
     * 
     * @param keyCode The key to check
     * @return True if the key was released this frame, false otherwise
     */
    public static boolean wasKeyReleased(int keyCode) {
        createKeyCodes();
        return released[keyCode];
    }

    public static boolean wasButtonPressed(int i) {
        createKeyCodes();
        return mousePressed[i];
    }

    public static void resetKeys() {
        createKeyCodes();
        for (int i = 0; i < 256; ++i) {
            if (i < 10) {
                mousePressed[i] = mouseReleased[i] = false;
            }
            pressed[i] = released[i] = false;
        }
    }

    public static void updateKey() {
        if (Keyboard.getEventKeyState()) {
            pressed[Keyboard.getEventKey()] = true;
        } else {
            released[Keyboard.getEventKey()] = true;
        }
    }

    public static void updateButton() {
        if (Mouse.getEventButton() == -1) {
            return;
        }
        if (Mouse.getEventButtonState()) {
            mousePressed[Mouse.getEventButton()] = true;
        } else {
            mouseReleased[Mouse.getEventButton()] = true;
        }
    }

    public static Vec2 getMousePosition() {
        return new Vec2(Mouse.getX(), Display.getHeight() - Mouse.getY());
    }
}
