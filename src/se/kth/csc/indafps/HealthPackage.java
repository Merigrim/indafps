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
        super(position, quantity);
        setTexture(TextureManager.get("data/healthpackage.jpg"));
    }

    /**
     * Restores the health of the Actor.
     */
    @Override
    protected void interact(Actor actor) {
        actor.restoreHealth(getQuantity());
    }

    @Override
    protected String getHelpMessage(Player player) {
        if (canPickUp(player)) {
            return "Restore health";
        }
        return "Health full";
    }

    @Override
    protected boolean canPickUp(Player player) {
        return player.getHealth() < player.getMaximumHealth();
    }
}
