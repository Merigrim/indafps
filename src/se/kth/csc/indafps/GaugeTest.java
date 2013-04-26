package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GaugeTest {
	Gauge g;

	@Before
	public void setUp() {
		g = new Gauge(100);
	}

	@Test
	public void addTest() {
		// TODO Make test for integer overflow.
		g.setValue(50);
		assertEquals(60, g.add(10));
		assertEquals(60, g.getValue());

		// Reach the upper limit
		assertEquals(100, g.add(100));
		assertEquals(100, g.getValue());

		// Decrease the value
		assertEquals(90, g.add(-10));
		assertEquals(90, g.getValue());

		// Reach the lower limit
		assertEquals(0, g.add(-100));
		assertEquals(0, g.getValue());
	}

	@Test
	public void setValueTest() {
		// Set a value above the upper limit
		g.setValue(150);
		assertEquals(100, g.getValue());

		// Set a negative value
		g.setValue(-100);
		assertEquals(0, g.getValue());
	}

	@Test
	public void setMaximumTest() {
		// Increase the upper limit
		g.setMaximum(200);
		assertEquals(200, g.getMaximum());

		// Set the maximum lower than the actual value of the Gauge
		g.setValue(50);
		g.setMaximum(25);
		assertEquals(25, g.getValue());
		assertEquals(25, g.getMaximum());

		// Set a negative value to the upper limit, shall become 0
		g.setMaximum(-50);
		assertEquals(0, g.getValue());
		assertEquals(0, g.getMaximum());
	}

	@Test
	public void isFullTest() {
		// The Gauge is by default full
		assertTrue(g.isFull());

		// Not full anymore
		g.setValue(50);
		assertFalse(g.isFull());

		// Make it full again
		g.add(100);
		assertTrue(g.isFull());

		// Change the maximum
		g.setMaximum(200);
		assertFalse(g.isFull());
	}

	@Test
	public void isEmptyTest() {
		// The Gauge is not empty by default
		assertFalse(g.isEmpty());

		// Make the Gauge empty
		g.setValue(0);
		assertTrue(g.isEmpty());

		// Add something to the Gauge
		g.add(1);
		assertFalse(g.isEmpty());
	}

	@Test
	public void refillTest() {
		g.setValue(50);
		g.refill();
		assertTrue(g.isFull());
	}

	@Test
	public void emptyTest() {
		g.empty();
		assertTrue(g.isEmpty());
	}
}
