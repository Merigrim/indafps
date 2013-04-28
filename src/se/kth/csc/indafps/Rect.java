package se.kth.csc.indafps;

/**
 * A simple rectangle class, which can be used to represent the dimensions of an
 * object.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Rect {
    // The coordinates of the rectangle
    public int left, right, top, bottom;

    /**
     * Default constructor.
     */
    public Rect() {
        this.left = this.right = this.top = this.bottom = 0;
    }

    /**
     * Initializes the rectangle using the specified values.
     * 
     * @param left The leftmost coordinate of the rectangle
     * @param right The rightmost coordinate of the rectangle
     * @param top The topmost coordinate of the rectangle
     * @param bottom The bottommost coordinate of the rectangle
     */
    public Rect(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d, %d)", left, top, right, bottom);
    }
}
