package se.kth.csc.indafps;

/**
 * Abstract base class for moving game objects.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Actor extends Entity {
    private Gauge health;
    private Gauge ammo;

    private Vec3 viewDirection;

    /**
     * Restores the health of the actor. If amount is negative, the health will
     * be decreased instead.
     * 
     * @param amount The amount of recovered health.
     * @return The new amount of health of the actor.
     */
    public int restoreHealth(int amount) {
        return 0;
    }

    /**
     * Restores the ammo of the actor. If amount is negative, the ammo will be
     * decreased instead.
     * 
     * @param amount The amount of recovered ammo.
     * @return The new amount of ammo of the actor.
     */
    public int restoreAmmo(int amount) {
        return 0;
    }
}
