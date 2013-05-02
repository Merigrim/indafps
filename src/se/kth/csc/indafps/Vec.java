package se.kth.csc.indafps;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

/**
 * A vector class for vector's of length N.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Vec {
    // The vector components
    private float[] components;

    /**
     * Initializes the vector with the specified number of components.
     * 
     * @param n The number of components to use
     */
    protected Vec(int n) {
        components = new float[n];
    }

    private void checkComponents(Vec other)
            throws ArrayIndexOutOfBoundsException {
        if (other.components.length != components.length) {
            throw new ArrayIndexOutOfBoundsException(String.format(
                    "Vec component lengths do not match: %d != %d.",
                    dimension(), other.dimension()));
        }
    }

    /**
     * Copies the properties of the other vector to this vector.
     */
    public void copy(Vec other) throws ArrayIndexOutOfBoundsException {
        checkComponents(other);
        System.arraycopy(other.components, 0, components, 0, dimension());
    }

    /**
     * Returns the specified component
     * 
     * @param n The component to get
     * @return The requested component
     */
    public float get(int n) {
        return components[n];
    }

    /**
     * Sets the specified component
     * 
     * @param n The component to set
     */
    protected void set(int n, float value) {
        components[n] = value;
    }

    /**
     * Returns the length of this vector.
     * 
     * @return The length of this vector
     */
    public float getLength() {
        float sum = 0;
        for (int i = 0; i < dimension(); ++i) {
            float c = get(i);
            sum += c * c;
        }
        return (float)Math.sqrt(sum);
    }

    /**
     * @return The vector normalized
     */
    protected Vec getNormal() {
        Vec normalized = new Vec(dimension());
        float length = getLength();
        for (int i = 0; i < dimension(); ++i) {
            normalized.set(i, get(i) / length);
        }
        return normalized;
    }

    /**
     * @return True if the other vector is identical to this vector, otherwise
     *         false.
     */
    @Override
    public boolean equals(Object other) {
        Vec vec = (Vec)other;
        if (dimension() == vec.dimension()) {
            for (int i = 0; i < dimension(); ++i) {
                if (get(i) != vec.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the dimension of this vector.
     * 
     * @return The dimension of this vector
     */
    public int dimension() {
        return components.length;
    }

    /**
     * Negates all components of this vector.
     * 
     * @return The negated vector.
     */
    protected Vec negate() {
        Vec res = new Vec(dimension());
        for (int i = 0; i < dimension(); ++i) {
            res.set(i, -get(i));
        }
        return res;
    }

    /**
     * Adds the two vectors.
     * 
     * @param other The other vector
     * @return The sum vector
     */
    protected Vec add(Vec other) {
        Vec res = new Vec(dimension());
        for (int i = 0; i < dimension(); ++i) {
            res.set(i, get(i) + other.get(i));
        }
        return res;
    }

    /**
     * Subtracts the two vectors.
     * 
     * @param other The other vector
     * @return The difference vector
     */
    protected Vec sub(Vec other) {
        Vec res = new Vec(dimension());
        for (int i = 0; i < dimension(); ++i) {
            res.set(i, get(i) - other.get(i));
        }
        return res;
    }

    /**
     * Multiplies every element in the vector with the given number.
     * 
     * @param factor The number the elements will be multiplied with.
     * @return The multiplied vector.
     */
    protected Vec mul(float factor) {
        Vec res = new Vec(dimension());
        for (int i = 0; i < dimension(); ++i) {
            res.set(i, get(i) * factor);
        }
        return res;
    }

    /**
     * Returns the dot product of this and another vector
     * 
     * @param other The other vector
     * @return The dot product of this and another vector
     * @throws ArrayIndexOutOfBoundsException If the vectors are not of equal
     *             dimension.
     */
    public float dot(Vec other) throws ArrayIndexOutOfBoundsException {
        checkComponents(other);
        float sum = 0;
        for (int i = 0; i < dimension(); ++i) {
            sum += get(i) * other.get(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < dimension(); ++i) {
            sb.append(" " + get(i) + (i != dimension() - 1 ? "," : ""));
        }
        sb.append(" )");
        return sb.toString();
    }

    public FloatBuffer toBuffer() {
        FloatBuffer ret = BufferUtils.createFloatBuffer(dimension());
        for (int i = 0; i < dimension(); ++i) {
            ret.put(get(i));
        }
        ret.flip();
        return ret;
    }

	/**
	 * Returns the angle in radians between this vector and the other vector.
	 * @param other The other vector.
	 * @return The angle between this vector and the other vector.
	 */
	public float angle(Vec other) throws ArrayIndexOutOfBoundsException{
		checkComponents(other);
		return (float) Math.acos(dot(other) / (getLength() * other.getLength()));
	}
}
