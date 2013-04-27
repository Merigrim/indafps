package se.kth.csc.indafps;

import java.util.ArrayList;

/**
 * The actor controlled by the player.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Player extends Actor {
    private ArrayList<Item> inventory;

	public Player(Vec3 position) {
		super(position, 100, 12);
		inventory = new ArrayList<Item>();
	}

    /**
     * Adds the given item to the Player inventory.
     */
    public void pickUp(Item iem) {
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
