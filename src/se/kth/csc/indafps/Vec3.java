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
        init(3);
    }

    /**
     * Initializes the vector using the specified coordinates.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public Vec3(float x, float y, float z) {
        init(3);
        setX(x);
        setY(y);
        setZ(z);
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
}
