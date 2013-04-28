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
        Mat4 expected = new Mat4(new float[] { 0, 0, 0, 0, 14, 20, 26, 32, 28,
                40, 52, 64, 42, 60, 78, 96 });
        assertThat(a.mul(b), is(expected));
        Mat4 id = new Mat4();
        assertThat(id.mul(id), is(id));
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
    public void testOrtho() {
        Mat4 o2d = Mat4.ortho(0, 640, 0, 480, -1.0f, 1.0f);
        Vec4 v = new Vec4(160.0f, 120.0f, 10.0f, 1.0f);
        assertThat(o2d.mul(v), is(new Vec4(-0.5f, 0.5f, -10.0f, 1.0f)));
        Mat4 o2d2 = Mat4.ortho(0, 1280, 0, 720, -1.0f, 1.0f);
        Vec4 v2 = new Vec4(1280.0f, 0.0f, 1.0f, 1.0f);
        assertThat(o2d2.mul(v2), is(new Vec4(1.0f, 1.0f, -1.0f, 1.0f)));
    }

    @Test
    public void testPerspective() {
        Mat4 proj = Mat4.perspective(90.0f, 1280.0f / 720.0f, 0.1f, 1000.0f);
        // How to test this?
    }

    @Test
    public void testToString() {
        assertThat(c.toString(), is("[ 1.0 0.0 0.0 0.0 0.0\n  0.0 1.0 0.0 0.0"
                + " 0.0\n  0.0 0.0 1.0 0.0 0.0\n  0.0 0.0 0.0 1.0 0.0\n"
                + "  0.0 0.0 0.0 0.0 1.0 ]"));
    }
}
