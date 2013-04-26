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
	 * @param maxHealth The maximum health of the Actor. If it's set to
	 * a negative value the maximum health will be 0.
	 * @param maxAmmo The maximum ammunation of the Actor. If it's set to
	 * a negative value the maximum ammunation will be 0.
	 */
	Actor(int maxHealth, int maxAmmo) {
		health = new Gauge(maxHealth);
		ammo = new Gauge(maxAmmo);
		viewDirection = new Vec3(0.0f, 1.0f, 0.0f);
	}

    /**
     * Restores the health of the actor. If amount is negative, the health will
     * be decreased instead.
     * 
     * @param amount The amount of recovered health.
     * @return The new amount of health of the actor.
     */
    public int restoreHealth(int amount) {
        return health.add(amount);
    }

    /**
     * Restores the ammo of the actor. If amount is negative, the ammo will be
     * decreased instead.
     * 
     * @param amount The amount of recovered ammo.
     * @return The new amount of ammo of the actor.
     */
    public int restoreAmmo(int amount) {
        return ammo.add(amount);
    }

	/**
	 * @return The current amount of health.
	 */
	public int getHealth() {
		return health.getValue();
	}

	/**
	 * @return The current amount of ammunation.
	 */
	public int getAmmo() {
		return ammo.getValue();
	}

	/**
	 * @return The maximum amount of health.
	 */
	public int getMaximumHealth() {
		return health.getMaximum();
	}

	/**
	 * @return The maximum amount of ammo.
	 */
	public int getMaximumAmmo() {
		return ammo.getMaximum();
	}

	/**
	 * @return A copy of the vector representation of the view direction. The
	 * returned vector is normalized.
	 */
	public Vec3 getViewDirection() {
		Vec3 v = new Vec3();
		v.copy(viewDirection);
		return v;
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
