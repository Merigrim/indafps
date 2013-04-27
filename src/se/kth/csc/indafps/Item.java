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

    public Item(Vec3 position) {
        super(position);
        owner = null;
    }

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
