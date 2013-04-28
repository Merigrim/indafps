package se.kth.csc.indafps;

/**
 * A parallelogram in 3 dimensions. It has mostly the same properties as Plane,
 * with the exception that a Parallelogram has defined corners.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-27
 */
public class Parallelogram extends Plane {
	/**
	 * Constructs a parallelogram out of the three points. The fourth point
	 * is automatically generated and is opposite to the first point.
	 *
	 * @throws IllegalArgumentException If two of the given points are
	 * coinciding.
	 */
	public Parallelogram(Vec3 p1, Vec3 p2, Vec3 p3) {
		super(p1, p2, p3);
	}

	/**
	 * Returns the vector of the requested corner of the parallelogram.
	 * The corners 1 and 4 are opposite to each other, and the corner 2
	 * and 3 are opposite to each other.
	 *
	 * @param index The corner that will be returned.
	 * @throws IllegalArgumentException If index is not 0, 1, 2 or 3.
	 * @return The requested corner.
	 */
	public Vec3 getCorner(int index) {
		if (index > 3 || index < 0) {
			throw new IllegalArgumentException("Index must be one of 0, 1, 2 "
					+ "or 3.");
		}
		if (index == 3) {
			return points[1].add(points[2].sub(points[0]));
		}
		return points[index];
	}

	/**
	 * Returns the intersection point of this parallelogram and the given line.
	 *
	 * @param line The line to check for an intersection with.
	 * @return The position of the intersection. If no intersection was found,
	 * return null.
	 */
	@Override
	public Vec3 intersects(Line line) {
		return Geometry.intersects(line, this);
	}
}
