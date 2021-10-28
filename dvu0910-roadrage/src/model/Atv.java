/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class for object Atv.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class Atv extends AbstractVehicle {
    /**
     * MY_DEATH_TIME the death time of atv when collide return true.
     */
    private static final int MY_DEATH_TIME = 15;
    /**
     * MY_VEHICLE_NAME to run the image of vehicle.
     */
    private static final String MY_VEHICLE_NAME = "atv";

    /**
     * construct a atv with position , direction and death time.
     * 
     * @param theX position Y of atv.
     * @param theY position Y of atv.
     * @param theDir direction of atv.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME, MY_VEHICLE_NAME);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPassNext = false;
        if (theTerrain != Terrain.WALL) {
            canPassNext = true;
        }
        return canPassNext;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final int num = (int) (Math.random() * list(theNeighbors).size());
        return list(theNeighbors).get(num);

    }

    private List<Direction> list(final Map<Direction, Terrain> theNeighbors) {

        final List<Direction> atvList = new ArrayList<>();
        if (theNeighbors.get(getDirection().left()) != Terrain.WALL) {
            atvList.add(getDirection().left());

        }

        if (theNeighbors.get(getDirection().right()) != Terrain.WALL) {
            atvList.add(getDirection().right());

        }

        if (theNeighbors.get(getDirection()) != Terrain.WALL) {
            atvList.add(getDirection());

        }
        return atvList;

    }

}
