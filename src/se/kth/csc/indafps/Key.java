package se.kth.csc.indafps;

import java.io.IOException;

/**
 * Abstract base class for every game object.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Key extends Item {
    public Key(Vec3 position) {
        super(position);
        try {
            model = ModelManager.get("data/key.obj");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setRotation(new Vec3((float)Math.PI * 0.25f, 0.0f,
                (float)Math.PI * 1.45f));
        setBoundingSphereRadius(0.25f);
    }

    @Override
    public void update(float dt) {
        setRotation(getRotation().add(new Vec3(0, dt * (float)Math.PI * 2.0f, 0)));
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
