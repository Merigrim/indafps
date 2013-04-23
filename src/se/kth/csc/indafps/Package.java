package se.kth.csc.indafps;

/**
 * Abstract base class for game objects that affect the status
 * of the Player when it is picked up. The package can be picked
 * up only once.
 *
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */

public class Package extends Entity {
	private boolean taken;
	private int quantity;

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
