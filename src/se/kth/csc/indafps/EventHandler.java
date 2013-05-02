package se.kth.csc.indafps;

import org.lwjgl.input.Keyboard;

public class EventHandler {
    private static boolean[] pressed;
    private static boolean[] released;

    private static void createKeyCodes() {
        if (pressed == null) {
            pressed = new boolean[256];
        }
        if (released == null) {
            released = new boolean[256];
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

    public static void resetKeys() {
        createKeyCodes();
        for (int i = 0; i < 256; ++i) {
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
}
