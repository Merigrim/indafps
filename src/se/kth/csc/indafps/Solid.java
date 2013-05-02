package se.kth.csc.indafps;

import java.util.Set;

public abstract class Solid extends Entity {
    public Solid(Vec3 position) {
        super(position);
    }

    protected boolean toNorth(Vec3 position) {
        Vec3 wallPos = getPosition();
        return position.getX() > -position.getZ() + wallPos.getX()
                + wallPos.getZ()
                && position.getX() < position.getZ() + wallPos.getX()
                        - wallPos.getZ();
    }

    protected boolean toWest(Vec3 position) {
        Vec3 wallPos = getPosition();
        return position.getZ() > -position.getX() + wallPos.getZ()
                + wallPos.getX()
                && position.getZ() < position.getX() + wallPos.getZ()
                        - wallPos.getX();
    }

    protected boolean toSouth(Vec3 position) {
        Vec3 wallPos = getPosition();
        return position.getX() < -position.getZ() + wallPos.getX()
                + wallPos.getZ()
                && position.getX() > position.getZ() + wallPos.getX()
                        - wallPos.getZ();
    }

    protected boolean toEast(Vec3 position) {
        Vec3 wallPos = getPosition();
        return position.getZ() < -position.getX() + wallPos.getZ()
                + wallPos.getX()
                && position.getZ() > position.getX() + wallPos.getZ()
                        - wallPos.getX();
    }

    /**
     * Positions the actor so that it doesn't collide with the wall anymore.
     */
    protected void positionActor(Actor actor) {
        Vec3 actorPos = actor.getPosition();
        Vec3 actorScale = actor.getScale();
        Vec3 wallPos = getPosition();
        Vec3 wallScale = getScale();
        if (toNorth(actorPos)) {
            actorPos.setZ(wallPos.getZ()
                    + (wallScale.getZ() + actorScale.getZ()) * 0.5f);
        } else if (toWest(actorPos)) {
            actorPos.setX(wallPos.getX()
                    + (wallScale.getX() + actorScale.getX()) * 0.5f);
        } else if (toSouth(actorPos)) {
            actorPos.setZ(wallPos.getZ()
                    - (wallScale.getZ() + actorScale.getZ()) * 0.5f);
        } else if (toEast(actorPos)) {
            actorPos.setX(wallPos.getX()
                    - (wallScale.getX() + actorScale.getX()) * 0.5f);
        }
        actor.setPosition(actorPos);
    }

    protected void actorCollision(Set<Entity> entities) {
        for (Entity entity : entities) {
            if (testIntersection(entity)) {
                positionActor((Actor)entity);
            }
        }
    }

    @Override
    public void update(float dt) {
        actorCollision(level.getEntities("Player"));
        actorCollision(level.getEntities("Enemy"));
    }
}
