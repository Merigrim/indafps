package se.kth.csc.indafps;

/**
 * A simple 3D line.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Line {
    // The coordinates of this line's origin
    private Vec3 origin;

    // The direction of this line.
    private Vec3 direction;

    /**
     * Initializes the line with the specified parameters.
     * 
     * @param origin The line's origin position in space
     * @param direction The direction of the line
     */
    public Line(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * Returns this line's origin.
     * 
     * @return This line's origin
     */
    public Vec3 getOrigin() {
        return origin;
    }

    /**
     * Returns this line's direction.
     * 
     * @return This line's direction
     */
    public Vec3 getDirection() {
        return direction;
    }

    /**
     * Sets this line's origin.
     * 
     * @param origin This line's new origin
     */
    public void setOrigin(Vec3 origin) {
        this.origin = origin;
    }

    /**
     * Sets this line's direction.
     * 
     * @param direction This line's new direction
     */
    public void setDirection(Vec3 direction) {
        this.direction = direction.normalize();
    }

    /**
     * Returns the intersection point of this line and another one.
     * 
     * @param other The other line to check for an intersection with
     * @return The position of the intersection. If no intersection was found,
     *         return null
     */
    /*
     * public Vec3 intersects(Line other) { // Infinite non-parallel lines will
     * always intersect at some point
     * 
     * Vec3 pos = null;
     * 
     * return pos; }
     */

    /**
     * Returns the intersection point of this line and the given plane.
     * 
     * @param plane The plane to check for an intersection with.
     * @return The position of the intersection. If no intersection was found,
     *         return null.
     */
    public Vec3 intersects(Plane plane) {
        return Geometry.intersects(this, plane);
    }

    /**
     * Returns the intersection point of this line and the given parallel.
     * 
     * @param parallel The parallel to check for an intersection with.
     * @return The position of the intersection. If no intersection was found,
     *         return null.
     */
    public Vec3 intersects(Parallelogram parallel) {
        return Geometry.intersects(this, parallel);
    }
}
