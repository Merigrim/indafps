package se.kth.csc.indafps;

import java.nio.FloatBuffer;
import java.util.Arrays;

import org.lwjgl.BufferUtils;

/**
 * A matrix class for square matrices of size NxN. N should be properly defined
 * in extending classes.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Mat {
    // The raw float array used to represent the matrix
    protected float[] mat;

    // The width/height of the matrix
    protected int n;

    /**
     * Initializes the matrix as a NxN matrix.
     * 
     * @param n The width/height of the matrix
     * @throws Exception
     */
    protected Mat(int n) {
        this.n = n;
        mat = new float[n * n];
        setIdentity();
    }

    /**
     * Initializes this matrix as an identity matrix.
     */
    public void setIdentity() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                set(i, j, i == j ? 1 : 0);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mat other = (Mat)obj;
        if (n != other.n)
            return false;
        if (!Arrays.equals(mat, other.mat))
            return false;
        return true;
    }

    /**
     * Sets the value at the specified row and column.
     * 
     * @param row The row of the value
     * @param column The column of the value
     * @param value The new value to use
     */
    public void set(int row, int column, float value) {
        mat[row * n + column] = value;
    }

    /**
     * Gets the value at the specified row and column
     * 
     * @param row The row of the value
     * @param column The column of the value
     * @return The value of the requested matrix cell
     */
    public float get(int row, int column) {
        return mat[row * n + column];
    }

    /**
     * Copies the values of the other matrix into this one.
     * 
     * @param other The matrix whose values to copy
     */
    public void copy(Mat other) throws ArrayIndexOutOfBoundsException {
        if (n != other.n) {
            throw new ArrayIndexOutOfBoundsException(String.format(
                    "Square matrix dimensions do not match: %d != %d.", n,
                    other.n));
        }
        System.arraycopy(other.mat, 0, mat, 0, mat.length);
    }

    /**
     * Multiplies the specified matrices.
     * 
     * @param a The left matrix of the multiplication
     * @param b The right matrix of the multiplication
     * @return The product of the matrix multiplication
     * @throws ArithmeticException
     */
    protected static Mat mul(Mat a, Mat b) throws ArithmeticException {
        if (a.n != b.n) {
            throw new ArithmeticException(String.format(
                    "Square matrix dimensions do not match: %d != %d.", a.n,
                    b.n));
        }
        int n = a.n;
        Mat product = new Mat(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                float sum = 0.0f;
                for (int k = 0; k < n; ++k) {
                    sum += a.get(i, k) * b.get(j, k);
                }
                product.set(i, j, sum);
            }
        }
        return product;
    }

    /**
     * Multiplies this matrix with the specified matrix.
     * 
     * @param other The matrix to perform the multiplication with
     * @throws ArithmeticException
     */
    protected void mul(Mat other) throws ArithmeticException {
        copy(mul(this, other));
    }

    /**
     * Multiplies this NxN matrix with the specified R^N vector.
     * 
     * @param vec The vector to perform the multiplication with
     * @return The resulting vector
     * @throws ArithmeticException If the dimensions do not match
     */
    protected Vec mul(Vec vec) throws ArithmeticException {
        if (vec.dimension() != n) {
            throw new ArithmeticException(
                    "Matrix height and vector dimension do not match.");
        }
        Vec product = new Vec(n);
        for (int i = 0; i < n; ++i) {
            float sum = 0;
            for (int j = 0; j < n; ++j) {
                sum += get(i, j) * vec.get(j);
            }
            product.set(i, sum);
        }
        return product;
    }

    /**
     * Returns the minor of the specified row and column.
     * 
     * @param row The row of the minor
     * @param column The column of the minor
     * @return The minor of the specified row and column
     */
    public float minor(int row, int column) {
        Mat m = new Mat(n - 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == row || j == column) {
                    continue;
                }
                m.set(i - (i > row ? 1 : 0), j - (j > column ? 1 : 0), mat[i
                        * n + j]);
            }
        }
        System.out.println(m);
        return m.det();
    }

    /**
     * Returns the cofactor of a certain row and column.
     * 
     * @param i The row of the cofactor
     * @param j The column of the cofactor
     * @return The cofactor C_ij
     */
    public float cofactor(int i, int j) {
        return (float)(Math.pow(-1, i + j) * minor(i, j));
    }

    /**
     * Returns the determinant of this matrix.
     * 
     * @return The determinant of this matrix
     */
    public float det() {
        if (n == 2) {
            return mat[0] * mat[3] - mat[1] * mat[2];
        } else if (n == 1) {
            return mat[0];
        }
        int d = 0;
        for (int i = 0; i < n; ++i) {
            d += get(i, 0) * cofactor(i, 0);
        }
        return d;
    }

    /**
     * Returns the matrix as a float buffer for passing to OpenGL functions. The
     * resulting raw matrix will be in column major form.
     * 
     * @return The matrix as a column major float buffer
     */
    public FloatBuffer toFloatBuffer() {
        FloatBuffer buf = BufferUtils.createFloatBuffer(n * n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                buf.put(mat[j * 4 + i]);
            }
        }
        buf.flip();
        return buf;
    }

    /**
     * Returns the string representation of this matrix.
     * 
     * @return The string representation of this matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sb.append(" " + get(i, j));
                if (j == n - 1) {
                    if (i == n - 1) {
                        sb.append(" ]");
                    } else {
                        sb.append("\n ");
                    }
                }
            }
        }
        return sb.toString();
    }
}
