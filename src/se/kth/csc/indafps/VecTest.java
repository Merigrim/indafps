package se.kth.csc.indafps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VecTest {
    Vec2 a2;
    Vec2 b2;
    Vec3 a3;
    Vec3 b3;
    Vec4 a4;
    Vec4 b4;

    @Before
    public void setUp() throws Exception {
        a2 = new Vec2(1, 2);
        b2 = new Vec2(3, 4);
        a3 = new Vec3(1, 2, 3);
        b3 = new Vec3(4, 5, 6);
        a4 = new Vec4(1, 2, 3, 4);
        b4 = new Vec4(5, 6, 7, 8);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(a2.getX(), is(1.0f));
        assertThat(a2.getY(), is(2.0f));
        assertThat(b2.getX(), is(3.0f));
        assertThat(b2.getY(), is(4.0f));
        assertThat(a3.getX(), is(1.0f));
        assertThat(a3.getY(), is(2.0f));
        assertThat(a3.getZ(), is(3.0f));
        assertThat(b3.getX(), is(4.0f));
        assertThat(b3.getY(), is(5.0f));
        assertThat(b3.getZ(), is(6.0f));
        assertThat(a4.getR(), is(1.0f));
        assertThat(a4.getG(), is(2.0f));
        assertThat(a4.getB(), is(3.0f));
        assertThat(a4.getA(), is(4.0f));
        assertThat(b4.getR(), is(5.0f));
        assertThat(b4.getG(), is(6.0f));
        assertThat(b4.getB(), is(7.0f));
        assertThat(b4.getA(), is(8.0f));

        a2.setX(10.0f);
        assertThat(a2.getX(), is(10.0f));
        a3.setY(20.0f);
        assertThat(a3.getY(), is(20.0f));
        a4.setB(30.0f);
        assertThat(a4.getB(), is(30.0f));
    }

    @Test
    public void testCross() {
        assertThat(a3.cross(b3), is(new Vec3(-3, 6, -3)));
    }

    @Test
    public void testDot() {
        assertThat(a2.dot(b2), is(b2.dot(a2)));
        assertThat(a2.dot(b2), is(11.0f));
        assertThat(a3.dot(b3), is(b3.dot(a3)));
        assertThat(a3.dot(b3), is(32.0f));
        assertThat(a4.dot(b4), is(b4.dot(a4)));
        assertThat(a4.dot(b4), is(70.0f));
        try {
            a2.dot(b3);
            fail("Dot product of v in R^N and w in R^M, N != M didn't fail.");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Success
        }
    }

    private boolean near(Vec a, Vec b, float error) {
        Vec c = a.sub(b);
        for (int i = 0; i < c.dimension(); ++i) {
            if (Math.abs(c.get(i)) > error) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testNormalize() {
        assertTrue(near(a2.normalize(), new Vec2(1.0f / (float)Math.sqrt(5.0f),
                2.0f / (float)Math.sqrt(5.0f)), 0.001f));
        assertTrue(near(b2.normalize(), new Vec2(3.0f / 5.0f, 4.0f / 5.0f),
                0.001f));
        assertTrue(near(a3.normalize(), new Vec3(
                1.0f / (float)Math.sqrt(14.0f), (float)Math.sqrt(2.0f / 7.0f),
                3.0f / (float)Math.sqrt(14.0f)), 0.001f));
        float sqrt77 = (float)Math.sqrt(77.0f);
        assertTrue(near(b3.normalize(), new Vec3(4.0f / sqrt77, 5.0f / sqrt77,
                6.0f / sqrt77), 0.001f));
        System.out.println(a2.toString() + ", " + a2.normalize());
        System.out.println(a3.toString() + ", " + a3.normalize().toString());
        System.out.println(a4.toString() + ", " + a4.normalize().toString());
        assertTrue(near(
                a4.normalize(),
                new Vec4(1.0f / (float)Math.sqrt(30.0f), (float)Math
                        .sqrt(2.0f / 15.0f), (float)Math.sqrt(3.0f / 10.0f),
                        2.0f * (float)Math.sqrt(2.0f / 15.0f)), 0.001f));
    }
}
