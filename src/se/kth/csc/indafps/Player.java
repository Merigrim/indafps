package se.kth.csc.indafps;

import java.util.Set;
import java.util.HashSet;

import org.lwjgl.input.Mouse;

/**
 * The actor controlled by the player.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Player extends Actor {
    private Set<Item> inventory;

    public Player(Vec3 position) {
        this(position, 100, 12);
    }

    /**
     * Constructs a Player with specified maximum amount of health and ammo.
     * 
     * @param maxHealth The maximum health of this Player
     * @param maxAmmo The maximum ammo of this Player
     */
    public Player(Vec3 position, int maxHealth, int maxAmmo) {
        super(position, maxHealth, maxAmmo);
        inventory = new HashSet<Item>();
    }

    /**
     * Adds the Item to this Player inventory and makes this Player to the owner
     * of the Item. The Item will not be added to the inventory if it already
     * exist in the inventory.
     * 
     * @param item The Item to be added.
     */
    public void pickUp(Item item) {
        if (inventory.add(item)) {
            item.setOwner(this);
        }
    }

    /**
     * Searches for the Item with the given class name and returns the first
     * result. Null is returned if no matching Item was found.
     * 
     * @param type The Class name of the requested Item.
     * @return The first found Item of the given type. Null if no item was
     *         found.
     */
    public Item searchItem(String type) {
        for (Item item : inventory) {
            if (item.getClass().getName() == type) {
                return item;
            }
        }
        return null;
    }

    /**
     * Requests for the Item with the given class name and returns the first
     * result. The returned Item will be removed from the inventory of this
     * Player and the owner of the Item will be set to null. Null is returned if
     * no matching Item was found.
     * 
     * @param type The Class name of the requested Item.
     * @return The first found Item of the given type. Null if no item was
     *         found.
     */
    public Item requestItem(String type) {
        for (Item item : inventory) {
            if (item.getClass().getName() == type) {
                inventory.remove(item);
                item.setOwner(null);
                return item;
            }
        }
        return null;
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
        float dx = Mouse.getDX();
        float dy = Mouse.getDY();
        camera.move(dx, -dy);
    }
}
