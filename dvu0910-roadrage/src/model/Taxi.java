/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.Map;

/**
 * class for object Taxi.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class Taxi extends AbstractVehicle {
    /**
     * MY_DEATH_TIME the death time of car when collide return true.
     */
    private static final int MY_DEATH_TIME = 15;

    /**
     * time stop at cross walk when light change red to green.
     */
    private static final int MY_TIME_CROSSWALK = 3;
    /**
     * MY_VEHICLE_NAME to run the image of vehicle.
     */
    private static final String MY_VEHICLE_NAME = "taxi";
    /**
     * myCountTempWait count time wait when light change red to green. in
     * crossWalk.
     */
    private byte myCountTempWait;

    /**
     * construct a Taxi with position , direction and death time.
     * 
     * @param theX position Y of Taxi.
     * @param theY position Y of Taxi.
     * @param theDir direction of Taxi.
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME, MY_VEHICLE_NAME);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * method to check condition light in cross walk.
     * @param theLight light in cross walk.
     * @return boolean can pass cross walk
     */
    private boolean passCrossWalk(final Light theLight) {
        boolean canPassCrossWalk = false;
        if (theLight == Light.GREEN) {
            canPassCrossWalk = true;
        }
        if (theLight == Light.YELLOW) {
            canPassCrossWalk = true;
        }
        if (theLight == Light.RED) {
            if (myCountTempWait == MY_TIME_CROSSWALK) {
                myCountTempWait = 0;
                canPassCrossWalk = true;
            } else {
                myCountTempWait++;
            }
        }

        return canPassCrossWalk;
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canGoNext = false;
        if (theTerrain == Terrain.STREET) {
            canGoNext = true;
        }

        if (theTerrain == Terrain.LIGHT
            && (theLight == Light.YELLOW || theLight == Light.GREEN)) {
            canGoNext = true;
        }

        if (theTerrain == Terrain.CROSSWALK) {
            canGoNext = passCrossWalk(theLight);
        }
        return canGoNext;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
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
            chooseDirection = getDirection().left();
        } else {
            chooseDirection = getDirection().reverse();

        }
        return chooseDirection;
    }

}
