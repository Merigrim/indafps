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
    }
}
