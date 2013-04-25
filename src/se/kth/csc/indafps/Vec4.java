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
        init(4);
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
        init(4);
        setR(r);
        setG(g);
        setB(b);
        setA(a);
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
}
