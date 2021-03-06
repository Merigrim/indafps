package se.kth.csc.indafps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public abstract class EntityTest {
	protected Entity e1;
	protected Entity e2;

	private void biIntersectionTest(Entity e1, Entity e2, boolean expected) {
		assertEquals(expected, e1.testIntersection(e2));
		assertEquals(expected, e2.testIntersection(e1));
	}

    @Test
    public void setSolidTest() {
		e1.setSolid(true);
		assertTrue(e1.isSolid());
		e1.setSolid(false);
		assertFalse(e1.isSolid());
    }

	@Test
	public void setPositionTest() {
		Vec3 v1 = new Vec3(1.0f, 1.0f, -2.0f);
		e1.setPosition(v1);
		Vec3 v2 = e1.getPosition();
		assertTrue(v1.equals(v2));
	}

	@Test
	public void setScaleTest() {
		Vec3 v1 = new Vec3(1.0f, 1.0f, -2.0f);
		e1.setScale(v1);
		Vec3 v2 = e1.getScale();
		assertTrue(v1.equals(v2));
	}

	@Test
	public void setRotationTest() {
		Vec3 v1 = new Vec3(1.0f, 1.0f, -2.0f);
		e1.setRotation(v1);
		Vec3 v2 = e1.getRotation();
		assertTrue(v1.equals(v2));
	}

	@Test
	public void setColorTest() {
		Vec4 v1 = new Vec4(1.0f, 1.0f, -2.0f, 1.5f);
		e1.setColor(v1);
		Vec4 v2 = e1.getColor();
		assertTrue(v1.equals(v2));
	}

    @Test
    public void lineIntersectionTest() {
		e1.setPosition(new Vec3(0.0f, 0.0f, 0.0f));
		e1.setScale(new Vec3(2.0f, 2.0f, 2.0f));
		e1.setRotation(new Vec3(0.0f, 0.0f, 0.0f));

		// Test intersection where one line end point is inside
		// the Entity.
		assertThat(e1.testIntersection(new Line(new Vec3(0.0f, 0.0f, 0.0f),
				new Vec3(4.0f, 0.0f, 0.0f))), is(new Vec3(1.0f, 0.0f, 0.0f)));

		// Test intersection where none of the line end points is 
		// inside the Entity.
		assertThat(e1.testIntersection(new Line(new Vec3(-2.0f, 0.5f, -0.5f),
				new Vec3(2.0f, -0.5f, 0.5f))), is(new Vec3(-1.0f, 0.25f, -0.25f)));
		assertThat(e1.testIntersection(new Line(new Vec3(0.0f, 0.0f, -4.0f),
				new Vec3(0.0f, 0.0f, 2.0f))), is(new Vec3(0.0f, 0.0f, -1.0f)));

		// Test intersection where both of the line end points is 
		// inside the Entity.
		assertThat(e1.testIntersection(new Line(new Vec3(0.5f, 0.5f, -0.5f),
				new Vec3(-0.5f, -0.5f, 0.5f))), is(new Vec3(-1.0f, -1.0f, 1.0f)));

		// Test some cases where there are no intersection.
		assertThat(e1.testIntersection(new Line(new Vec3(2.5f, 0.5f, -0.5f),
				new Vec3(2.5f, -0.5f, 0.5f))), nullValue());
		assertThat(e1.testIntersection(new Line(new Vec3(2.0f, 0.0f, 0.0f),
				new Vec3(0.0f, 0.0f, 1.0f))), nullValue());
		assertThat(e1.testIntersection(new Line(new Vec3(0.0f, 2.5f, 0.0f),
				new Vec3(2.5f, -2.5f, 0.0f))), nullValue());
    }

	@Test
	public void entityIntersectionTest() {
		e1.setPosition(new Vec3(0.0f, 0.0f, 0.0f));
		e1.setScale(new Vec3(2.0f, 2.0f, 2.0f));
		e2.setPosition(new Vec3(0.0f, 0.0f, 0.0f));
		e2.setScale(new Vec3(2.0f, 2.0f, 2.0f));

		// Same position
		biIntersectionTest(e1, e2, true);

		// The center point isn't inside
		e1.setPosition(new Vec3(1.5f, 0.0f, 1.5f));
		biIntersectionTest(e1, e2, true);

		// They collide at the corners
		e1.setPosition(new Vec3(2.0f, 0.0f, 2.0f));
		biIntersectionTest(e1, e2, true);

		// No intersection
		e1.setPosition(new Vec3(2.5f, 0.0f, 0.0f));
		biIntersectionTest(e1, e2, false);

		// No intersection
		e1.setPosition(new Vec3(0.0f, 0.0f, 2.5f));
		biIntersectionTest(e1, e2, false);
	}
}
