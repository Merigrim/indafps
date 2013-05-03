package se.kth.csc.indafps;

import java.util.Set;

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
    private float healthEffect;

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
        healthEffect = 0.0f;
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
        healthEffect = Math.signum(amount);
        if (isAlive()) {
            return health.add(amount);
        }
        return 0;
    }

    /**
     * Decreases the health of this Actor and tell this Actor who shot
     * this Actor.
     *
     * @param amount The amount of health that will be decreased.
     * @param shooter The Actor who took the shot.
     * @return The new amount of health of this Actor.
     */
    public int takeDamage(int amount, Actor shooter) {
        actOnShooter(shooter);
        return restoreHealth(-amount);
    }

    /**
     * Template for a function that gives this Actor the ability to respond
     * a fired bullet.
     *
     * @param shooter The Actor that this Actor will respond to.
     */
    protected void actOnShooter(Actor shooter) {
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
     * @return The current level of the color effect that happens when the
     *         health of the Actor has changed.
     */
    public float getHealthEffect() {
        return healthEffect;
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
     * @return True if the actor still has ammunition left.
     */
    public final boolean hasAmmo() {
        return !ammo.isEmpty();
    }

    /**
     * Moves this Actor at the given speed and direction. The direction will
     * automatically be normalized.
     */
    public void move(Vec3 direction, float speed) {
        setPosition(getPosition().add(direction.normalize().mul(speed)));
    }

    /**
     * Tests if the given Entity is inside the field of view of this Actor.
     * Entities blocked by walls will be excluded.
     *
     * @param entity The Entity that will be tested.
     * @param maxDistance The maximum distance allowed between this Actor and
     * the Entities.
     * @param angle The maximum angle allowed between the line between this
     * Actor and the Entities, and the view direction of the camera of this
     * Actor.
     * @return True if the Entity is inside the field of view of this Actor,
     * otherwise false is returned.
     */
    public boolean isInSight(Entity entity, float maxDistance, float angle) {
        Vec3 toEntity = entity.getPosition().sub(getPosition());
        if (toEntity.getLength() > maxDistance
                || toEntity.angle(camera.getViewDirection()) > angle) {
            return false;
        }
        Line ray = new Line(getPosition(), toEntity);
        Vec3 v = Geometry.intersects(ray, entity.getBoundingSphere());
        if (v == null) {
            return false;
        }
        float distance = v.sub(camera.getPosition()).getLength();
        for (Entity wall : level.getEntities("Wall")) {
            Vec3 v2 = wall.testIntersection(ray);
            if (v2 != null && v2.sub(camera.getPosition()).getLength() < distance) {
                return false;
            }
        }
        return true;
    }

    /**
     * Searches for an entity that is inside the field of view of this Actor.
     * Entities blocked by walls will be excluded.
     *
     * @param type The type of Entity to search for.
     * @param maxDistance The maximum distance allowed between this Actor and
     * the Entities.
     * @param angle The maximum angle allowed between the line between this
     * Actor and the Entities, and the view direction of the camera of this
     * Actor.
     * @return A Entity inside the field of view of this Actor.
     */
    public Entity getEntityInSight(String type, float maxDistance, float angle) {
        for (Entity entity : level.getEntities(type)) {
            if (isInSight(entity, maxDistance, angle)) {
                return entity;
            }
        }
        return null;
    }

    /**
     * Fires a bullet in the direction of the view. Actors hit by the bullet will
     * take damage. Walls blocks bullets. When shot, the amount of bullets left
     * will be decreased by one. If there are no bullets left, no bullet will be
     * fired. Returns the amount health left of the Actor who was shot. If
     * there were no Actor who was hit by the bullet, -1 is returned instead.
     *
     * @return The amount healt left of the Actor who was hit by the buller,
     * or -1 if no Actor was hit by the bullet.
     */
    public int fireBullet() {
        if (!hasAmmo()) {
            return -1;
        }
        Vec3 viewDirection = camera.getViewDirection();
        Line shootLine = new Line(getPosition(), viewDirection);
        Entity closestActor = level.getIntersectingEntity("Actor", shootLine,
                this);
        Entity closestSolid = level.getIntersectingEntity("Solid", shootLine,
                this);
        float actorDistance = Float.MAX_VALUE;
        float solidDistance = Float.MAX_VALUE;
        restoreAmmo(-1);
        if (closestActor != null) {
            Vec3 toActor = closestActor.getPosition().sub(getPosition());
            actorDistance = toActor.getLength();
        }
        if (closestSolid != null) {
            Vec3 toSolid = closestSolid.getPosition().sub(getPosition());
            solidDistance = toSolid.getLength();
        }
        if (actorDistance < solidDistance) {
            return ((Actor) closestActor).takeDamage(10, this);
        }
        return -1;
    }

    @Override
    public void update(float dt) {
        if (isAlive()) {
            healthEffect *= 0.9f;
            if (healthEffect < 0.0f) {
                setColor(new Vec4(1.0f, 1.0f + healthEffect, 1.0f + healthEffect,
                            1.0f));
            }
            if (healthEffect > 0.0f) {
                setColor(new Vec4(1.0f + healthEffect, 1.0f, 1.0f - healthEffect,
                            1.0f));
            }
        } else {
            box.getPosition().setY(box.getScale().getX() * 0.5f);
            box.getRotation().setZ((float)Math.PI * 0.5f);
            //TODO find a way to remove this small hack
            setPosition(getPosition());
        }
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
