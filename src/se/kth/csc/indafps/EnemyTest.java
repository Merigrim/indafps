package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class EnemyTest extends ActorTest {
	Enemy e;

	@Before
	public void setUp() {
		a1 = e = new Enemy(new Vec3(0.0f, 0.0f, 0.0f));
		a2 = new Enemy(new Vec3(4.0f, 4.0f, 4.0f));
		super.setUp();
	}

	@Test
	public void changeItemTest() {
		Key k1 = new Key(new Vec3(0.0f, 0.0f, 0.0f));
		Key k2 = new Key(new Vec3(0.0f, 0.0f, 0.0f));

		assertEquals(null, e.changeItem(k1));
		assertEquals(e, k1.getOwner());
		assertEquals(k1, e.changeItem(k2));
		assertEquals(null, k1.getOwner());
		assertEquals(e, k2.getOwner());
		assertEquals(k2, e.changeItem(null));
		assertEquals(null, k2.getOwner());
	}

	@Test
	public void getPhaseTest() {
		assertEquals(Enemy.Phase.IDLE, e.getPhase());
		//TODO Set the enemy to ALERT mode.
	}
}
