/*
 * TCSS 305 - Road Rage
 */

package model;

/**
 * Class implement from class Vehicle to have general implements for vehicle.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Save the start position of X.
     */
    private final int myStartX;

    /**
     * Save the start position of Y.
     */
    private final int myStartY;

    /**
     * Save the current position(X) of vehicle.
     */
    private int myCurX;

    /**
     * Save the current position(Y) of vehicle.
     */
    private int myCurY;

    /**
     * Save the current Direction of vehicle.
     */
    private Direction myCurDirection;

    /**
     * Save the start Direction of vehicle.
     */
    private final Direction myStartDirection;

    /**
     * Save the status of vehicle.
     */
    private boolean myAlive;

    /**
     * Save the DeathTime of each vehicle.
     */
    private final int myDeathTime;

    /**
     * count the death time when car collided.
     */
    private int myCountDeathTime;

    /**
     * take the name of vehicle.
     */
    private final String myVehicleName;

    /**
     * element in vehicle.
     * 
     * @param theX the position(X) of the vehicle
     * @param theY the position(Y) of the vehicle
     * @param theDir the direction of the vehicle
     * @param theDeathTime the DeathTime of vehicle.
     * @param theVehicleName name of vehicle.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir,
                              final int theDeathTime, final String theVehicleName) {
        myStartX = theX;
        myStartY = theY;
        myCurX = myStartX;
        myCurY = myStartY;

        myDeathTime = theDeathTime;
        myCurDirection = theDir;
        myStartDirection = theDir;
        myAlive = true;
        myCountDeathTime = 0;
        myVehicleName = theVehicleName;
    }

    /**
     * Check the vehicle is alive collided.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (this.isAlive() && theOther.isAlive()
            && (this.myDeathTime > theOther.getDeathTime())) {
            myAlive = false;

        } else if ((isAlive() && theOther.isAlive())
                   && (myDeathTime < theOther.getDeathTime())) {
            myAlive = true;

        } else if ((isAlive() && theOther.isAlive())
                   && (myDeathTime == theOther.getDeathTime())) {
            myAlive = true;
        }
    }
    
    /**
     * get death time.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;

    }
    /**
     * assign name and status.
     */
    @Override
    public String getImageFileName() {
        final StringBuilder fileImage = new StringBuilder(myVehicleName);

        if (myAlive) {
            fileImage.append(".gif");

        } else {
            fileImage.append("_dead.gif");
        }
        return fileImage.toString();

    }

    @Override
    public Direction getDirection() {
        return myCurDirection;
    }

    @Override
    public int getX() {
        return myCurX;
    }

    @Override
    public int getY() {
        return myCurY;
    }

    @Override
    public boolean isAlive() {
        return myAlive;

    }

    /**
     * count the time death.
     */
    @Override
    public void poke() {
        if (myCountDeathTime == myDeathTime) {
            setDirection(Direction.random());
            myAlive = true;
            myCountDeathTime = 0;
        } else {
            myCountDeathTime++;
        }

    }

    /**
     * reset to at start point.
     */
    @Override
    public void reset() {
        myCurX = myStartX;
        myCurY = myStartY;
        myCurDirection = myStartDirection;
        myAlive = true;

    }

    /**
     * to reassigns current Direction (myCurDirection).
     */
    @Override
    public void setDirection(final Direction theDir) {
        myCurDirection = theDir;
    }

    /**
     * to reassigns current X (myCurX).
     */
    @Override
    public void setX(final int theX) {
        myCurX = theX;

    }

    /**
     * to reassigns current Y (myCurY).
     */
    @Override
    public void setY(final int theY) {
        myCurY = theY;

    }

}
