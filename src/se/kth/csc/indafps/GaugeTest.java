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
		g.setValue(50);
	}
}
