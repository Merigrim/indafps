package se.kth.csc.indafps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public abstract class ActorTest extends EntityTest {
    protected Actor a1;
    protected Actor a2;

    public void setUp() {
        e1 = a1;
        e2 = a2;
    }

    @Test
    public void restoreHealthTest() {
        a1.setMaximumHealth(100);
        assertEquals(100, a1.restoreHealth(100));
        assertEquals(100, a1.getHealth());
        assertEquals(50, a1.restoreHealth(-50));
        assertEquals(50, a1.getHealth());
        assertEquals(75, a1.restoreHealth(25));
        assertEquals(0, a1.restoreHealth(-100));
        assertEquals(0, a1.getHealth());

        // The Actor is now dead, it shall not be able to restore its health.
        assertEquals(0, a1.restoreHealth(50));
        assertEquals(0, a1.getHealth());
    }

    @Test
    public void restoreAmmoTest() {
        a1.setMaximumAmmo(100);
        assertEquals(100, a1.restoreAmmo(100));
        assertEquals(100, a1.getAmmo());
        assertEquals(50, a1.restoreAmmo(-50));
        assertEquals(50, a1.getAmmo());
        assertEquals(75, a1.restoreAmmo(25));
        assertEquals(0, a1.restoreAmmo(-100));
        assertEquals(0, a1.getAmmo());
    }

    @Test
    public void setMaximumHealthTest() {
        a1.setMaximumHealth(50);
        a1.restoreHealth(100);
        assertEquals(50, a1.getMaximumHealth());
        assertEquals(50, a1.getHealth());

        a1.setMaximumHealth(-50);
        assertEquals(0, a1.getMaximumHealth());
        assertEquals(0, a1.getHealth());
    }

    @Test
    public void setMaximumAmmoTest() {
        a1.setMaximumAmmo(50);
        a1.restoreAmmo(100);
        assertEquals(50, a1.getMaximumAmmo());
        assertEquals(50, a1.getAmmo());

        a1.setMaximumAmmo(-50);
        assertEquals(0, a1.getMaximumAmmo());
        assertEquals(0, a1.getAmmo());
    }

    @Test
    public void getViewDirectionTest() {
        // TODO Manipulate the view direction.
        // Make sure the returned vector is normalized
        Vec3 v = a1.getViewDirection();
        Vec3 n = v.normalize();
        assertTrue(v.equals(n));

        // Make sure the returned vector is a copy
        v.setX(2.0f);
        assertFalse(v.equals(a1.getViewDirection()));
    }

    @Test
    public void isAliveTest() {
        assertTrue(a1.isAlive());
        a1.setMaximumHealth(100);
        a1.restoreHealth(-100);

        assertFalse(a1.isAlive());

        a1.restoreHealth(-100);
        assertFalse(a1.isAlive());
    }

    @Test
    public void hasAmmo() {
        assertTrue(a1.hasAmmo());
        a1.setMaximumAmmo(12);
        a1.restoreAmmo(-12);

        assertFalse(a1.hasAmmo());

        a1.restoreAmmo(-12);
        assertFalse(a1.hasAmmo());
    }
}
