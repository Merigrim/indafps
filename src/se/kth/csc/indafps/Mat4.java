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

    public void setRow(int row, Vec4 vec) {
        for (int i = 0; i < 4; ++i) {
            mat[row * 4 + i] = vec.get(i);
        }
    }

    public Vec4 getRow(int row) {
        Vec4 rowVec = new Vec4(mat[row * 4 + 0], mat[row * 4 + 1],
                mat[row * 4 + 2], mat[row * 4 + 3]);
        return rowVec;
    }

    public Mat4 mul(Mat4 other) throws ArithmeticException {
        Mat general = super.mul(other);
        Mat4 product = new Mat4();
        product.copy(general);
        return product;
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
        /*
         * Mat4 ret = new Mat4(); float range = (float)Math.tan((fov * 0.5f) *
         * (Math.PI / 180.0f)) * near; float left = -range * aspect; float right
         * = range * aspect; float bottom = -range; float top = range;
         * 
         * ret.set(0, 0, (2.0f * near) / (right - left)); ret.set(1, 1, (2.0f *
         * near) / (top - bottom)); ret.set(2, 2, -(far + near) / (far - near));
         * ret.set(2, 3, -1.0f); ret.set(3, 2, -(2.0f * far * near) / (far -
         * near)); return ret;
         */
        Mat4 ret = new Mat4();
        float d2r = (float)Math.PI / 180.0f;
        float yScale = 1.0f / (float)Math.tan(d2r * fov / 2.0f);
        float xScale = yScale / aspect;
        float clipdif = near - far;
        ret.setRow(0, new Vec4(xScale, 0, 0, 0));
        ret.setRow(1, new Vec4(0, yScale, 0, 0));
        ret.setRow(2, new Vec4(0, 0, far / clipdif, (far * near) / clipdif));
        ret.setRow(3, new Vec4(0, 0, -1, 0));
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
    public void rotate(float angle, Vec3 axis) {
        float c = (float)Math.cos(angle);
        float s = (float)Math.sin(angle);
        Vec3 a = axis.normalize();
        Vec3 temp = a.mul(1.0f - c);

        Mat4 rot = new Mat4();
        rot.set(0, 0, c + temp.getX() * a.getX());
        rot.set(0, 1, 0 + temp.getX() * a.getY() + s * a.getZ());
        rot.set(0, 2, 0 + temp.getX() * a.getZ() - s * a.getY());

        rot.set(1, 0, 0 + temp.getY() * a.getX() - s * a.getZ());
        rot.set(1, 1, c + temp.getY() * a.getY());
        rot.set(1, 2, 0 + temp.getY() * a.getZ() + s * a.getX());

        rot.set(2, 0, 0 + temp.getZ() * a.getX() + s * a.getY());
        rot.set(2, 1, 0 + temp.getZ() * a.getY() - s * a.getX());
        rot.set(2, 2, c + temp.getZ() * a.getZ());

        Mat4 res = new Mat4();
        for (int i = 0; i < 3; ++i) {
            res.setRow(
                    i,
                    getRow(0).mul(rot.get(i, 0)).add(
                            getRow(1).mul(rot.get(i, 1)).add(
                                    getRow(2).mul(rot.get(i, 2)))));
        }
        res.setRow(3, getRow(3));
        copy(res);
    }
}
