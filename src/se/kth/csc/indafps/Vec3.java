package se.kth.csc.indafps;

/**
 * A simple 3D vector class.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Vec3 extends Vec {
    /**
     * Initializes a default 3D vector.
     */
    public Vec3() {
        super(3);
    }

    /**
     * Initializes the vector using the specified coordinates.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public Vec3(float x, float y, float z) {
        super(3);
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Initializes the vector using the specified vector.
     */
    public Vec3(Vec v) {
        super(3);
        set(0, v.get(0));
        set(1, v.get(1));
        set(2, v.get(2));
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
     * @param y The new y coordinate
     */
    public void setY(float y) {
        set(1, y);
    }

    /**
     * Sets this vector's z coordinate
     * 
     * @param z The new z coordinate
     */
    public void setZ(float z) {
        set(2, z);
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
     * Gets this vector's z coordinate
     * 
     * @return This vector's z coordinate
     */
    public float getZ() {
        return get(2);
    }

    /**
     * Returns a normalized version of the vector.
     * 
     * @return A normalized version of the vector.
     */
    public Vec3 normalize() {
        Vec general = getNormal();
        Vec3 normalized = new Vec3(general.get(0), general.get(1),
                general.get(2));
        return normalized;
    }

    /**
     * Returns the cross product of this 3D vector and another one.
     * 
     * @param other The other 3D vector
     * @return The cross product of the vectors
     */
    public Vec3 cross(Vec3 other) {
        float x = getX(), y = getY(), z = getZ();
        float ox = other.getX(), oy = other.getY(), oz = other.getZ();
        return new Vec3(y * oz - oy * z, ox * z - x * oz, x * oy - ox * y);
    }

    /**
     * Adds the two vectors.
     * 
     * @param other The other vector
     * @return The sum vector
     */
    public Vec3 add(Vec3 other) {
        return new Vec3(super.add(other));
    }

    /**
     * Subtracts the two vectors.
     * 
     * @param other The other vector
     * @return The difference vector
     */
    public Vec3 sub(Vec3 other) {
        return new Vec3(super.sub(other));
    }

    /**
     * Negates all components of this vector.
     * 
     * @return The negated vector.
     */
    @Override
    public Vec3 negate() {
        return new Vec3(super.negate());
    }

    /**
     * Multiplies every element in the vector with the given number.
     * 
     * @param factor The number the elements will be multiplied with.
     * @return The multiplied vector.
     */
    @Override
    protected Vec3 mul(float factor) {
        return new Vec3(super.mul(factor));
    }
}
