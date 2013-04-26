package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class PlayerTest extends ActorTest {

	@Before
	public void setUp() {
		a1 = new Player();
		a2 = new Player();
		super.setUp();
	}

}
