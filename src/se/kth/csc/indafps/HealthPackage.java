package se.kth.csc.indafps;

/**
 * Package that restores the health of an Actor when picked up.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class HealthPackage extends Package {
    public HealthPackage(Vec3 position, int quantity) {
        super(position, quantity, "Restore health");
        model = ModelManager.get("data/key.obj");
    }

    /**
     * Restores the health of the Actor.
     */
    @Override
    public void interact(Actor actor) {
        actor.restoreHealth(getQuantity());
    }
}
