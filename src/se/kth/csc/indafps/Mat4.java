package se.kth.csc.indafps;

/**
 * A simple square 4x4 matrix class.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Mat4 extends Mat {
    public Mat4() {
        super(4);
    }

    public Mat4(float[] raw) {
        super(4);
        System.arraycopy(raw, 0, mat, 0, mat.length);
    }

    /**
     * Multiplies this 4x4 matrix with the specified R^4 vector.
     * 
     * @param vec The vector to perform the multiplication with
     * @return The resulting vector
     * @throws ArithmeticException If the dimensions do not match
     */
    public Vec4 mul(Vec4 vec) throws ArithmeticException {
        Vec general = super.mul(vec);
        Vec4 ret = new Vec4();
        ret.copy(general);
        return ret;
    }

    /**
     * Creates an orthographic projection matrix.
     * 
     * @param left The left vertical clipping plane
     * @param right The right vertical clipping plane
     * @param top The top horizontal clipping plane
     * @param bottom The bottom horizontal clipping plane
     * @param near The near clipping plane
     * @param far The far clipping plane
     * @return The created orthographic projection matrix
     */
    public static Mat4 ortho(float left, float right, float top, float bottom,
            float near, float far) {
        Mat4 ret = new Mat4();
        ret.set(0, 0, 2.0f / (right - left));
        ret.set(0, 3, -(right + left) / (right - left));
        ret.set(1, 1, 2.0f / (top - bottom));
        ret.set(1, 3, -(top + bottom) / (top - bottom));
        ret.set(2, 2, -2.0f / (far - near));
        ret.set(2, 3, -(far + near) / (far - near));
        return ret;
    }

    /**
     * Creates a perspective projection matrix.
     * 
     * @param fov The field of view
     * @param aspect The aspect ratio of the view
     * @param near The near clipping plane
     * @param far The far clipping plane
     * @return The created perspective projection matrix
     */
    public static Mat4 perspective(float fov, float aspect, float near,
            float far) {
        Mat4 ret = new Mat4();
        float range = (float)Math.tan((fov * 0.5f) * (Math.PI / 180.0f)) * near;
        float left = -range * aspect;
        float right = range * aspect;
        float bottom = -range;
        float top = range;

        ret.set(0, 0, (2.0f * near) / (right - left));
        ret.set(1, 1, (2.0f * near) / (top - bottom));
        ret.set(2, 2, -(far + near) / (far - near));
        ret.set(2, 3, -1.0f);
        ret.set(3, 2, -(2.0f * far * near) / (far - near));
        return ret;
    }

    /**
     * Translates this matrix by the specified distance.
     * 
     * @param delta The distance to translate this matrix by.
     */
    public void translate(Vec3 delta) {
        mat[3] += delta.getX();
        mat[7] += delta.getY();
        mat[11] += delta.getZ();
    }

    /**
     * Rotates this matrix by the specified angles.
     * 
     * @param rotation A vector expressing the rotation along the three axises.
     */
    public void rotate(Vec3 rotation) {
        // TODO Do not use, not done yet
        float x = rotation.getX();
        float y = rotation.getY();
        float z = rotation.getZ();
        mat[0] = (float)(Math.cos(y) * Math.cos(z));
        mat[1] = (float)(Math.sin(x) * Math.sin(y) * Math.cos(z) - Math.cos(x)
                * Math.sin(z));
    }
}
