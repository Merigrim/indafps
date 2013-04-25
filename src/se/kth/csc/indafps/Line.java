package se.kth.csc.indafps;

/**
 * A simple 3D line.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Line {
    // The coordinates of this line
    private Vec3 position;

    // The direction of this line.
    private Vec3 direction;

    /**
     * Initializes the line with the specified parameters.
     * 
     * @param position The line's position in space
     * @param direction The direction of the line
     */
    public Line(Vec3 position, Vec3 direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Returns this line's position.
     * 
     * @return This line's position
     */
    public Vec3 getPosition() {
        return position;
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
     * Sets this line's position.
     * 
     * @param position This line's new position
     */
    public void setPosition(Vec3 position) {
        this.position = position;
    }

    /**
     * Sets this line's direction.
     * 
     * @param direction This line's new direction
     */
    public void setDirection(Vec3 direction) {
        this.direction = direction;
    }
}
