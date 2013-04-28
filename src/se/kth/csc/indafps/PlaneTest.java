package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class PlaneTest {
	private Plane p;

	private void constructorFailHelper(Vec3 p1, Vec3 p2, Vec3 p3) {
		try {
			p = new Plane(p1, p2, p1);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Before
	public void setUp() {
		Vec3 p1 = new Vec3(1.0f, 1.0f, 2.0f);
		Vec3 p2 = new Vec3(1.0f, 2.0f, 2.0f);
		Vec3 p3 = new Vec3(1.0f, 2.0f, 3.0f);
		p = new Plane(p1, p2, p3);
	}

	@Test
	public void constructorFailTest() {
		Vec3 p1 = new Vec3(1.0f, 1.0f, 1.0f);
		Vec3 p2 = new Vec3(1.0f, 1.0f, 2.0f);
		Vec3 p3 = new Vec3(1.0f, 1.0f, 2.0f);
		constructorFailHelper(p1, p2, p3);
		constructorFailHelper(p1, p3, p2);
		constructorFailHelper(p2, p1, p3);
		constructorFailHelper(p3, p1, p2);
		constructorFailHelper(p2, p3, p1);
		constructorFailHelper(p3, p2, p1);
	}

	@Test
	public void getNormalTest() {
		assertTrue(p.getNormal().equals(new Vec3(1.0f, 0.0f, 0.0f)));
	}

	@Test
	public void getPoint() {
		assertTrue(p.getPoint().equals(new Vec3(1.0f, 1.0f, 2.0f)));
	}
}
