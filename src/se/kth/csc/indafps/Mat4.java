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
     * @param near The near clipping plane
     * @param far The far clipping plane
     * @param fov The field of view
     * @return The created perspective projection matrix
     */
    public static Mat4 perspective(float near, float far, float fov) {
        Mat4 ret = new Mat4();
        float scale = 1.0f / (float)Math.tan((fov * 0.5) * (Math.PI / 180.0f));
        ret.set(0, 0, scale);
        ret.set(1, 1, scale);
        ret.set(2, 2, -far / (far - near));
        ret.set(2, 3, -1.0f);
        ret.set(3, 2, -(far * near) / (far - near));
        ret.set(3, 3, 0);
        return ret;
    }
}
