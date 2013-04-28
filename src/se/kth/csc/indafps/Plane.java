package se.kth.csc.indafps;

/**
 * A 3 dimensional plane.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */

public class Plane {
	private Vec3 points[];

	/**
	 * Constructs a plane containing the three given vectors.
	 *
	 * @param p[1-3] Distinct points in the plane.
	 * @throws IllegalArgumentException If two of the given points are
	 * coinciding.
	 */
	public Plane(Vec3 p1, Vec3 p2, Vec3 p3) {
		points = new Vec3[3];
		if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
			throw new IllegalArgumentException("Two or all of the given "
					+ "points are coinciding."); 
		}
		points[0] = p1;
		points[1] = p2;
		points[2] = p3;
	}

	/**
	 * Calculates the normal of the plane by using the cross product
	 * (p2 - p1) x (p3 - p1). The direction of the normal can be determined by
	 * the "right hand rule". The returned vector is normalized.
	 *
	 * @return The normal of the plane.
	 */
	public Vec3 getNormal() {
		Vec3 p1 = points[1].sub(points[0]);
		Vec3 p2 = points[2].sub(points[0]);
		return p1.cross(p2).normalize();
	}

	/**
	 * Returns a point in the plane, or more specifically, p1.
	 *
	 * @return The p1 vector.
	 */
	public Vec3 getPoint() {
		return points[0];
	}
}
