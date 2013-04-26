package se.kth.csc.indafps;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Game object manager.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Level implements GameComponent {
    private Map<String, Set<Entity>> entities;
    private Actor camera;

    /**
     * Reads the content on the file with the given filename and builds the
     * level according to it.
     * 
     * @param filename The name of the file with the level information.
     * @throws IOException If there was an error loading the file.
     */
    public void importLevel(String filename) throws IOException {
    }

    /**
     * Sets the camera view to the view of the given Actor.
     */
    public void setCamera(Actor actor) {
    }

    /**
     * Adds the given Entity to the level.
     * 
     * @throws IllegalArgumentException If the new Entity is null.
     */
    public void addEntity(Entity entity) {
    }

	/**
	 * Returns the Entity of the given type that intersects with the given
	 * line and that is closest to the origin point of the given line. Null
	 * is returned if no Entity intersected with the line. Entities in the
	 * opposite direction of the Line will not be included.
	 */
	public Entity getIntersectingEntity(String type, Line line) {
		return null;
	}

	/**
	 * Returns a Set of Entities of the given type that intersects with
	 * the given Entity. The returned Set is empty if there were no Entities
	 * that intersected with the given Entity.
	 */
	public Set<Entity> getIntersectingEntities(String type, Entity entity) {
		return null;
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
