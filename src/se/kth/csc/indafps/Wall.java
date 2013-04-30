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

	public void entityCollision(Set <Entity> entities) {
		for (Entity entity : entities) {
			if (testIntersection(entity)) {
				System.out.printf("HAHA");
			}
		}
	}

    @Override
    public void update(float dt) {
		entityCollision(level.getEntities("Player"));
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
