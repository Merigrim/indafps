package se.kth.csc.indafps;

/**
 * Abstract base class for pickupable game objects.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Item extends Entity {
    private Entity owner;

<<<<<<< HEAD
	public Item(Vec3 position) {
		super(position);
		owner = null;
	}
=======
    public Item() {
        owner = null;
    }

    public Item(Vec3 position) {
        super(position);
    }
>>>>>>> 4cb79aad3e04ba410d2cb915e03d81769db15828

    /**
     * @return The current owner of this Item. Null is returned if this Item has
     *         no owners.
     */
    public Entity getOwner() {
        return owner;
    }

    /**
     * Changes the owner of the Item.
     */
    public void setOwner(Entity owner) {
        this.owner = owner;
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
