package se.kth.csc.indafps;

/**
 * Package that restores the ammo of the Player when picked up.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class AmmoPackage extends Package {
    public AmmoPackage(Vec3 position, int quantity) {
        super(position, quantity, "Restore ammo");
    }

    /**
     * Restores the amount of ammunation carried by the Actor.
     */
    public void interact(Actor actor) {
        actor.restoreAmmo(getQuantity());
    }
}
