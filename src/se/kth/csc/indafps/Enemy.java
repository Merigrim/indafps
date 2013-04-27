package se.kth.csc.indafps;

/**
 * Enemy object that are hostile to the Player.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Enemy extends Actor {
    private Item itemDrop;

    /**
     * Temporary constructor to make the build compile
     * 
     * TODO: Fix this
     * 
     * @param maxHealth Maximum enemy health
     * @param maxAmmo Maximum enemy ammunition
     */
    public Enemy(int maxHealth, int maxAmmo) {
        super(maxHealth, maxAmmo);
    }

    /**
     * @return True if the given Entity is inside the field of view of this
     *         Enemy.
     */
    private boolean isInSight(Entity entity) {
        return false;
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
