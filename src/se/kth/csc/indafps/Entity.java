package se.kth.csc.indafps;

/**
 * Abstract base class for every game object.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Entity implements GameComponent {
    protected Vec3 position;
    protected Vec3 rotation;
    protected Vec3 scale;
    protected Vec3 velocity;
    protected Vec3 color;

    protected boolean solid;

    public Entity() {
    }

    public Entity(Vec3 position) {
    }

    /**
     * Sets the texture to this Entity. If texture is null, texturing on the
     * object will be disabled.
     */
    public void setTexture() {
    }

    /**
     * Sets whether the Entity is solid or not.
     */
    public void setSolid(boolean solid) {
    }

    /**
     * @return The vector representation of the position.
     */
    public Vec3 getPosition() {
        return null;
    }

    /**
     * @return The vector representation of the rotation.
     */
    public Vec3 getRotation() {
        return null;
    }

    /**
     * @return The vector representation of the scale.
     */
    public Vec3 getScale() {
        return null;
    }

    /**
     * @return The vector representation of the velocity.
     */
    public Vec3 getVelocity() {
        return null;
    }

    /**
     * @return The vector representation of the color.
     */
    public Vec3 getColor() {
        return null;
    }

    /**
     * @return True or false whether the Entity is solid or not.
     */
    public boolean isSolid() {
        return false;
    }

    /**
     * Tests if the line intersects with this Entity.
     * 
     * @return True if there's an intersection, otherwise false.
     */
    public boolean testIntersection(Line line) {
        return false;
    }

    /**
     * Tests if the other Entity intersects with this Entity. It is guaranteed
     * that A.testIntersection(B) is equivalence with B.testIntersection(A).
     * 
     * @return True if there's an intersection, otherwise false.
     */
    public boolean testIntersection(Entity entity) {
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
