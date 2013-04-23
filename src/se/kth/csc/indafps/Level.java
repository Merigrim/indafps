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
	 * Reads the content on the file with the given filename
	 * and builds the level according to it.
	 * @param filename The name of the file with the level
	 * information.
	 * @throws IOException If there was an error loading the
	 * file.
	 */
	public void importLevel(String filename)
		throws IOException {
	}

	/**
	 * Sets the camera view to the view of the given Actor.
	 */
	public void setCamera(Actor actor) {
	}

	/**
	 * Adds the given Entity to the level.
	 * @throws IllegalArgumentException If the new Entity is
	 * null.
	 */
	public void addEntity(Entity entity) {
	}

	public void update(float dt) {
	}

	public void render() {
	}

	public void handleInput() {
	}
}
