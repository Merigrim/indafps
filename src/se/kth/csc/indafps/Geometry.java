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

	/**
	 * Returns the intersection point of the given line and parallelogram.
	 *
	 * @param line The line to check for an intersection with.
	 * @param parallel The parallelogram to check for an intersection with.
	 * @return The position of the intersection. If no intersection was found,
	 * return null.
	 */
	public static Vec3 intersects(Line line, Parallelogram parallel) {
		Vec3 intersection = intersects(line, (Plane) parallel);
		if (intersection != null) {
			Vec3 edge1 = parallel.getCorner(1).sub(parallel.getCorner(0));
			Vec3 edge2 = parallel.getCorner(2).sub(parallel.getCorner(0));
			Vec3 toPoint = intersection.sub(parallel.getCorner(0));
			float proj1Length = projection(toPoint, edge1).getLength();
			float proj2Length = projection(toPoint, edge2).getLength();
			if (proj1Length > 0.0f && proj1Length < edge1.getLength()
					&& proj2Length > 0.0f && proj2Length < edge2.getLength()){
				return intersection;
			}
		}
		return null;
	}

	/**
	 * Returns the projection of v1 to v2. The returned vector could be written
	 * in the form k*v2 where k is a real number.
	 *
	 * @param v1 The vector that will be projected to v2.
	 * @param v2 The vector that v1 will be projected to.
	 * @return The projection of v1 to v2.
	 */
	public static Vec3 projection(Vec3 v1, Vec3 v2) {
		float v2Length = v2.getLength();
		return v2.mul(v1.dot(v2) / (v2Length * v2Length));
	}
}
