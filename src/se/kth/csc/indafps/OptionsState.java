package se.kth.csc.indafps;

import java.util.HashSet;
import java.util.Set;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Allows the user to set various options.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-05-04
 */
public class OptionsState extends State {
    private Set<Button> buttons;
    private int displayModeIndex;
    private DisplayMode[] displayModes;
    private boolean fullscreen;

    public OptionsState() {
        fullscreen = Display.isFullscreen();
        try {
            displayModes = Display.getAvailableDisplayModes();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < displayModes.length; ++i) {
            if (Display.getDisplayMode() == displayModes[i]) {
                displayModeIndex = i;
                break;
            }
        }
        buttons = new HashSet<Button>();
        buttons.add(new Button("<", new Rect(32, 32, 64, 64),
                new ButtonCallback() {
                    @Override
                    public void onHover(Vec2 pos) {
                        // ...
                    }

                    @Override
                    public void onPress(Vec2 pos) {
                        --displayModeIndex;
                        if (displayModeIndex < 0) {
                            displayModeIndex = displayModes.length - 1;
                        }
                    }

                    @Override
                    public void onRelease(Vec2 pos) {
                        // ...
                    }
                }));
        buttons.add(new Button(">", new Rect(320, 32, 352, 64),
                new ButtonCallback() {
                    @Override
                    public void onHover(Vec2 pos) {
                        // ...
                    }

                    @Override
                    public void onPress(Vec2 pos) {
                        --displayModeIndex;
                        if (displayModeIndex < 0) {
                            displayModeIndex = displayModes.length - 1;
                        }
                    }

                    @Override
                    public void onRelease(Vec2 pos) {
                        // ...
                    }
                }));
        buttons.add(new Button(Display.isFullscreen() ? "Fullscreen"
                : "Windowed", new Rect(32, 80, 240, 112), new ButtonCallback() {
            @Override
            public void onHover(Vec2 pos) {
                // ...
            }

            @Override
            public void onPress(Vec2 pos) {
                fullscreen = !fullscreen;
                button.setText(fullscreen ? "Fullscreen" : "Windowed");
            }

            @Override
            public void onRelease(Vec2 pos) {
                // ...
            }
        }));
    }

    /**
     * Sets the display mode of the game window using the specified parameters.
     */
    private void updateDisplayMode() {
        DisplayMode m = displayModes[displayModeIndex];
        try {
            Display.setDisplayMode(m);
            Display.setFullscreen(fullscreen);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float dt) {
        for (Button b : buttons) {
            b.update(dt);
        }
    }

    @Override
    public void render(Renderer renderer) {
        for (Button b : buttons) {
            renderer.render(b);
        }
        DisplayMode m = displayModes[displayModeIndex];
        renderer.render(String.format("%dx%d %dBPP %dHz", m.getWidth(),
                m.getHeight(), m.getBitsPerPixel(), m.getFrequency()),
                new Vec2(192, 32), new Vec2(0.5f, 0.0f), new Vec4(1.0f, 1.0f,
                        1.0f, 1.0f));
    }

    @Override
    public void handleInput() {
        if (EventHandler.wasKeyReleased(Keyboard.KEY_ESCAPE)) {
            manager.popState();
        } else if (EventHandler.wasKeyReleased(Keyboard.KEY_RETURN)) {
            updateDisplayMode();
        }
        for (Button b : buttons) {
            b.handleInput();
        }
    }
}
