package se.kth.csc.indafps;

/**
 * Collection of geometrical functions.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-28
 */

public class Geometry {
    public static Vec3 intersects(Line line, Sphere sphere) {
        Vec3 org = line.getOrigin();
        Vec3 dir = line.getDirection();
        Vec3 center = sphere.getCenter();
        float rad = sphere.getRadius();
        Vec3 oc = org.sub(center);

        float a = dir.dot(dir);
        float b = 2.0f * dir.dot(org.sub(center));
        float c = oc.dot(oc) - rad * rad;

        float discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return null;
        }

        float distSqrt = (float)Math.sqrt(discriminant);
        float q = b < 0 ? (-b - distSqrt) * 0.5f : (-b + distSqrt) * 0.5f;

        float t0 = q / a;
        float t1 = c / q;

        if (t0 > t1) {
            float temp = t0;
            t0 = t1;
            t1 = temp;
        }

        if (t1 < 0) {
            return null;
        }

        if (t0 < 0) {
            return org.add(dir.mul(t1));
        }
        return org.add(dir.mul(t0));
    }

    /**
     * Returns the intersection point of the given line and plane.
     * 
     * @param line The line to check for an intersection with.
     * @param plane The plane to check for an intersection with.
     * @return The position of the intersection. If no intersection was found,
     *         return null.
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
     *         return null.
     */
    public static Vec3 intersects(Line line, Parallelogram parallel) {
        // TODO This formula does only work on rectangles.
        Vec3 intersection = intersects(line, (Plane)parallel);
        if (intersection != null) {
            Vec3 edge1 = parallel.getCorner(1).sub(parallel.getCorner(0));
            Vec3 edge2 = parallel.getCorner(2).sub(parallel.getCorner(0));
            Vec3 toPoint = intersection.sub(parallel.getCorner(0));
			Vec3 proj1 = projection(toPoint, edge1);
			Vec3 proj2 = projection(toPoint, edge2);
			float proj1Length = proj1.getLength();
			float proj2Length = proj2.getLength();
            if (proj1.dot(edge1) >= 0.0f && proj1Length <= edge1.getLength()
                    && proj2.dot(edge2) >= 0.0f && proj2Length <= edge2.getLength()) {
                return intersection;
            }
        }
        return null;
    }

    /**
     * Tests if the the two boxes intersects with each other.
     * 
     * @return True if the two boxes intersect, otherwise false.
     */
    public static boolean intersectsFalse(Box box1, Box box2) {
        // TODO This formula only detects if the boxes are colliding in 2D
        Vec3 corners1[] = box1.getCorners();
        Vec3 corners2[] = box2.getCorners();
        if (corners1[0].getX() > corners2[1].getX())
            return false;
        if (corners1[1].getX() < corners2[0].getX())
            return false;
        if (corners1[0].getZ() > corners2[3].getZ())
            return false;
        if (corners1[3].getZ() < corners2[0].getZ())
            return false;
        return true;
    }

    /**
     * Tests if the the two boxes intersects with each other.
     * 
     * @return True if the two boxes intersect, otherwise false.
     */
    public static boolean intersects(Box box1, Box box2) {
        Vec3 corners1[] = box1.getCorners();
        Vec3 corners2[] = box2.getCorners();
        Vec3 base1[] = new Vec3[3];
        Vec3 base2[] = new Vec3[3];
        float maximums1[] = new float[3];
        float maximums2[] = new float[3];
        for (int i = 0; i < 3; ++i) {
            base1[i] = corners1[i + 1].sub(corners1[0]);
            base2[i] = corners2[i + 1].sub(corners2[0]);
            maximums1[i] = base1[i].getLength();
            maximums2[i] = base2[i].getLength();
            base1[i].copy(base1[i].normalize());
            base2[i].copy(base2[i].normalize());
        }
        for (int i = 0; i < 3; ++i) {
            float dotProduct = base1[i].dot(corners2[0].sub(corners1[0]));
            float maxValue = dotProduct;
            float minValue = dotProduct;
            for (int j = 0; j < 3; ++j) {
                if (base2[j].get(i) > 0.0f) {
                    maxValue += base2[j].get(i) * maximums2[i];
                } else {
                    minValue += base2[j].get(i) * maximums2[i];
                }
            }
            if (maxValue < 0.0f || minValue > maximums1[i]) {
                return false;
            }
        }
        return true;
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
