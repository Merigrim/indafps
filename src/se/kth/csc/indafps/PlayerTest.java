package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class PlayerTest extends ActorTest {

	@Before
	public void setUp() {
		a1 = new Player(new Vec3(0.0f, 0.0f, 0.0f));
		a2 = new Player(new Vec3(4.0f, 4.0f, 4.0f));
		super.setUp();
	}

}
