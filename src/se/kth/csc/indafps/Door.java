package se.kth.csc.indafps;

/**
 * A game object that blocks the path of Actors when locked.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */

public class Door extends Entity {
    private boolean locked;
    private boolean open;
    private int direction;

    /**
     * Unlocks the door.
     */
    public void unlock() {
    }

    /**
     * Opens the door. Returns false if the door is locked.
     */
    public boolean open() {
        return false;
    }

    /**
     * Check the position of the surrounding walls and determine which direction
     * of the Door is most suitable. The Door will then change its direction to
     * the newly found direction.
     * 
     * @return The direction of the Door.
     */
    public int determineDirection() {
        return 0;
    }

    /**
     * @return True if the door is locked.
     */
    public boolean isLocked() {
        return false;
    }

    /**
     * @return True if the door is open.
     */
    public boolean isOpen() {
        return false;
    }

    /**
     * @return The direction of the door.
     */
    public int getDirection() {
        return 0;
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render() {
    }

    @Override
    public void handleInput() {
    }
}
