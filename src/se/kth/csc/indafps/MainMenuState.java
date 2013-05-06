package se.kth.csc.indafps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * The main menu is what the player will see when they first start the game. It
 * allows them to either jump into the actual game or take time and set various
 * options by taking them to the options menu.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class MainMenuState extends State {
    Image logo;

    public MainMenuState() {
        logo = new Image();
        Texture logoTexture = TextureManager.get("data/logo.png");
        logo.setTexture(logoTexture);
        logo.setPosition(new Vec2((Display.getWidth() - 512.0f) / 2.0f, Display
                .getHeight() / 22.5f));
    }

    /**
     * Starts a new game.
     */
    private void newGame() {
        GameState gameState = new GameState("data/level.lvl");
        // Reset mouse movement
        Mouse.getDX();
        Mouse.getDY();
        manager.pushState(gameState);
    }

    /**
     * Loads a saved game.
     */
    private void loadGame() {
        // TODO
    }

    /**
     * Opens the options menu.
     */
    private void options() {
        manager.pushState(new OptionsState());
    }

    /**
     * Quits the game.
     */
    private void quit() {
        // TODO
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(Renderer renderer) {
        renderer.render(logo);
    }

    @Override
    public void handleInput() {
        if (EventHandler.wasKeyPressed(Keyboard.KEY_RETURN)) {
            newGame();
        } else if (EventHandler.wasKeyPressed(Keyboard.KEY_O)) {
            options();
        }
        Mouse.setGrabbed(false);
    }
}
