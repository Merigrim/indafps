package se.kth.csc.indafps;

import java.io.IOException;
import java.util.Set;

/**
 * A game object that blocks the movement of Actors.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Wall extends Entity {
    public Wall(Vec3 position) {
        super(position);
        try {
            model = ModelManager.get("data/cube.obj");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	private boolean toNorth(Vec3 position) {
		Vec3 wallPos = getPosition();
		return position.getX() > -position.getZ() + wallPos.getX() + wallPos.getZ()
			&& position.getX() < position.getZ() + wallPos.getX() - wallPos.getZ();
	}

	private boolean toWest(Vec3 position) {
		Vec3 wallPos = getPosition();
		return position.getZ() > -position.getX() + wallPos.getZ() + wallPos.getX()
			&& position.getZ() < position.getX() + wallPos.getZ() - wallPos.getX();
	}

	private boolean toSouth(Vec3 position) {
		Vec3 wallPos = getPosition();
		return position.getX() < -position.getZ() + wallPos.getX() + wallPos.getZ()
			&& position.getX() > position.getZ() + wallPos.getX() - wallPos.getZ();
	}

	private boolean toEast(Vec3 position) {
		Vec3 wallPos = getPosition();
		return position.getZ() < -position.getX() + wallPos.getZ() + wallPos.getX()
			&& position.getZ() > position.getX() + wallPos.getZ() - wallPos.getX();
	}

	/**
	 * Positions the actor so that it doesn't collide with the wall
	 * anymore.
	 */
	private void positionActor(Actor actor) {
		Vec3 actorPos = actor.getPosition();
		Vec3 actorScale = actor.getScale();
		Vec3 wallPos = getPosition();
		Vec3 wallScale = getScale();
		if (toNorth(actorPos)) {
			actorPos.setZ(wallPos.getZ() + (wallScale.getZ() + actorScale.getZ()) * 0.5f);
		} else if (toWest(actorPos)) {
			actorPos.setX(wallPos.getX() + (wallScale.getX() + actorScale.getX()) * 0.5f);
		} else if (toSouth(actorPos)) {
			actorPos.setZ(wallPos.getZ() - (wallScale.getZ() + actorScale.getZ()) * 0.5f);
		} else if (toEast(actorPos)) {
			actorPos.setX(wallPos.getX() - (wallScale.getX() + actorScale.getX()) * 0.5f);
		}
		actor.setPosition(actorPos);
	}

	private void actorCollision(Set <Entity> entities) {
		for (Entity entity : entities) {
			if (testIntersection(entity)) {
				positionActor((Actor) entity);
			}
		}
	}

    @Override
    public void update(float dt) {
		actorCollision(level.getEntities("Player"));
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
