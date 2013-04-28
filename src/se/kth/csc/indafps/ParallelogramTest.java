package se.kth.csc.indafps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class ParallelogramTest {
	Parallelogram p;
	@Before
	public void setUp() {
		Vec3 p1 = new Vec3(1.0f, 1.0f, 2.0f);
		Vec3 p2 = new Vec3(1.0f, 2.0f, 2.0f);
		Vec3 p3 = new Vec3(3.0f, 1.0f, 1.0f);

		p = new Parallelogram(p1, p2, p3);
	}

	@Test
	public void getCornerTest() {
		assertThat(p.getCorner(0), is(new Vec3(1.0f, 1.0f, 2.0f)));
		assertThat(p.getCorner(1), is(new Vec3(1.0f, 2.0f, 2.0f)));
		assertThat(p.getCorner(2), is(new Vec3(3.0f, 1.0f, 1.0f)));
		assertThat(p.getCorner(3), is(new Vec3(3.0f, 2.0f, 1.0f)));
	}
}
