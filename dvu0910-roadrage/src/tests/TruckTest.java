/*
 * TCSS 305 - Road Rage
 */

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Atv;
import model.Car;
import model.Direction;
import model.Light;
import model.Taxi;
import model.Terrain;
import model.Truck;
import org.junit.Test;

/**
 * Unit tests for class Truck.
 * 
 * @author DuyVu
 * @version Winter 2021
 */
public class TruckTest {

    /** Test method for Truck constructor. */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(4, 5, Direction.NORTH);

        assertEquals("Truck x coordinate not initialized correctly!", 4, t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 5, t.getY());
        assertEquals("Truck direction not initialized coreectly!", Direction.NORTH,
                     t.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, t.getDeathTime());
        assertTrue("Truck death time not initialized isAlive", t.isAlive());
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(4, 5, Direction.NORTH);

        t.setX(6);
        assertEquals("Truck setX failed!", 6, t.getX());
        t.setY(7);
        assertEquals("Truck setY failed!", 7, t.getY());
        t.setDirection(Direction.SOUTH);
        assertEquals("Truck setDirection failed!", Direction.SOUTH, t.getDirection());
    }

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {

        final List<Terrain> vaildTerain = new ArrayList<>();
        vaildTerain.add(Terrain.STREET);
        vaildTerain.add(Terrain.LIGHT);
        vaildTerain.add(Terrain.CROSSWALK);
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLight : Light.values()) {
                // truck can pass STREET
                if (destinationTerrain == Terrain.STREET) {
                    assertTrue("Truck can pass terrain street",
                               truck.canPass(destinationTerrain, currentLight));
                    // truck can pass LIGHT
                } else if (destinationTerrain == Terrain.LIGHT) {
                    assertTrue("Truck can pass terrain Light",
                               truck.canPass(destinationTerrain, currentLight));
                    // truck can pass CROSSWALK?
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    // truck can pass when light GREEN and YELLOW
                    if (currentLight == Light.GREEN || currentLight == Light.YELLOW) {
                        assertTrue("Truck pass terrain crosswalk and green or yellow light",
                                   truck.canPass(destinationTerrain, currentLight));

                        // truck can not pass when light RED
                    } else {
                        assertFalse("Truck can not pass terrain crosswalk and red light",
                                    truck.canPass(destinationTerrain, currentLight));
                    }
                    // truck can not pass
                } else {
                    assertFalse("Truck can not pass terrain " + destinationTerrain,
                                truck.canPass(destinationTerrain, currentLight));

                }
            }
        }
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionTurnLeft() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        assertEquals("truck can turn leff", Direction.WEST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.WEST, Terrain.LIGHT);
        assertEquals("truck can turn leff", Direction.WEST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.WEST, Terrain.CROSSWALK);
        assertEquals("truck can turn leff", Direction.WEST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.WEST, Terrain.CROSSWALK);
        assertEquals("truck can turn leff", Direction.WEST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.WEST, Terrain.WALL);
        assertNotEquals("truck can turn leff", Direction.WEST,
                        truck.chooseDirection(neighbors));

    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionAhead() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.NORTH, Terrain.STREET);
        assertEquals("truck can turn leff", Direction.NORTH, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        assertEquals("truck can turn leff", Direction.NORTH, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        assertEquals("truck can turn leff", Direction.NORTH, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.NORTH, Terrain.WALL);
        assertNotEquals("truck can turn leff", Direction.NORTH,
                        truck.chooseDirection(neighbors));

    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionTurnRight() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.EAST, Terrain.STREET);
        assertEquals("truck can turn leff", Direction.EAST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        assertEquals("truck can turn leff", Direction.EAST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.EAST, Terrain.CROSSWALK);
        assertEquals("truck can turn leff", Direction.EAST, truck.chooseDirection(neighbors));
        neighbors.clear();
        neighbors.put(Direction.EAST, Terrain.TRAIL);
        assertNotEquals("truck can turn leff", Direction.EAST,
                        truck.chooseDirection(neighbors));
    }

//    /**
//     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
//     */
//    @Test
//    public void testTruckChooseDirectionWest() {
//        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
//        final Truck truck = new
//    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionMustReverse() {

//        for (final Terrain t : Terrain.values()) {
//            if (t != Terrain.LIGHT && t != Terrain.STREET && t != Terrain.CROSSWALK) {

        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.TRAIL);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.STREET);

        final Truck truck = new Truck(0, 0, Direction.WEST);

        // the Human must reverse and go SOUTH
        assertEquals(" Truck choose reverse", Direction.SOUTH,
                     truck.chooseDirection(neighbors));
        assertNotEquals(" Truck choose reverse", Direction.WEST,
                        truck.chooseDirection(neighbors));

        neighbors.clear();

        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.TRAIL);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.STREET);

        final Truck truck2 = new Truck(0, 0, Direction.NORTH);

        // the Human must reverse and go SOUTH
        assertEquals(" Truck choose reverse", Direction.EAST,
                     truck2.chooseDirection(neighbors));

    }

    /**
     * Test method for {@link model.AbstractVehicle#collide(model.Vehicle)}.
     * Test method for {@link model.AbstractVehicle#getImageFileName()}.
     */
    @Test
    public void testCollideGetImageFileName() {
        final Truck truck = new Truck(12, 5, Direction.WEST);
        final Car car = new Car(12, 5, Direction.SOUTH);
        final Taxi taxi = new Taxi(12, 5, Direction.SOUTH);

        assertEquals(" failed to get Image File Name", "truck.gif", truck.getImageFileName());

        truck.collide(car);
        assertEquals(" failed to get Image File Name", "truck.gif", truck.getImageFileName());
        assertTrue("Truck not death", truck.isAlive());

        car.collide(truck);
        assertEquals(" failed to get Image File Name", "car_dead.gif", car.getImageFileName());
        assertFalse("car not death", car.isAlive());

        taxi.collide(car);
        assertEquals(" failed to get Image File Name", "taxi.gif", taxi.getImageFileName());
        assertTrue("car not death", taxi.isAlive());

    }

    /**
     * Test method for {@link model.AbstractVehicle#reset()}.
     */
    @Test
    public void testReset() {
        final Truck truck = new Truck(19, 9, Direction.WEST);
        truck.setX(6);
        truck.setY(7);
        truck.setDirection(Direction.SOUTH);

        assertEquals("failed to set X", 6, truck.getX());
        assertEquals("failed to set Y", 7, truck.getY());

        truck.reset();
        assertEquals("failed to set X", 19, truck.getX());
        assertEquals("failed to set Y", 9, truck.getY());
        assertEquals("failed to set Direction", Direction.WEST, truck.getDirection());
    }

    /**
     * Test method for {@link model.AbstractVehicle#collide(model.Vehicle)}.
     * Test method for {@link model.AbstractVehicle#getDeathTime()}.
     * Test method for {@link model.AbstractVehicle#poke()}.
     * Test method for {@link model.AbstractVehicle#isAlive()}.
     */
    @Test
    public void testPoke() {
        final Truck truck = new Truck(4, 5, Direction.WEST);
        final Atv atv = new Atv(4, 5, Direction.SOUTH);
        final Car car = new Car(4, 5, Direction.EAST);
        final Taxi taxi = new Taxi(4, 5, Direction.NORTH);
        truck.collide(car);
        truck.getDeathTime();
        truck.isAlive();
        truck.poke();
        atv.collide(car);
        atv.getDeathTime();
        atv.isAlive();
        atv.poke();
        taxi.collide(car);
        taxi.getDeathTime();
        taxi.isAlive();
        taxi.poke();
        car.collide(atv);
        car.getDeathTime();
        car.isAlive();
        car.poke();

        assertEquals("failed to deadtime", 0, truck.getDeathTime());
        assertTrue("failed to collode", taxi.isAlive());
        assertTrue("failed to isAlive", atv.isAlive());
        assertTrue("failed to collode", car.isAlive());

    }
}
