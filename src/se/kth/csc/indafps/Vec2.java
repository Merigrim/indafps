package se.kth.csc.indafps;

/**
 * A simple 2D vector class.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Vec2 extends Vec {
    /**
     * Initializes a default 2D vector.
     */
    public Vec2() {
        super(2);
    }

    /**
     * Initializes the vector using the specified coordinates.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Vec2(float x, float y) {
        super(2);
        setX(x);
        setY(y);
    }

	/**
	 * Initializes the vector using the specified vector.
	 */
	public Vec2(Vec v) {
		super(2);
		set(0, v.get(0));
		set(1, v.get(1));
	}

    /**
     * Sets this vector's x coordinate
     * 
     * @param x The new x coordinate
     */
    public void setX(float x) {
        set(0, x);
    }

    /**
     * Sets this vector's y coordinate
     * 
     * @param x The new y coordinate
     */
    public void setY(float y) {
        set(1, y);
    }

    /**
     * Gets this vector's x coordinate
     * 
     * @return This vector's x coordinate
     */
    public float getX() {
        return get(0);
    }

    /**
     * Gets this vector's y coordinate
     * 
     * @return This vector's y coordinate
     */
    public float getY() {
        return get(1);
    }

    /**
     * Returns a normalized version of the vector.
     * 
     * @return A normalized version of the vector.
     */
    public Vec2 normalize() {
        Vec general = getNormal();
        Vec2 normalized = new Vec2(general.get(0), general.get(1));
        return normalized;
    }

    /**
     * Adds the two vectors.
     * 
     * @param other The other vector
     * @return The sum vector
     */
	public Vec2 add(Vec2 other) {
		return new Vec2(super.add(other));
	}

    /**
     * Subtracts the two vectors.
     * 
     * @param other The other vector
     * @return The difference vector
     */
	public Vec2 sub(Vec2 other) {
		return new Vec2(super.sub(other));
	}

	/**
	 * Multiplies every element in the vector with the given number.
	 *
	 * @param factor The number the elements will be multiplied with.
	 * @return The multiplied vector.
	 */
	protected Vec2 mul(float factor) {
		return new Vec2(super.mul(factor));
	}
}
