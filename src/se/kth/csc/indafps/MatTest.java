package se.kth.csc.indafps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatTest {
    Mat4 a;
    Mat4 b;
    Mat c;

    @Before
    public void setUp() throws Exception {
        a = new Mat4();
        b = new Mat4();
        c = new Mat(5);
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                a.set(i, j, i * j);
                b.set(i, j, i + j);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCopy() {
        assertThat(a, not(b));
        a.copy(b);
        assertThat(a, is(b));
    }

    @Test
    public void testMultiplication() {
        Mat expected = new Mat4();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                expected.set(i, j, (i * j) * (i + j));
            }
        }
        a.mul(b);
        assertThat(a, is(b));
        try {
            a.mul(c);
            fail("ArithmeticException not thrown on multiplication of square"
                    + "matrices of different dimensions.");
        } catch (ArithmeticException e) {
            // Success
        }
    }

    @Test
    public void testVectorMultiplication() {
        Vec4 v = new Vec4(1, 2, 3, 4);
        assertThat(a.mul(v), is(new Vec4(0.0f, 20.0f, 40.0f, 60.0f)));
        assertThat(b.mul(v), is(new Vec4(20.0f, 30.0f, 40.0f, 50.0f)));
        try {
            Vec v2 = new Vec(5);
            a.mul(v2);
            fail("ArithmeticException not thrown on multiplication of square"
                    + "matrix with vector of different dimension.");
        } catch (ArithmeticException e) {
            // Success
        }
    }

    @Test
    public void testDeterminant() {
        assertThat(a.det(), is(0.0f));
        assertThat(b.det(), is(0.0f));
        assertThat(c.det(), is(1.0f));
    }

    @Test
    public void testToString() {
        assertThat(c.toString(), is("[ 1.0 0.0 0.0 0.0 0.0\n  0.0 1.0 0.0 0.0"
                + " 0.0\n  0.0 0.0 1.0 0.0 0.0\n  0.0 0.0 0.0 1.0 0.0\n"
                + "  0.0 0.0 0.0 0.0 1.0 ]"));
    }
}
