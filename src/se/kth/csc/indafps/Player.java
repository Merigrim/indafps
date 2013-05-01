package se.kth.csc.indafps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * The actor controlled by the player.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Player extends Actor {
    private Set<Item> inventory;
    // 0: W, 1: A, 2: S, 3: D
    private boolean[] wasd;
    private Item itemInSight;

    public Player(Vec3 position) {
        this(position, 100, 12);
        setScale(new Vec3(0.3f, 0.3f, 0.3f));
        wasd = new boolean[4];
        itemInSight = null;
        try {
            model = ModelManager.get("data/cube.obj");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        level.removeEntity(item);
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

    /**
     * Handles movement for the player among other things.
     * 
     * TODO: Add "other things".
     * 
     * @param dt The time difference since the last frame
     */
    @Override
    public void update(float dt) {
        itemInSight = null;
        Vec3 viewDir = camera.getViewDirection();
        viewDir.setY(0.0f);
        Vec3 complDir = new Vec3(viewDir.getZ(), 0.0f, -viewDir.getX());
        Vec3 movementDir = null;
        if (wasd[0] && wasd[1]) {
            movementDir = viewDir.add(complDir).normalize();
        } else if (wasd[0] && wasd[3]) {
            complDir = complDir.negate();
            movementDir = viewDir.add(complDir).normalize();
        } else if (wasd[0]) {
            movementDir = viewDir;
        } else if (wasd[2] && wasd[1]) {
            movementDir = viewDir.negate().add(complDir).normalize();
        } else if (wasd[2] && wasd[3]) {
            complDir = complDir.negate();
            movementDir = viewDir.negate().add(complDir).normalize();
        } else if (wasd[2]) {
            movementDir = viewDir.negate();
        } else if (wasd[1]) {
            movementDir = complDir;
        } else if (wasd[3]) {
            movementDir = complDir.negate();
        }
        if (movementDir != null) {
            setPosition(box.getPosition().add(
                    movementDir.normalize().mul(dt * 1.5f)));
        }

        for (Entity e : level.getEntities("Key")) {
            if (e.getPosition().sub(getPosition()).getLength() > 1.0f) {
                continue;
            }
            Vec3 v;
            Line ray = new Line(camera.getPosition(), camera.getViewDirection());
            if ((v = Geometry.intersects(ray, e.getBoundingSphere())) != null) {
                float distance = v.sub(camera.getPosition()).getLength();
                boolean wallBlocking = false;
                for (Entity w : level.getEntities("Wall")) {
                    Vec3 v2;
                    if ((v2 = w.testIntersection(ray)) != null
                            && v2.sub(camera.getPosition()).getLength() < distance) {
                        wallBlocking = true;
                        break;
                    }
                }
                if (!wallBlocking) {
                    itemInSight = (Item)e;
                }
            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        if (itemInSight != null) {
            renderer.render("[E] Pick up "
                    + itemInSight.getClass().getSimpleName(),
                    new Vec2(Display.getWidth() / 2.0f,
                            Display.getHeight() / 6.0f * 5.0f), new Vec2(0.5f,
                            0.0f));
        }
    }

    @Override
    public void handleInput() {
        float dx = Mouse.getDX();
        float dy = Mouse.getDY();
        camera.move(dx, -dy);

        wasd[0] = Keyboard.isKeyDown(Keyboard.KEY_W)
                || Keyboard.isKeyDown(Keyboard.KEY_UP);
        wasd[1] = Keyboard.isKeyDown(Keyboard.KEY_A)
                || Keyboard.isKeyDown(Keyboard.KEY_LEFT);
        wasd[2] = Keyboard.isKeyDown(Keyboard.KEY_S)
                || Keyboard.isKeyDown(Keyboard.KEY_DOWN);
        wasd[3] = Keyboard.isKeyDown(Keyboard.KEY_D)
                || Keyboard.isKeyDown(Keyboard.KEY_RIGHT);

        if (Keyboard.isKeyDown(Keyboard.KEY_E) && itemInSight != null) {
            pickUp(itemInSight);
        }
    }
}
