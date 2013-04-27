package se.kth.csc.indafps;

/**
 * Package that restores the health of the Player when picked up.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class HealthPackage extends Package {
	public HealthPackage(Vec3 position, int quantity) {
		super(position, quantity);
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
