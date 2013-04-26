package se.kth.csc.indafps;

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

    public void copy(Vec other) {
        if (other.components.length != components.length) {
            return;
        }
        System.arraycopy(other.components, 0, components, 0, components.length);
    }

    /**
     * Returns the specified component
     * 
     * @param n The component to get
     * @return The requested component
     */
    protected float get(int n) {
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
        for (int i = 0; i < components.length; ++i) {
            float c = get(i);
            sum += c * c;
        }
        return (float)Math.sqrt(sum);
    }

    protected Vec getNormal() {
        Vec normalized = new Vec(components.length);
        float length = getLength();
        for (int i = 0; i < components.length; ++i) {
            normalized.set(i, get(i) / length);
        }
        return normalized;
    }

    /**
     * @return True if the other vector is identical to this vector, otherwise
     *         false.
     */
    public boolean equals(Vec other) {
        if (components.length == other.components.length) {
            for (int i = 0; i < components.length; ++i) {
                if (components[i] != other.components[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
