package se.kth.csc.indafps;

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
	public void setPosTest() {
		e1.setPosition(new Vec3(1.0f, 1.0f, -2.0f));
		Vec3 pos = e1.getPosition();
	}

    @Test
    public void lineIntersectionTest() {
		e1.setPosition(new Vec3(0.0f, 0.0f, 0.0f));
		e1.setSize(new Vec3(2.0f, 2.0f, 2.0f));
		e1.setRotation(new Vec3(0.0f, 0.0f, 0.0f));

		// Test intersection where one line end point is inside
		// the Entity.
		assertTrue(e1.testIntersection(new Line(new Vec3(0.0f, 0.0f, 0.0f),
				new Vec3(5.0f, 4.0f, 0.0f))));

		// Test intersection where none of the line end points is 
		// inside the Entity.
		assertTrue(e1.testIntersection(new Line(new Vec3(-2.0f, 0.5f, -0.5f),
				new Vec3(2.0f, -0.5f, 0.5f))));

		// Test intersection where both of the line end points is 
		// inside the Entity.
		assertTrue(e1.testIntersection(new Line(new Vec3(0.5f, 0.5f, -0.5f),
				new Vec3(-0.5f, -0.5f, 0.5f))));

		// Test some cases where there are no intersection.
		assertFalse(e1.testIntersection(new Line(new Vec3(2.5f, 0.5f, -0.5f),
				new Vec3(2.5f, -0.5f, 0.5f))));
		assertFalse(e1.testIntersection(new Line(new Vec3(0.0f, 2.5f, 0.0f),
				new Vec3(2.5f, 0.0f, 0.0f))));
    }

	@Test
	public void entityIntersectionTest() {
		e1.setPosition(new Vec3(0.0f, 0.0f, 0.0f));
		e1.setSize(new Vec3(2.0f, 2.0f, 2.0f));
		e2.setPosition(new Vec3(0.0f, 0.0f, 0.0f));
		e2.setSize(new Vec3(2.0f, 2.0f, 2.0f));

		// Same position
		biIntersectionTest(e1, e2, true);

		// The center point isn't inside
		e1.setPosition(new Vec3(1.5f, 1.5f, 1.5f));
		biIntersectionTest(e1, e2, true);

		// They collide at the corners
		e1.setPosition(new Vec3(2.0f, 2.0f, 2.0f));
		biIntersectionTest(e1, e2, true);

		// No intersection
		e1.setPosition(new Vec3(2.5f, 2.5f, 2.5f));
		biIntersectionTest(e1, e2, true);
	}
}
