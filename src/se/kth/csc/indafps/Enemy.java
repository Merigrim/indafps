package se.kth.csc.indafps;

/**
 * Enemy object that are hostile to the Player.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Enemy extends Actor {
    private Item itemDrop;

    /**
     * @return True if the given Entity is inside the field of view of this
     *         Enemy.
     */
    private boolean isInSight(Entity entity) {
        return false;
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render() {
    }

    @Override
    public void handleInput() {
    }
}
