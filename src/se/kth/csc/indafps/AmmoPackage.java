package se.kth.csc.indafps;

/**
 * Package that restores the ammo of the Player when picked up.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class AmmoPackage extends Package {
	public AmmoPackage(Vec3 position, int quantity) {
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
