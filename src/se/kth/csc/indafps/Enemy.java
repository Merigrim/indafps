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
	private float fireDelay;
	private Actor target;

	/**
	 * The phases of the Enemy.
	 */
	public enum Phase {
		IDLE, // The Enemy hasn't noticed the Player yet
		ALERT // The Enemy is attacking the Player
	}

	public Enemy(Vec3 position) {
		this(position, 100, 12);
        setScale(new Vec3(0.3f, 1.0f, 0.3f));
		phase = Phase.IDLE;
		fireDelay = 0.0f;
		model = ModelManager.get("data/cube.obj");
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
	 * Searches for a Player in the field of view of this Enemy. If a Player
	 * is found, this Enemy will be set to alert mode and target the Player.
	 */
	private void findPlayer(){
		Actor foundActor = (Actor) getEntityInSight("Player", 3.0f, (float) Math.PI / 4);
		if (foundActor != null) {
			target = foundActor;
			phase = Phase.ALERT;
		}
	}

    @Override
    public void update(float dt) {
		super.update(dt);
		if (isAlive()) {
			if (phase == Phase.IDLE) {
				findPlayer();
			} else if (phase == Phase.ALERT) {
				camera.setTarget(target.getPosition());
				Vec3 viewDir = camera.getViewDirection();
				float angle = viewDir.dot(new Vec3(1.0f, 0.0f, 0.0f));
				setRotation(new Vec3(0.0f, (float) -Math.acos(angle)
							* Math.signum(viewDir.getZ()), 0.0f));
				if (target.getPosition().sub(getPosition()).getLength() >= 1.5f) {
					move(viewDir, 0.5f * dt);
				}
				fireDelay += 1.0f * dt;
				if (fireDelay > 1.0f) {
					fireBullet();
					fireDelay = 0.0f;
				}
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
