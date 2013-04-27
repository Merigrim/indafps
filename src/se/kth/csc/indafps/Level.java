package se.kth.csc.indafps;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Level manages the game objects.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Level implements GameComponent {
    private Map<String, Set<Entity>> entities;

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
     * Adds the given Entity to this Level and associates the Entity with this
	 * Level. This function will do nothing if entity is set to null.
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

	/**
	 * Returns the Set of Entites of the given type. Returns null if there
	 * are no Set for the given type.
	 */
	public Set<Entity> getEntities(String type) {
		return entities.get(type);
	}

	@Override
    public void update(float dt) {
    }

	@Override
    public void render(Renderer renderer) {
    }

	@Override
    public void handleInput() {
    }
}
