/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.Map;

/**
 * class for object bicycle.
 * 
 * @author DuyVu
 * @version Winter 2021
 */
public class Bicycle extends AbstractVehicle {

    /**
     * MY_DEATH_TIME the death time of bicycle when collide return true.
     */
    private static final int MY_DEATH_TIME = 35;
    /**
     * MY_VEHICLE_NAME to run the image of vehicle.
     */
    private static final String MY_VEHICLE_NAME = "bicycle";

    /**
     * construct a car with position , direction and death time.
     * 
     * @param theX position Y of bicycle.
     * @param theY position Y of bicycle.
     * @param theDir direction of bicycle.
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME, MY_VEHICLE_NAME);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canGoNext = false;
        if (theTerrain == Terrain.STREET) {
            canGoNext = true;
        }
        if (theTerrain == Terrain.TRAIL) {
            canGoNext = true;
        }

        if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN) {
            canGoNext = true;
        }

        if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            canGoNext = true;
        }
        return canGoNext;
    }

    private Direction directionNotInTrail(final Map<Direction, Terrain> theNeighbors) {
        final Direction chooseDirection;
        if (theNeighbors.get(getDirection()) == Terrain.STREET
            || theNeighbors.get(getDirection()) == Terrain.CROSSWALK
            || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            chooseDirection = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET
                   || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK
                   || theNeighbors.get(getDirection().left()) == Terrain.LIGHT) {
            chooseDirection = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET
                   || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK
                   || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {
            chooseDirection = getDirection().right();
        } else {
            chooseDirection = getDirection().reverse();

        }
        return chooseDirection;

    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction chooseDirection;
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            chooseDirection = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            chooseDirection = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            chooseDirection = getDirection().right();
        } else {
            chooseDirection = directionNotInTrail(theNeighbors);

        }
        return chooseDirection;

    }

}
