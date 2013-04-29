package se.kth.csc.indafps;

import java.io.IOException;

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
    protected Vec4 color;

    protected boolean solid;

    protected Level level;

    protected Model model;

    public Entity() {
        position = new Vec3();
        rotation = new Vec3();
        scale = new Vec3();
        color = new Vec4();

        solid = false;
    }

    public Entity(Vec3 position) {
        this();
        setPosition(position);
    }

    /**
     * Associates this Entity with a level. When this Entity has associated with
     * a level, it is able to interact with other objects in the level.
     */
    public final void associateLevel(Level level) {
        this.level = level;
    }

    /**
     * Sets the texture to this Entity. If texture is null, texturing on the
     * object will be disabled.
     */
    public final void setTexture() {
    }

    /**
     * Sets whether this Entity is solid or not.
     */
    public final void setSolid(boolean value) {
        solid = value;
    }

    /**
     * Positions the center of this Entity at the position representation of the
     * given vector.
     */
    public void setPosition(Vec3 vec) {
        position.copy(vec);
    }

    /**
     * Set the scale of this Entity.
     */
    public final void setSize(Vec3 vec) {
        scale.copy(vec);
    }

    /**
     * Set the rotation of this Entity.
     */
    public final void setRotation(Vec3 vec) {
        rotation.copy(vec);
    }

    /**
     * Set the color of the Entity.
     */
    public final void setColor(Vec4 vec) {
        color.copy(vec);
    }

    /**
     * @return The vector representation of the position.
     */
    public final Vec3 getPosition() {
        return new Vec3(position);
    }

    /**
     * @return The vector representation of the rotation.
     */
    public final Vec3 getRotation() {
        return new Vec3(rotation);
    }

    /**
     * @return The vector representation of the scale.
     */
    public final Vec3 getScale() {
        return new Vec3(scale);
    }

    /**
     * @return The vector representation of the color.
     */
    public final Vec4 getColor() {
        return new Vec4(color);
    }

    /**
     * @return The level instance associated with this entity.
     */
    public final Level getLevel() {
        return level;
    }

    /**
     * @return True or false whether the Entity is solid or not.
     */
    public final boolean isSolid() {
        return solid;
    }

    /**
     * Tests if the Line intersects with this Entity and returns the point where
     * the Line and this Entity. Null is returned if there is no intersection.
     * 
     * @return The point where the Line and this Entity intersects.
     */
    public final Vec3 testIntersection(Line line) {
        return null;
    }

    /**
     * Tests if the other Entity intersects with this Entity. It is guaranteed
     * that A.testIntersection(B) is equivalence with B.testIntersection(A).
     * 
     * @return True if there's an intersection, otherwise false.
     */
    public final boolean testIntersection(Entity entity) {
        return false;
    }

    /**
     * Returns the 3D model associated with this entity.
     * 
     * @return The 3D model associated with this entity
     */
    public final Model getModel() {
        return model;
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
