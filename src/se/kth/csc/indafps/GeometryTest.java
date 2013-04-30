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

	@Test
	public void lineParallelogramIntersectionTest() {
		Vec3 p1 = new Vec3(2.0f, 1.0f, 2.0f);
		Vec3 p2 = new Vec3(1.0f, 2.0f, 2.0f);
		Vec3 p3 = new Vec3(1.0f, 2.0f, 3.0f);
		Parallelogram parallel = new Parallelogram(p1, p2, p3);

		Vec3 l1 = new Vec3(1.5f, 3.5f, 2.5f);
		Vec3 l2 = new Vec3(0.0f, 1.0f, 0.0f);
		Line line = new Line(l1, l2);

		assertThat(Geometry.intersects(line, parallel),
				is(new Vec3(1.5f, 1.5f, 2.5f)));

		line.setOrigin(new Vec3(1.0f, 1.0f, 3.0f));
		assertEquals(null, Geometry.intersects(line, parallel));
		
		// Line parallel to the parallelogram
		line.setDirection(new Vec3(-1.0f, 1.0f, 0.0f));
		assertEquals(null, Geometry.intersects(line, parallel));
	}

	@Test
	public void projectionTest() {
		Vec3 p1 = new Vec3(15.0f, 10.0f, 3.0f);
		Vec3 p2 = new Vec3(-5.0f, 3.0f, -15.0f);

		Vec3 proj1 = Geometry.projection(p1, p2);
		assertEquals(0.0, proj1.cross(p2).getLength(), 0.00001);
		assertEquals(0.0, p1.sub(proj1).dot(p2), 0.00001);
	}
}
