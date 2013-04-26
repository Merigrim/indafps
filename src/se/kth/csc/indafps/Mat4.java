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
}
