/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class for object human.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class Human extends AbstractVehicle {

    /**
     * MY_DEATH_TIME the death time of truck when collide return true.
     */
    private static final int MY_DEATH_TIME = 45;
    /**
     * MY_VEHICLE_NAME to run the image of vehicle.
     */
    private static final String MY_VEHICLE_NAME = "human";

    /**
     * call the implement of class abtractVehiclse.
     * 
     * @param theX position(X) of vehicle.
     * @param theY position(Y) of vehicle.
     * @param theDir current the direction of vehicle.
     */

    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME, MY_VEHICLE_NAME);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPassNext = false;
        if (theTerrain == Terrain.GRASS) {
            canPassNext = true;
        }
        if (theTerrain == Terrain.CROSSWALK
            && (theLight == Light.YELLOW || theLight == Light.RED)) {
            canPassNext = true;
        }
        return canPassNext;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction chooseDirection;
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            chooseDirection = getDirection();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            chooseDirection = getDirection().right();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            chooseDirection = getDirection().left();
        } else {
            if (list(theNeighbors).isEmpty()) {
                chooseDirection = getDirection().reverse();
            } else {
                final int num = (int) (Math.random() * list(theNeighbors).size());
                chooseDirection = list(theNeighbors).get(num);
            }
        }
        return chooseDirection;
    }

    private List<Direction> list(final Map<Direction, Terrain> theNeighbors) {

        final List<Direction> humanList = new ArrayList<>();

        if (theNeighbors.get(getDirection()) == Terrain.GRASS) {
            humanList.add(getDirection());
        }
        if (theNeighbors.get(getDirection().right()) == Terrain.GRASS) {
            humanList.add(getDirection().right());
        }
        if (theNeighbors.get(getDirection().left()) == Terrain.GRASS) {
            humanList.add(getDirection().left());
        }

        return humanList;

    }
}
