/**
 * TCSS 305B Assignment 2 UW Bookstore.
 * 
 */
package model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * class Item to have information of item.
 * 
 * @author Duyvu
 * @version Winter 2021
 * 
 */
public final class Item {
    /**
     * name of the item.
     */
    private String myName;
    /**
     * price of an item.
     */
    private BigDecimal myPrice;
    /**
     * bulk quantity for membership.
     */
    private int myBulkQuantity;
    /**
     * buck price for a bulk.
     */
    private BigDecimal myBulkPrice;

    /**
     * class Item to have name of item and price.
     * 
     * @param theName name of item.
     * @param thePrice price of item.
     */

    public Item(final String theName, final BigDecimal thePrice) {
        this.myName = theName;
        this.myPrice = thePrice;
    }

    /**
     * class Item to have value item and bulk and bulkprice.
     * 
     * @param theName take name of item
     * @param thePrice price of item
     * @param theBulkQuantity quantity of bulk
     * @param theBulkPrice price of a bulk
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        this(theName, thePrice);
        this.myBulkPrice = theBulkPrice;
        this.myBulkQuantity = theBulkQuantity;

    }

    /**
     * method to get the price of item.
     * 
     * @return price of item
     */

    public BigDecimal getPrice() {
        return this.myPrice;

    }

    /**
     * method to return bulk quantity.
     * 
     * @return myBulkQuantity of the item
     */
    public int getBulkQuantity() {
        final int ans;
        if (isBulk()) {
            ans = this.myBulkQuantity;
        } else {
            ans = 0;
        }
        return ans;
    }

    /**
     * method to return the bulk price and null if item have no bulk.
     * 
     * @return myBulkPrice price of a bulk
     */
    public BigDecimal getBulkPrice() {
        final BigDecimal ans;
        if (isBulk()) {
            ans = this.myBulkPrice;
        } else {
            ans = BigDecimal.ZERO;
        }
        return ans;
    }

    /**
     * method to check the item have buck.
     * 
     * @return boolean to check item have bulk.
     */
    public boolean isBulk() {
        final boolean ans;
        if (this.myBulkQuantity > 0) {
            ans = true;
        } else {
            ans = false;
        }
        return ans;
    }

    @Override
    // toString hien thi ten , huong dan cua lua chon
    // change String to “X, $19.99 (5 for $89.99)
    // with out is bulk“X, $19.99”
    public String toString() {

        String str = "";

        if (isBulk()) {
            str = myPrice.toPlainString() + " (" + myBulkQuantity + " for $" + myBulkPrice
                  + ")";

        } else {

            str = myPrice.toPlainString();
        }
        return myName + ", $" + str;

    }

//            str.append(myName.toString());
//            str.append(", ");
//            str.append("$");
//            str.append(myPrice.toPlainString());
//            str.append(" (");
//            str.append(myBulkQuantity);
//            str.append(" for $" + myBulkPrice.toPlainString());
//            str.append(")");
//        } else {
//            str.append(myName);
//            str.append(", $" + myPrice.toPlainString());
//        }
//        return str.toString();

    @Override
    public boolean equals(final Object theOther) {

        final Item copy = (Item) theOther;
        if (this.isBulk() && copy.isBulk()) {
            if (this.myName.equals(copy.myName) 
                            && this.getPrice().equals(copy.getPrice()) 
                            && this.getBulkPrice().equals(copy.getBulkPrice()) 
                            && this.getBulkQuantity() == copy.getBulkQuantity()) {
                return true;
            } else if (this.myName.equals(copy.myName) 
                                    && this.getPrice().equals(copy.getPrice())) {
                return true;
            }
        }
        return false;
    }

       

    @Override
    public int hashCode() {
        final int code;

        if (isBulk()) {
            code = Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
        } else {
            
            code = Objects.hash(myName, myPrice);
        }
        return code;
    }

}
