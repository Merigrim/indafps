package se.kth.csc.indafps;

public class Sphere {
    private Vec3 center;
    private float radius;

    /**
     * Creates a new sphere with the specified center point and radius.
     * 
     * @param center The center point of the sphere
     * @param radius The radius of the sphere
     */
    public Sphere(Vec3 center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Sets the center point of this sphere.
     * 
     * @param center The new center point of this sphere
     */
    public void setCenter(Vec3 center) {
        this.center.copy(center);
    }

    /**
     * @return The center point of this sphere
     */
    public Vec3 getCenter() {
        return new Vec3(center);
    }

    /**
     * Sets the radius of this sphere.
     * 
     * @param radius The new radius of this sphere
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * @return The radius of this sphere
     */
    public float getRadius() {
        return radius;
    }
}
