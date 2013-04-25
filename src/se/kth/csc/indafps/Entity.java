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
    protected Vec4 color;

    protected boolean solid;

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
     * Sets the texture to this Entity. If texture is null, texturing on the
     * object will be disabled.
     */
    public void setTexture() {
    }

    /**
     * Sets whether this Entity is solid or not.
     */
    public void setSolid(boolean value) {
		solid = value;
    }

	/**
	 * Position this Entity at the given position so that the given
	 * position will have the same position as the center of this
	 * Entity.
	 */
	public void setPosition(Vec3 vec) {
		position.setX(vec.getX());
		position.setY(vec.getY());
		position.setZ(vec.getZ());
	}

	/**
	 * Set the scale of this Entity.
	 */
	public void setSize(Vec3 vec) {
		scale.setX(vec.getX());
		scale.setY(vec.getY());
		scale.setZ(vec.getZ());
	}

	/**
	 * Set the rotation of this Entity.
	 */
	public void setRotation(Vec3 vec) {
		rotation.setX(vec.getX());
		rotation.setY(vec.getY());
		rotation.setZ(vec.getZ());
	}

	/**
	 * Set the color of the Entity.
	 */
	public void setColor(Vec4 vec) {
		color.setR(vec.getR());
		color.setG(vec.getG());
		color.setB(vec.getB());
		color.setA(vec.getA());
	}

    /**
     * @return The vector representation of the position.
     */
    public Vec3 getPosition() {
		return new Vec3(position.getX(), position.getY(),
				position.getZ());
    }

    /**
     * @return The vector representation of the rotation.
     */
    public Vec3 getRotation() {
		return new Vec3(rotation.getX(), rotation.getY(),
				rotation.getZ());
    }

    /**
     * @return The vector representation of the scale.
     */
    public Vec3 getScale() {
		return new Vec3(scale.getX(), scale.getY(),
				scale.getZ());
    }

    /**
     * @return The vector representation of the color.
     */
    public Vec4 getColor() {
		return new Vec4(color.getR(), color.getG(),
				color.getB(), color.getA());
    }

    /**
     * @return True or false whether the Entity is solid or not.
     */
    public boolean isSolid() {
        return solid;
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
