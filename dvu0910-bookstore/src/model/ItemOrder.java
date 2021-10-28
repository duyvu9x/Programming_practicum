/**
 * TCSS 305B Assignment 2 UW Bookstore.
 * 
 */
package model;

/**
 * ItemOrder class have item and quantity.
 * @author DuyVu
 * @version Winter 2021
 *
 */
public final class ItemOrder {
    /**
     * myItem is item of order.
     */
    private Item myItem;
    /**
     * myQuantity is quantity of order item.
     */
    private int myQuantity;
    

   /**
   * ItemOrder to save the item and quantity.
   * @param theItem call the item
   * @param theQuantity call the quantity of the item order
   * @throws IllegalArgumentException for it not positive quantity
   */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theQuantity < 0) {  
            throw new IllegalArgumentException(" the quantity need positive number");
        } else {
            this.myItem = theItem;
            this.myQuantity = theQuantity;  
        }  
    }
    
    /**
     * method to get item.
     * @return the item
     */

    public Item getItem() {
        return this.myItem;
    }
    
    /**
     * method to get quantity of item.
     * @return quantity of item.
     */
    public int getQuantity() {
        return this.myQuantity;
    }

    @Override
    // return the quantity follow the instructor
    public String toString() {
        return this.getQuantity() + "of " + this.getItem().toString();
       
    }

}
