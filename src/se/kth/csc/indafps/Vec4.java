package se.kth.csc.indafps;

/**
 * A simple 4D vector class.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Vec4 extends Vec {
    /**
     * Initializes a default 4D vector.
     */
    public Vec4() {
        super(4);
    }

    /**
     * Initializes the vector using the specified coordinates.
     * 
     * @param r The r coordinate
     * @param g The g coordinate
     * @param b The b coordinate
     * @param a The a coordinate
     */
    public Vec4(float r, float g, float b, float a) {
        super(4);
        setR(r);
        setG(g);
        setB(b);
        setA(a);
    }

    /**
     * Initializes the vector using the specified vector.
     */
    public Vec4(Vec v) {
        super(4);
        if (v.dimension() > dimension()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < v.dimension(); ++i) {
            set(i, v.get(i));
        }
    }

    /**
     * Sets this vector's red component
     * 
     * @param r The new red component
     */
    public void setR(float r) {
        set(0, r);
    }

    /**
     * Sets this vector's green component
     * 
     * @param g The new green component
     */
    public void setG(float g) {
        set(1, g);
    }

    /**
     * Sets this vector's blue component
     * 
     * @param b The new blue component
     */
    public void setB(float b) {
        set(2, b);
    }

    /**
     * Sets this vector's alpha component
     * 
     * @param b The new alpha component
     */
    public void setA(float a) {
        set(3, a);
    }

    /**
     * Gets this vector's red component
     * 
     * @return This vector's red component
     */
    public float getR() {
        return get(0);
    }

    /**
     * Gets this vector's green component
     * 
     * @return This vector's green component
     */
    public float getG() {
        return get(1);
    }

    /**
     * Gets this vector's blue component
     * 
     * @return This vector's blue component
     */
    public float getB() {
        return get(2);
    }

    /**
     * Gets this vector's alpha component
     * 
     * @return This vector's alpha component
     */
    public float getA() {
        return get(3);
    }

    /**
     * Returns a normalized version of the vector.
     * 
     * @return A normalized version of the vector.
     */
    public Vec4 normalize() {
        Vec general = getNormal();
        Vec4 normalized = new Vec4(general.get(0), general.get(1),
                general.get(2), general.get(3));
        return normalized;
    }

    /**
     * Adds the two vectors.
     * 
     * @param other The other vector
     * @return The sum vector
     */
    public Vec4 add(Vec4 other) {
        return new Vec4(super.add(other));
    }

    /**
     * Subtracts the two vectors.
     * 
     * @param other The other vector
     * @return The difference vector
     */
    public Vec4 sub(Vec4 other) {
        return new Vec4(super.sub(other));
    }

    /**
     * Multiplies every element in the vector with the given number.
     * 
     * @param factor The number the elements will be multiplied with.
     * @return The multiplied vector.
     */
    @Override
    protected Vec4 mul(float factor) {
        return new Vec4(super.mul(factor));
    }
}
