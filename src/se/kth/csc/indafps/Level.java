package se.kth.csc.indafps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
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
    private Vec2 size;

    /**
     * Initializes the level with an empty set of entities.
     */
    public Level() {
        entities = new HashMap<String, Set<Entity>>();
        size = new Vec2();
    }

	private void addFloorAndRoof(int x, int y) {
		addEntity(new Floor(new Vec3(x, 0, y)));
		addEntity(new Roof(new Vec3(x, 1.0f, y)));
	}

    /**
     * Reads the content on the file with the given filename and builds the
     * level according to it.
     * 
     * TODO: Add better error handling
     * 
     * @param filename The name of the file with the level information.
     * @throws IOException If there was an error loading the file.
     */
    public void importLevel(String filename) throws IOException {
        File f = new File(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(f)));
        try {
            String line;
            boolean mapBlock = false;
            int x = 0, y = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (mapBlock) {
                    for (char c : line.toCharArray()) {
                        switch (c) {
                        case '#': // Wall
                            addEntity(new Wall(new Vec3(x, 0.5f, y)));
                            break;
                        case '.': // Floor
							addFloorAndRoof(x, y);
                            break;
                        case ' ': // Void
                            break;
                        case '@': // Player
                            addEntity(new Player(new Vec3(x, 0.5f, y)));
							addFloorAndRoof(x, y);
                            break;
                        case 'e': // Enemy
                            addEntity(new Enemy(new Vec3(x, 0.5f, y)));
							addFloorAndRoof(x, y);
                            break;
                        case 'k': // Key
                            addEntity(new Key(new Vec3(x, 0.35f, y)));
							addFloorAndRoof(x, y);
                            break;
						case 'h': // Health package
							addEntity(new HealthPackage(new Vec3(x, 0.35f, y), 50));
							addFloorAndRoof(x, y);
                        }
                        ++x;
                    }
                    x = 0;
                    ++y;
                } else if (line.startsWith("@")) { // property
                    String[] parts = line.substring(1).split("=");
                    switch (parts[0]) {
                    case "width":
                        size.setX(Integer.parseInt(parts[1]));
                        break;
                    case "height":
                        size.setY(Integer.parseInt(parts[1]));
                        break;
                    case "map":
                        mapBlock = true;
                        break;
                    }
                }
            }
        } finally {
            br.close();
        }
    }

    /**
     * Adds the given Entity to this Level and associates the Entity with this
     * Level. This function will do nothing if entity is set to null.
     */
    public void addEntity(Entity entity) {
        if (entity == null) {
            return;
        }
        String key = entity.getClass().getSimpleName();
        if (!entities.containsKey(key)) {
            entities.put(key, new HashSet<Entity>());
        }
        entities.get(key).add(entity);
        entity.associateLevel(this);
    }

    public Entity removeEntity(Entity entity) {
        if (entity == null) {
            return null;
        }
        String key = entity.getClass().getSimpleName();
        if (!entities.containsKey(key)) {
            return null;
        }
        entities.get(key).remove(entity);
        return entity;
    }

    /**
     * Returns the Entity of the given type that intersects with the given line
     * and that is closest to the origin point of the given line. Null is
     * returned if no Entity intersected with the line. Entities in the opposite
     * direction of the Line will not be included.
	 * 
	 * @param type The type of entities to be tested.
	 * @param line The line that will be tested.
	 * @param exclusion An entity that will not be included in the tests. Often
	 * this will be the Entity that calls this function. If it's null no one
	 * of the entities will be excluded.
     */
    public Entity getIntersectingEntity(String type, Line line, Entity exclusion) {
		Entity closestEntity = null;
		float closestDistance = Float.MAX_VALUE;
        for (Entity entity : entities.get(type)) {
			if (entity == exclusion) {
				continue;
			}
			Vec3 intersection = entity.testIntersection(line);
			if (intersection != null) {
				float distance = intersection.sub(line.getOrigin()).getLength();
				if (distance < closestDistance) {
					closestEntity = entity;
					closestDistance = distance;
				}
			}
		}
		return closestEntity;
    }

    /**
     * Returns a Set of Entities of the given type that intersects with the
     * given Entity. The returned Set is empty if there were no Entities that
     * intersected with the given Entity.
     */
    public Set<Entity> getIntersectingEntities(String type, Entity entity) {
        return null;
    }

    /**
     * Returns the Set of Entites of the given type. Returns null if there are
     * no Set for the given type.
     */
    public Set<Entity> getEntities(String type) {
        return entities.get(type);
    }

    /**
     * Returns the level size in unit squares.
     * 
     * @return The level size in unit squares
     */
    public Vec2 getSize() {
        return new Vec2(size);
    }

    @Override
    public void update(float dt) {
        for (Set<Entity> vals : entities.values()) {
            for (Entity e : vals) {
                e.update(dt);
            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        Entity p = entities.get("Player").iterator().next();
        Player player = (Player)p;
        renderer.setCamera(player.getCamera());
        for (Set<Entity> vals : entities.values()) {
            for (Entity e : vals) {
                if (!e.getClass().getSimpleName().equals("Player")) {
                    renderer.render(e);
                }
            }
        }
        for (Set<Entity> vals : entities.values()) {
            // TODO: Fix this small hack, used to make sure player renders text
            // on top of entities
            for (Entity e : vals) {
                e.render(renderer);
            }
        }
    }

    @Override
    public void handleInput() {
        for (Set<Entity> vals : entities.values()) {
            for (Entity e : vals) {
                e.handleInput();
            }
        }
    }
}
