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
        super(position, quantity);
        model = ModelManager.get("data/key.obj");
    }

    /**
     * Restores the amount of ammunition carried by the Actor.
     */
    @Override
    protected void interact(Actor actor) {
        actor.restoreAmmo(getQuantity());
    }

    @Override
    protected String getHelpMessage(Player player) {
        if (canPickUp(player)) {
            return "Restore ammo";
        }
        return "Ammo full";
    }

    @Override
    protected boolean canPickUp(Player player) {
        return player.getAmmo() < player.getMaximumAmmo();
    }
}
