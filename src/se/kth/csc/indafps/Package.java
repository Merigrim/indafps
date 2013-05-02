package se.kth.csc.indafps;

/**
 * Abstract base class for game objects that affect the status of the Player
 * when it is picked up. The package can be picked up only once.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Package extends Entity {
    protected boolean taken;
    protected int quantity;

	public Package(Vec3 position, int quantity) {
		super(position);
		this.taken = false;
		this.quantity = quantity;
	}

	/**
	 * @return True if the package has been taken, otherwise false.
	 */
	public boolean isTaken() {
		return taken;
	}

	/**
	 * @return The quantity of the package.
	 */
	public int getQuantity() {
		return quantity;
	}

    @Override
    public void update(float dt) {
		for (Entity player : level.getEntities("Player")) {
			if (((Actor) player).isInSight(this, 1.0f, 0.1f)) {
			}
		}
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
