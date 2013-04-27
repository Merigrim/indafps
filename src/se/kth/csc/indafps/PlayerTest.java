package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class PlayerTest extends ActorTest {
	Player p;

	@Before
	public void setUp() {
		a1 = p = new Player(new Vec3(0.0f, 0.0f, 0.0f));
		a2 = new Player(new Vec3(4.0f, 4.0f, 4.0f));
		super.setUp();
	}

	@Test
	public void pickUpTest() {
		Key k = new Key(new Vec3(0.0f, 0.0f, 0.0f));
		p.pickUp(k);
		assertEquals(p, k.getOwner());
		assertEquals(k, p.searchItem(k.getClass().getName()));
	}

	@Test
	public void searchItemTest() {
		Key k1 = new Key(new Vec3(0.0f, 0.0f, 0.0f));
		Key k2 = new Key(new Vec3(0.0f, 0.0f, 0.0f));
		assertEquals(null, p.searchItem("Rainbows!"));
		p.pickUp(k1);
		p.pickUp(k2);
		assertEquals(null, p.searchItem("Candy!"));
		Item returnedKey = p.searchItem("se.kth.csc.indafps.Key");
		assertTrue(k1 == returnedKey || k2 == returnedKey);
	}

	@Test
	public void requestItemTest() {
		Key k1 = new Key(new Vec3(0.0f, 0.0f, 0.0f));
		Key k2 = new Key(new Vec3(0.0f, 0.0f, 0.0f));
		assertEquals(null, p.requestItem("Rainbows!"));
		p.pickUp(k1);
		p.pickUp(k2);
		assertEquals(null, p.requestItem("Candy!"));
		Item returnedKey = p.requestItem("se.kth.csc.indafps.Key");
		assertTrue(k1 == returnedKey || k2 == returnedKey);
		assertTrue(p.searchItem("se.kth.csc.indafps.Key") != returnedKey);
	}
}
