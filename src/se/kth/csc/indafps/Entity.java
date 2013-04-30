package se.kth.csc.indafps;

/**
 * Abstract base class for every game object.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public abstract class Entity implements GameComponent {
	protected Box box;
    protected Vec3 rotation;
    protected Vec4 color;

    protected boolean solid;

    protected Level level;

    protected Model model;

    public Entity() {
		box = new Box(new Vec3(0.0f, 0.0f, 0.0f), new Vec3(1.0f, 1.0f, 1.0f));
        rotation = new Vec3();
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
    public void associateLevel(Level level) {
        this.level = level;
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
     * Positions the center of this Entity at the position representation of the
     * given vector.
     */
    public void setPosition(Vec3 vec) {
		box.setPosition(vec);
    }

    /**
     * Set the scale of this Entity.
     */
    public void setScale(Vec3 vec) {
		box.setScale(vec);
    }

    /**
     * Set the rotation of this Entity.
     */
    public void setRotation(Vec3 vec) {
        rotation.copy(vec);
    }

    /**
     * Set the color of the Entity.
     */
    public void setColor(Vec4 vec) {
        color.copy(vec);
    }

    /**
     * @return The vector representation of the position.
     */
    public Vec3 getPosition() {
        return box.getPosition();
    }

    /**
     * @return The vector representation of the rotation.
     */
    public Vec3 getRotation() {
        return new Vec3(rotation);
    }

    /**
     * @return The vector representation of the scale.
     */
    public Vec3 getScale() {
        return box.getScale();
    }

    /**
     * @return The vector representation of the color.
     */
    public Vec4 getColor() {
        return new Vec4(color);
    }

    /**
     * @return The level instance associated with this entity.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return True or false whether the Entity is solid or not.
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Tests if the Line intersects with this Entity and returns the point where
     * the Line and this Entity. Null is returned if there is no intersection.
	 * The point closest to the origin of the line is returned.
     * 
     * @return The point where the Line and this Entity intersects.
     */
    public Vec3 testIntersection(Line line) {
		Vec3 intersects[] = new Vec3[6];
		int closest = -1;
		float closestLength = Float.MAX_VALUE;
		Vec3 c[] = box.getCorners();
		intersects[0] = line.intersects(new Parallelogram(c[1], c[2], c[3]));
		intersects[1] = line.intersects(new Parallelogram(c[1], c[2], c[4]));
		intersects[2] = line.intersects(new Parallelogram(c[1], c[3], c[4]));
		intersects[3] = line.intersects(new Parallelogram(c[5], c[6], c[7]));
		intersects[4] = line.intersects(new Parallelogram(c[5], c[6], c[8]));
		intersects[5] = line.intersects(new Parallelogram(c[5], c[7], c[8]));
		for (int i = 0; i < intersects.length; ++i) {
			if (intersects[i] != null) {
				Vec3 distanceVec = intersects[i].sub(line.getOrigin());
				float length = distanceVec.dot(line.getDirection());
				if (length < closestLength && length >= 0.0f) {
					closest = i;
					closestLength = length;
				}
			}
		}
		if (closest != -1) {
			return intersects[closest];
		}
        return null;
    }

    /**
     * Tests if the other Entity intersects with this Entity. It is guaranteed
     * that A.testIntersection(B) is equivalence with B.testIntersection(A).
     * 
     * @return True if there's an intersection, otherwise false.
     */
    public boolean testIntersection(Entity entity) {
		return box.testIntersection(entity.box);
    }

    /**
     * Returns the 3D model associated with this entity.
     * 
     * @return The 3D model associated with this entity
     */
    public Model getModel() {
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
