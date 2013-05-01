package se.kth.csc.indafps;

import java.io.IOException;

/**
 * Enemy object that are hostile to the Player.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Enemy extends Actor {
    private Item itemDrop;
	private Phase phase;

	/**
	 * The phases of the Enemy.
	 */
	public enum Phase {
		IDLE, // The Enemy hasn't noticed the Player yet
		ALERT // The Enemy is attacking the Player
	}

	public Enemy(Vec3 position) {
		this(position, 100, 12);
        setScale(new Vec3(0.5f, 1.0f, 0.5f));
		phase = Phase.IDLE;
        try {
            model = ModelManager.get("data/cube.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    /**
     * Constructs an Enemy with specified health and ammo.
	 *
     * @param maxHealth Maximum enemy health
     * @param maxAmmo Maximum enemy ammunition
     */
    public Enemy(Vec3 position, int maxHealth, int maxAmmo) {
        super(position, maxHealth, maxAmmo);
		itemDrop = null;
    }

	/**
	 * Changes the Item held by the Enemy and returns the Item currently held
	 * by the Enemy. If no item is currently held by the Enemy, null is
	 * returned. The owner of the new Item will be set to this Enemy, and the
	 * owner of the currently held Item will be set to null.
	 *
	 * @return The Item currently held by the Enemy.
	 */
	public Item changeItem(Item item) {
		Item previous = itemDrop;
		itemDrop = item;
		item.setOwner(this);
		previous.setOwner(null);
		return previous;
	}

	/**
	 * @return The current phase of the Enemy. Could be one of IDLE or ALERT.
	 */
	public Phase getPhase() {
		return phase;
	}

    /**
     * @return True if the given Entity is inside the field of view of this
     *         Enemy.
     */
    private boolean isInSight(Entity entity) {
        return false;
    }

    @Override
    public void update(float dt) {
		super.update(dt);
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
