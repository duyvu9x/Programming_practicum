/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// import java.util.Random;

/**
 * class for object Truck.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class Truck extends AbstractVehicle {

    /**
     * the death time of truck when collide return true.
     */
    private static final int MY_DEATH_TIME = 0;
    /**
     * MY_VEHICLE_NAME to run the image of vehicle.
     */
    private static final String MY_VEHICLE_NAME = "truck";

    /**
     * construct a human with position , direction and death time.
     * 
     * @param theX position Y of human.
     * @param theY position Y of human.
     * @param theDir direction of Taxi.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME, MY_VEHICLE_NAME);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canGoNext = false;
        if ((theTerrain == Terrain.STREET) || (theTerrain == Terrain.LIGHT)) {
            canGoNext = true;
        }
        if (theTerrain == Terrain.CROSSWALK
            && (theLight == Light.GREEN || theLight == Light.YELLOW)) {
            canGoNext = true;

        }
        return canGoNext;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
//        final Random random = new Random();
//        
//        final Direction directions;
//        final List<Direction> possibleNext = new ArrayList<>();
//        
//        if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
//                        || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
//                        || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
//            possibleNext.add(getDirection().left());
//        
//        } 
//        
//        if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
//                        || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
//                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
//            possibleNext.add(getDirection().right());
//        
//        } 
//        
//        if (theNeighbors.get(getDirection()) == Terrain.STREET 
//                        || theNeighbors.get(getDirection()) == Terrain.LIGHT
//                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
//            possibleNext.add(getDirection());
//        
//        }
//        
//        if (possibleNext.isEmpty()) {
//            directions = getDirection().reverse();
//        
//        } else {
//            final int randomIndex = random.nextInt(possibleNext.size());
//            directions = possibleNext.get(randomIndex);
//        }
//        
//        return directions;
        final Direction directions;

        if (list(theNeighbors).isEmpty()) {
            directions = getDirection().reverse();
        } else {
            final int num = (int) (Math.random() * list(theNeighbors).size());
            directions = list(theNeighbors).get(num);
        }
        return directions;
    }

    /**
     * help method to save direction list.
     * 
     * @param theNeighbors to check the position next step.
     * @return truckList to remember the list can go.
     */

    private List<Direction> list(final Map<Direction, Terrain> theNeighbors) {

        final List<Direction> truckList = new ArrayList<>();
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET
            || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
            || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            truckList.add(getDirection().left());

        }

        if (theNeighbors.get(getDirection().right()) == Terrain.STREET
            || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
            || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            truckList.add(getDirection().right());

        }

        if (theNeighbors.get(getDirection()) == Terrain.STREET
            || theNeighbors.get(getDirection()) == Terrain.LIGHT
            || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            truckList.add(getDirection());

        }
        return truckList;

    }

}
