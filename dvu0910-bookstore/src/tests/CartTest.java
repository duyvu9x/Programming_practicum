/**
 * TCSS 305B Assignment 2 UW Bookstore.
 * 
 */

package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Cart;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for class Cart.
 * 
 * @author DuyVu
 * @version Winter 2021
 */
public class CartTest {
    /**
     * create a a cart.
     */
    private final Cart myCart = new Cart();
    /**
     * item 1.
     */
    private final Item myItem1 =
                    new Item("Book", BigDecimal.valueOf(3.00), 3, BigDecimal.valueOf(5.00));
    /**
     * itemorder have item and quantity.
     */
    private final ItemOrder myItemOrder = new ItemOrder(myItem1, 7);

    /**
     * throw exception.
     * 
     * @throws java.lang.Exception item and itemoder.
     */
    @Before
    public void setUp() throws Exception {
        myCart.add(myItemOrder);

    }

    /**
     * Test method for {@link model.Cart#Cart()}.
     */
    @Test
    public void testCart() {
        assertEquals("cart is 1 ", myCart.getCartSize(), 1);
    }

    /**
     * Test method for {@link model.Cart#add(model.ItemOrder)}.
     */
    @Test
    public void testAdd() {
        myCart.add(myItemOrder);

        assertEquals("Cart have size 1", myCart.getCartSize(), 1);
        myCart.add(myItemOrder);
        assertEquals("Cart add same item (1)", myCart.getCartSize(), 1);
    }

    /**
     * Test method for {@link model.Cart#setMembership(boolean)}.
     */
    @Test
    public void testSetMembership() {
        myCart.setMembership(false);
        String membership = myCart.toString().split(" ")[1];
        assertEquals("membership is flase", membership, "false");

        myCart.setMembership(true);
        membership = myCart.toString().split(" ")[1];
        assertEquals("membership is flase", membership, "true");

    }

    /**
     * Test method for {@link model.Cart#calculateTotal()}.
     */
    @Test
    public void testCalculateTotal() {

        assertEquals("value should 21", myCart.calculateTotal(), new BigDecimal("21.00"));
    }

    /**
     * Test method for {@link model.Cart#clear()}.
     */
    @Test
    public void testClear() {
        myCart.clear();
        assertEquals("cart size empty", myCart.getCartSize(), 0);

    }

    /**
     * Test method for {@link model.Cart#getCartSize()}.
     */
    @Test
    public void testGetCartSize() {
        assertEquals("cart size is 1", myCart.getCartSize(), 1);
    }

    /**
     * Test method for {@link model.Cart#toString()}.
     */
    @Test
    public void testToString() {
        assertTrue("same value", "myHasMembership false , 7 items: Book, $3.0 (3 for $5.0)\n".
                   equals(myCart.toString()));

    }

}
