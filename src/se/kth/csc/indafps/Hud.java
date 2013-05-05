package se.kth.csc.indafps;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * The HUD shows data such as health, ammo, etc. on the screen.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Hud implements GameComponent {
    // The player instance
    private Player player;

    /**
     * Initializes the HUD using the specified player instance.
     * 
     * @param player The player to keep track of
     */
    public Hud(Player player) {
        this.player = player;
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
    }

    private void drawHealthMeter(Renderer renderer) {
        Rect background = new Rect(50, Display.getHeight() - 150, 100,
                Display.getHeight() - 50);
        Rect meter = new Rect(50,
                Display.getHeight() - 50 - player.getHealth(), 100,
                Display.getHeight() - 50);
        renderer.render(background, new Vec4(0.2f, 0.0f, 0.0f, 1.0f));
        renderer.render(meter, new Vec4(0.8f, 0.0f, 0.0f, 1.0f));
    }

    private void drawAmmoMeter(Renderer renderer) {
        int x = Display.getWidth() - 50;
        for (int i = 0; i < player.getMaximumAmmo(); ++i) {
            Rect bullet = new Rect(x - 20, Display.getHeight() - 100, x,
                    Display.getHeight() - 50);
            if (i < player.getAmmo()) {
                renderer.render(bullet, new Vec4(0.4f, 0.4f, 1.0f, 1.0f));
            } else {
                renderer.render(bullet, new Vec4(0.1f, 0.1f, 0.4f, 1.0f));
            }
            x -= 30;
        }
    }

    @Override
    public void render(Renderer renderer) {
        if (player != null) {
            drawHealthMeter(renderer);
            drawAmmoMeter(renderer);
            Rect rect = new Rect(0, 0, Display.getWidth(), Display.getHeight());
            float tintLevel = player.getHealthEffect() * 0.9f;
            if (tintLevel < 0.0f) {
                // Player has taken damage
                renderer.render(rect, new Vec4(1.0f, 0.0f, 0.0f, -tintLevel));
            } else if (tintLevel > 0.0f) {
                // Player has recovered some health
                renderer.render(rect, new Vec4(0.0f, 1.0f, 0.0f, tintLevel));
            }
        }
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
    }
}
