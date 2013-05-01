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

    protected Camera camera;

    /**
     * Sets the maximum health and ammo, and make the Actor face north.
     * 
     * @param position The position of the new Actor.
     * @param maxHealth The maximum health of the Actor. If it's set to a
     *            negative value the maximum health will be 0.
     * @param maxAmmo The maximum ammunition of the Actor. If it's set to a
     *            negative value the maximum ammunition will be 0.
     */
    public Actor(Vec3 position, int maxHealth, int maxAmmo) {
        super(position);
        health = new Gauge(maxHealth);
        ammo = new Gauge(maxAmmo);
        camera = new Camera();
        camera.setPosition(position);
    }

    @Override
    public void setPosition(Vec3 position) {
        super.setPosition(position);
        if (camera != null) {
            camera.setPosition(position);
        }
    }

    /**
     * Restores the health of the Actor. If amount is negative, the health will
     * be decreased instead. This Actor will not be able to restore its health
     * if it is dead. The Actor will be colored green if the health is
	 * increased. Otherwise if the health is decreased, the Actor will be
	 * colored red.
     * 
     * @param amount The amount of recovered health.
     * @return The new amount of health of the actor.
     */
    public final int restoreHealth(int amount) {
        if (isAlive()) {
			color = new Vec4(1.0f, 0.0f, 0.0f, 1.0f);
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
     * Sets the maximum ammo of the Actor. If max is negative, the maximum ammo
     * will be set to 0.
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
     * @return The current amount of ammunition.
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
     *         returned vector is normalized.
     */
    public final Vec3 getViewDirection() {
        return camera.getViewDirection();
    }

    /**
     * @return The camera associated with this actor
     */
    public final Camera getCamera() {
        return camera;
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

	/**
	 * Fires a bullet in the direction of the view. Actors hit by the bullet will
	 * take damage. Walls blocks bullets. When shot, the amount of bullets left
	 * will be decreased by one. If there are no bullets left, no bullet will be
	 * fired.
	 */
	public void fireBullet() {
		Vec3 viewDirection = camera.getViewDirection();
		Line shootLine = new Line(getPosition(), viewDirection);
		Entity closestPlayer = level.getIntersectingEntity("Player", shootLine, this);
		Entity closestEnemy = level.getIntersectingEntity("Enemy", shootLine, this);
		Entity closestWall = level.getIntersectingEntity("Wall", shootLine, this);
		if (closestEnemy != null) {
			((Actor) closestEnemy).restoreHealth(-10);
		}
	}

    @Override
    public void update(float dt) {
		Vec4 colorAdd = new Vec4(0.1f, 0.1f, 0.1f, 0.0f);
		colorAdd.mul(dt);
		color.copy(color.add(colorAdd));
		if (color.getR() > 1.0f) {
			color.setR(1.0f);
		}
		if (color.getG() > 1.0f) {
			color.setG(1.0f);
		}
		if (color.getB() > 1.0f) {
			color.setB(1.0f);
		}
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
