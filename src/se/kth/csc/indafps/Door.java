package se.kth.csc.indafps;

import java.io.IOException;

/**
 * A game object that blocks the path of Actors when locked.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */

public class Door extends Solid {
    private boolean locked;
    private boolean open;
    private int direction;

    /**
     * The door will initially be locked.
     */
    public Door(Vec3 position) {
        super(position);
        locked = true;
        open = false;
        direction = 0;
        model = ModelManager.get("data/cube.obj");
        setTexture(TextureManager.get("data/door.jpg"));
    }

    /**
     * Unlocks the door.
     */
    public void unlock() {
        if (level.getPlayer().requestItem("Key") != null) {
            locked = false;
        }
    }

    @Override
    public void interact() {
        if (locked) {
            unlock();
        } else if (!open) {
            open();
        } else {
            close();
        }
    }

    /**
     * Opens the door. Returns false if the door is locked or already open.
     */
    public boolean open() {
        open = !locked && !open;
        return open;
    }

    /**
     * Closes the door. Returns false if the door is already closed or if it is
     * locked.
     */
    public boolean close() {
        if (!open || locked) {
            return false;
        }
        open = false;
        return true;
    }

    /**
     * Check the position of the surrounding walls and determine which direction
     * of the Door is most suitable. The Door will then change its direction to
     * the newly found direction.
     * 
     * @return The direction of the Door.
     */
    public int determineDirection() {
        return 0;
    }

    /**
     * @return True if the door is locked.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * @return True if the door is open.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @return The direction of the door.
     */
    public int getDirection() {
        return direction;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        Vec3 p = getPosition();
        if (open && p.getY() < 1.5f) {
            setPosition(new Vec3(p.getX(), Math.min(p.getY() + dt, 1.5f),
                    p.getZ()));
        } else if (!open && p.getY() > 0.5f) {
            setPosition(new Vec3(p.getX(), Math.max(p.getY() - dt, 0.5f),
                    p.getZ()));
        }
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void handleInput() {
    }
}
