package se.kth.csc.indafps;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LevelTest {
    Level l;
    Vec3 v[];

    @BeforeClass
    public static void createDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownDisplay() {
        Display.destroy();
    }

    @Before
    public void setUp() {
        // Set up a level with some Doors, Players and Walls.
        v = new Vec3[6];
        l = new Level();

        v[0] = new Vec3(1.0f, 1.0f, 0.0f);
        v[1] = new Vec3(2.0f, 2.0f, 0.0f);
        v[2] = new Vec3(0.0f, 3.0f, 0.0f);
        v[3] = new Vec3(0.0f, 0.0f, 0.0f);
        v[4] = new Vec3(2.0f, 0.0f, 0.0f);
        v[5] = new Vec3(2.0f, 2.0f, 0.0f);

        l.addEntity(new Door(v[0]));
        l.addEntity(new Door(v[1]));
        l.addEntity(new Door(v[2]));
        l.addEntity(new Player(v[3]));
        l.addEntity(new Wall(v[4]));
        l.addEntity(new Wall(v[5]));
    }

    @Test
    public void importLevelTest() {
        // TODO
    }

    @Test
    public void addEntityTest() {
        // Check if the Entity will be associated with the Level.
        Entity e = new Door(new Vec3(0.0f, 0.0f, 0.0f));
        l.addEntity(e);
        assertEquals(l, e.getLevel());
    }

    @Test
    public void getEntitiesTest() {
        // Check if the Player entity exists in the Level.
        Set<Entity> players = l.getEntities("Player");
        for (Entity player : players) {
            assertTrue(player.getPosition().equals(v[3]));
        }

        Set<Entity> doors = l.getEntities("Door");
        for (Entity door : doors) {
            boolean b1 = door.getPosition().equals(v[0]);
            boolean b2 = door.getPosition().equals(v[1]);
            boolean b3 = door.getPosition().equals(v[2]);
            assertTrue(b1 || b2 || b3);
        }
    }
}
