package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class DoorTest extends EntityTest {
	private Door d1;

	@Before
	public void setUp() {
		e1 = d1 = new Door(new Vec3(1.0f, 1.0f, 1.0f));
		e2 = new Door(new Vec3(1.0f, 1.0f, 1.0f));
	}

	@Test
	public void unlockTest() {
		assertTrue(d1.isLocked());
		d1.unlock();
		assertFalse(d1.isLocked());
	}

	@Test
	public void openTest() {
		assertFalse(d1.isOpen());
		d1.open();
		assertFalse(d1.isOpen());
		d1.unlock();
		d1.open();
		assertTrue(d1.isOpen());
	}

	@Test
	public void determineDirectionTest() {
		//TODO write this when the wall and level class is implemented
	}
}
