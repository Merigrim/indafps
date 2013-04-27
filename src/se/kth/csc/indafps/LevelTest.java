package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class LevelTest {
    Level l;

    @Before
    public void setUp() {
        // Set up a level with some Doors, Players and Walls.
        l = new Level();

        l.addEntity(new Door(new Vec3(1.0f, 1.0f, 0.0f)));
        l.addEntity(new Door(new Vec3(2.0f, 2.0f, 0.0f)));
        l.addEntity(new Door(new Vec3(0.0f, 3.0f, 0.0f)));

        l.addEntity(new Player(new Vec3(0.0f, 0.0f, 0.0f)));

        l.addEntity(new Wall(new Vec3(2.0f, 0.0f, 0.0f)));
        l.addEntity(new Wall(new Vec3(2.0f, 2.0f, 0.0f)));
    }

    @Test
    public void addEntityTest() {
        Entity e = new Door(new Vec3(0.0f, 0.0f, 0.0f));
        l.addEntity(new Door(new Vec3(0.0f, 0.0f, 0.0f)));
        assertEquals(l, e.getLevel());
    }
}
