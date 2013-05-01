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
	private Vec3 rotation;

	public Box(Vec3 position, Vec3 scale, Vec3 rotation) {
		this.position = new Vec3(position);
		this.scale = new Vec3(scale);
		this.rotation = new Vec3(rotation);
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
	 * Sets the rotation of this Box.
	 */
	public void setRotation(Vec3 vec) {
		rotation.copy(vec);
	}

	/**
	 * @return The reference to the position of the Box.
	 */
	public Vec3 getPosition() {
		return position;
	}

	/**
	 * @return The reference to the scale of the Box.
	 */
	public Vec3 getScale() {
		return scale;
	}

	/**
	 * @return The reference to the rotation of the Box.
	 */
	public Vec3 getRotation() {
		return rotation;
	}

	/**
	 * Returns the coordinates of the corners of the box.
	 *
	 * @return An array with 8 vectors describing the corners of the
	 * box.
	 */
	public Vec3[] getCorners() {
		Mat4 rotMat = new Mat4();
		Vec3 corners[] = new Vec3[8];
		rotMat.rotate(rotation.getX(), new Vec3(1.0f, 0.0f, 0.0f));
		rotMat.rotate(rotation.getY(), new Vec3(0.0f, 1.0f, 0.0f));
		rotMat.rotate(rotation.getZ(), new Vec3(0.0f, 0.0f, 1.0f));
		Vec3 rotScale = rotMat.mul(scale);
		corners[0] = position.sub(rotScale.mul(0.5f));
		corners[1] = corners[0].add(new Vec3(rotScale.getX(), 0.0f, 0.0f));
		corners[2] = corners[0].add(new Vec3(0.0f, rotScale.getY(), 0.0f));
		corners[3] = corners[0].add(new Vec3(0.0f, 0.0f, rotScale.getZ()));
		corners[4] = position.add(rotScale.mul(0.5f));
		corners[5] = corners[4].sub(new Vec3(rotScale.getX(), 0.0f, 0.0f));
		corners[6] = corners[4].sub(new Vec3(0.0f, rotScale.getY(), 0.0f));
		corners[7] = corners[4].sub(new Vec3(0.0f, 0.0f, rotScale.getZ()));
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
