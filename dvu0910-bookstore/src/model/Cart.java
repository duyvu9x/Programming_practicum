/**
 * TCSS 305B Assignment 2 UW Bookstore.
 * 
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Cart to put items order in.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class Cart {
    /**
     * myCart have type Map with key is Item and quantity is integer.
     */

    private Map<Item, Integer> myCart;
    /**
     * myHasMembership for person have membership check box to have different
     * prices.
     */
    private boolean myHasMembership;

    /**
     * return a hash map of item and quantity.
     */
    public Cart() {
        myCart = new HashMap<Item, Integer>();
        myHasMembership = false;
    }

    /**
     * Method add to get the item and the quantity of item order.
     * 
     * @param theOrder is order items
     */
    public void add(final ItemOrder theOrder) {
        if (theOrder != null) {
            myCart.put(theOrder.getItem(), theOrder.getQuantity());
        }
    }

    // to discount the member
    public void setMembership(final boolean theMembership) {
        myHasMembership = theMembership;
    }

    /**
     * calculate method to get cost when have membership and no membership.
     * 
     * @return BigDecimal value of cost item in cart.
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = new BigDecimal("0.00");
        if (!myCart.isEmpty()) {

            for (final Item item : myCart.keySet()) {
                final int quantity = myCart.get(item);

//        final Iterator<Item> iterator = myCart.keySet().iterator();
//
//        while (iterator.hasNext()) {
//            final Item items = iterator.next();
//            // Integer i = Cart.get(iterator.next());
//            final BigDecimal quantity = BigDecimal.valueOf(items.getQuantity());

                if (myHasMembership && item.isBulk() && quantity >= item.getBulkQuantity()) {
                    final BigDecimal isBuckQuantity =
                                    BigDecimal.valueOf(quantity / item.getBulkQuantity());
                    final BigDecimal isNotBuckQuantity =
                                    BigDecimal.valueOf(quantity % item.getBulkQuantity());
                    final BigDecimal totalBulk = isBuckQuantity.multiply(item.getBulkPrice());
                    final BigDecimal totalNotBulk =
                                    isNotBuckQuantity.multiply(item.getPrice());
                    total = total.add(totalNotBulk.add(totalBulk));
                } else {
                    total = total.add(item.getPrice().multiply(BigDecimal.valueOf(quantity)));
                }
            }
        }
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Clear cart to empty.
     */
    public void clear() {
        myCart = new HashMap<Item, Integer>();
    }

    public int getCartSize() {
        return myCart.size();
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        str.append("myHasMembership ");
        str.append(myHasMembership);
        str.append(" , ");
        for (final Item aItem : myCart.keySet()) {
            str.append(myCart.get(aItem));
            str.append(" items: ");
            str.append(aItem.toString());
            str.append("\n");
        }
        
        return str.toString();
    }
}
