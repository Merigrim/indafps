package se.kth.csc.indafps;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * Abstract base class for game objects that affect the status of Actors when it
 * is picked up. The package can be picked up only once.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Package extends Entity {
    private boolean inSight;
    protected int quantity;

    public Package(Vec3 position, int quantity) {
        super(position);
        this.quantity = quantity;
        inSight = false;
    }

    /**
     * Returns a relevant help message that is rendered when the player is in
     * close proximity to the package and looks at it.
     * 
     * Must be overridden in a subclass.
     * 
     * @param player The player object
     * @return The help message that is relevant to this package and the current
     *         situation.
     */
    protected abstract String getHelpMessage(Player player);

    /**
     * @return The quantity of the package.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Interact with the given Actor.
     */
    protected abstract void interact(Actor actor);

    /**
     * Search for Players that has this Package in their field of view and let
     * them interact with this Package.
     */
    @Override
    public void update(float dt) {
        inSight = false;
        for (Entity player : level.getEntities("Player")) {
            if (((Actor)player).isInSight(this, 1.0f, 0.5f)) {
                inSight = true;
                if (EventHandler.wasKeyPressed(Keyboard.KEY_E)
                        && canPickUp((Player)player)) {
                    interact((Actor)player);
                    level.removeEntity(this);
                }
            }
        }
    }

    /**
     * Renders a help message if there is a Player that has this Package in
     * their field of view.
     */
    @Override
    public void render(Renderer renderer) {
        if (inSight) {
            if (canPickUp(level.getPlayer())) {
                renderer.render(String.format("[E] %s",
                        getHelpMessage(level.getPlayer())));
            } else {
                renderer.render(getHelpMessage(level.getPlayer()), new Vec2(
                        Display.getWidth() / 2.0f,
                        Display.getHeight() / 6.0f * 5.0f),
                        new Vec2(0.5f, 0.0f), new Vec4(1.0f, 0.0f, 0.0f, 1.0f));
            }
        }
    }

    @Override
    public void handleInput() {
    }

    /**
     * Returns whether the package can be picked up or not.
     * 
     * Must be overridden in a subclass.
     * 
     * @param player The player object
     * @return Whether the package can be picked up or not
     */
    protected abstract boolean canPickUp(Player player);
}
