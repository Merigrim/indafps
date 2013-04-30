package se.kth.csc.indafps;

/**
 * Geometric box in 3 dimensional space. The box cannot be rotated.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-30
 */
public class Box {
	private Vec3 position;
	private Vec3 scale;

	public Box(Vec3 position, Vec3 scale) {
		this.position = new Vec3(position);
		this.scale = new Vec3(scale);
	}

	/**
	 * Positions the center of this Box at the given vector.
	 */
	public void setPosition(Vec3 vec) {
        position.copy(vec);
	}

	/**
	 * Set the scale of this Box.
	 */
	public void setScale(Vec3 vec) {
        scale.copy(vec);
	}

	/**
	 * @return The position of the Box.
	 */
	public Vec3 getPosition() {
		return new Vec3(position);
	}

	/**
	 * @return The scale of the Box.
	 */
	public Vec3 getScale() {
		return new Vec3(scale);
	}

	/**
	 * Returns the coordinates of the corners of the box.
	 *
	 * @return An array with 8 vectors describing the corners of the
	 * box.
	 */
	public Vec3[] getCorners() {
		Vec3 corners[] = new Vec3[8];
		corners[0] = position.add(scale.mul(-0.5f));
		corners[1] = corners[0].add(new Vec3(scale.getX(), 0.0f, 0.0f));
		corners[2] = corners[0].add(new Vec3(0.0f, scale.getY(), 0.0f));
		corners[3] = corners[0].add(new Vec3(0.0f, 0.0f, scale.getZ()));
		corners[4] = position.add(scale.mul(0.5f));
		corners[5] = corners[4].add(new Vec3(-scale.getX(), 0.0f, 0.0f));
		corners[6] = corners[4].add(new Vec3(0.0f, -scale.getY(), 0.0f));
		corners[7] = corners[4].add(new Vec3(0.0f, 0.0f, -scale.getZ()));
		return corners;
	}

	/**
	 * Tests if the other Box intersects with this Box. It is guaranteed
     * that A.testIntersection(B) is equivalence with B.testIntersection(A).
     * 
     * @return True if there's an intersection, otherwise false.
     */
	public boolean testIntersection(Box box) {
		return Geometry.intersects(this, box);
	}
}
