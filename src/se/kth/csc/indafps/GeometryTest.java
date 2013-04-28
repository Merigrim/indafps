package se.kth.csc.indafps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GeometryTest {
	@Test
	public void linePlaneIntersectionTest() {
		Vec3 p1 = new Vec3(2.0f, 1.0f, 2.0f);
		Vec3 p2 = new Vec3(1.0f, 2.0f, 2.0f);
		Vec3 p3 = new Vec3(1.0f, 2.0f, 3.0f);
		Plane plane = new Plane(p1, p2, p3);
		Vec3 l1 = new Vec3(1.0f, 3.0f, 0.0f);
		Vec3 l2 = new Vec3(0.0f, 1.0f, 0.0f);
		Line line = new Line(l1, l2);
		assertThat(Geometry.intersects(line, plane),
				is(new Vec3(1.0f, 2.0f, 0.0f)));
		line.setDirection(new Vec3(-1.0f, 1.0f, 0.0f));
		assertEquals(Geometry.intersects(line, plane), null);
	}
}
