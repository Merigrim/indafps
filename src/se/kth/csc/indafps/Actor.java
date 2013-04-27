package se.kth.csc.indafps;

/**
 * Abstract base class for moving game objects.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Actor extends Entity {
	private Gauge health;
	private Gauge ammo;

	protected Vec3 viewDirection;

	/**
	 * Sets the maximum health and ammo, and make the Actor face north.
	 * @param position The position of the new Actor.
	 * @param maxHealth The maximum health of the Actor. If it's set to
	 * a negative value the maximum health will be 0.
	 * @param maxAmmo The maximum ammunation of the Actor. If it's set to
	 * a negative value the maximum ammunation will be 0.
	 */
	public Actor(Vec3 position, int maxHealth, int maxAmmo) {
		super(position);
		health = new Gauge(maxHealth);
		ammo = new Gauge(maxAmmo);
		viewDirection = new Vec3(0.0f, 1.0f, 0.0f);
	}

	/**
	 * Restores the health of the Actor. If amount is negative, the health will
	 * be decreased instead. This Actor will not be able to restore its health
	 * if it is dead.
	 * 
	 * @param amount The amount of recovered health.
	 * @return The new amount of health of the actor.
	 */
	public final int restoreHealth(int amount) {
		if (isAlive()) {
			return health.add(amount);
		}
		return 0;
	}

	/**
	 * Restores the ammo of the Actor. If amount is negative, the ammo will be
	 * decreased instead.
	 * 
	 * @param amount The amount of recovered ammo.
	 * @return The new amount of ammo of the actor.
	 */
	public final int restoreAmmo(int amount) {
		return ammo.add(amount);
	}

	/**
	 * Sets the maximum health of the Actor. If max is negative, the maximum
	 * health will be set to 0.
	 *
	 * @param max The new maximum amount of health.
	 */
	public final void setMaximumHealth(int max) {
		health.setMaximum(max);
	}

	/**
	 * Sets the maximum ammo of the Actor. If max is negative, the maximum
	 * ammo will be set to 0.
	 *
	 * @param max The new maximum amount of ammo.
	 */
	public final void setMaximumAmmo(int max) {
		ammo.setMaximum(max);
	}

	/**
	 * @return The current amount of health.
	 */
	public final int getHealth() {
		return health.getValue();
	}

	/**
	 * @return The current amount of ammunation.
	 */
	public final int getAmmo() {
		return ammo.getValue();
	}

	/**
	 * @return The maximum amount of health.
	 */
	public final int getMaximumHealth() {
		return health.getMaximum();
	}

	/**
	 * @return The maximum amount of ammo.
	 */
	public final int getMaximumAmmo() {
		return ammo.getMaximum();
	}

	/**
	 * @return A copy of the vector representation of the view direction. The
	 * returned vector is normalized.
	 */
	public final Vec3 getViewDirection() {
		Vec3 v = new Vec3();
		v.copy(viewDirection);
		return v;
	}

	/**
	 * @return True if the actor still has health left.
	 */
	public final boolean isAlive() {
		return !health.isEmpty();
	}

	/**
	 * @return True if the actor still has ammunation left.
	 */
	public final boolean hasAmmo() {
		return !ammo.isEmpty();
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
