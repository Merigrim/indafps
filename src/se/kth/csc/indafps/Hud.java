package se.kth.csc.indafps;

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

    @Override
    public void render() {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
    }
}
