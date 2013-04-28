package se.kth.csc.indafps;

/**
 * Collection of geometrical functions.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-28
 */

public class Geometry {
	/**
	 * Returns the intersection point of the given line and plane.
	 *
	 * @param line The line to check for an intersection with.
	 * @param plane The plane to check for an intersection with.
	 * @return The position of the intersection. If no intersection was found,
	 * return null.
	 */
	public static Vec3 intersects(Line line, Plane plane) {
		Vec3 planePoint = plane.getPoint();
		Vec3 planeNormal = plane.getNormal();
		Vec3 lineDirection = line.getDirection();
		Vec3 lineOrigin = line.getOrigin();
		float denominator = lineDirection.dot(planeNormal);
		if (denominator != 0) {
			float numerators = planePoint.sub(lineOrigin).dot(planeNormal);
			float d = numerators / denominator;
			return lineDirection.mul(d).add(lineOrigin);
		}
		return null;
	}
}
