package se.kth.csc.indafps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class BoxTest {
	Box b;

	@Before
	public void setUp() {
		Vec3 pos = new Vec3(1.0f, 3.0f, 4.0f);
		Vec3 scale = new Vec3(2.0f, 1.0f, 4.0f);
		b = new Box(pos, scale);
	}

	@Test
	public void getCornersTest() {
		Vec3 corners[] = b.getCorners();
		assertThat(corners[0], is(new Vec3(0.0f, 2.5f, 2.0f)));
		assertThat(corners[1], is(new Vec3(2.0f, 2.5f, 2.0f)));
		assertThat(corners[2], is(new Vec3(0.0f, 3.5f, 2.0f)));
		assertThat(corners[3], is(new Vec3(0.0f, 2.5f, 6.0f)));
		assertThat(corners[4], is(new Vec3(2.0f, 3.5f, 6.0f)));
		assertThat(corners[5], is(new Vec3(0.0f, 3.5f, 6.0f)));
		assertThat(corners[6], is(new Vec3(2.0f, 2.5f, 6.0f)));
		assertThat(corners[7], is(new Vec3(2.0f, 3.5f, 2.0f)));
	}
}
