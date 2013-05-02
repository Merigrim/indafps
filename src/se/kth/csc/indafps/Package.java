package se.kth.csc.indafps;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * Abstract base class for game objects that affect the status of Actors
 * when it is picked up. The package can be picked up only once.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Package extends Entity {
    private boolean inSight;
    protected int quantity;
    protected String helpMessage;

    public Package(Vec3 position, int quantity, String helpMessage) {
        super(position);
        this.quantity = quantity;
        this.helpMessage = helpMessage;
        inSight = false;
    }

    /**
     * @return The quantity of the package.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Interact with the given Actor.
     */
    public abstract void interact(Actor actor);

    /**
     * Search for Players that has this Package in their field of view and
     * let them interact with this Package.
     */
    @Override
    public void update(float dt) {
        inSight = false;
        for (Entity player : level.getEntities("Player")) {
            if (((Actor) player).isInSight(this, 1.0f, 0.5f)) {
                inSight = true;
                if (EventHandler.wasKeyPressed(Keyboard.KEY_E)) {
                    interact((Actor) player);
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
            renderer.render(String.format("[E] %s", helpMessage));
        }
    }

    @Override
    public void handleInput() {
    }
}
