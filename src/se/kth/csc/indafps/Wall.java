package se.kth.csc.indafps;

/**
 * A game object that blocks the movement of Actors.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Wall extends Solid {
    public Wall(Vec3 position) {
        super(position);
        model = ModelManager.get("data/cube.obj");
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
