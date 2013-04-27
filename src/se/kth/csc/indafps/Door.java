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
	 * The door will initially be locked.
	 */
	public Door(Vec3 position) {
		super(position);
		locked = true;
		open = false;
		direction = 0;
	}

    /**
     * Unlocks the door.
     */
    public void unlock() {
		locked = false;
    }

    /**
     * Opens the door. Returns false if the door is locked.
     */
    public boolean open() {
		open = !locked;
		return open;
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
        return locked;
    }

    /**
     * @return True if the door is open.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @return The direction of the door.
     */
    public int getDirection() {
        return direction;
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
