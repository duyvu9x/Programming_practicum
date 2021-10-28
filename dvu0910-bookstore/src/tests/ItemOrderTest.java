/**
 * TCSS 305B Assignment 2 UW Bookstore.
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;



/**
 * test class ItemOrder.
 * 
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class ItemOrderTest {

    /**
     * item1.
     */
    private final Item myItem1 = new Item("Book", BigDecimal.valueOf(3.00),
                                          3, BigDecimal.valueOf(5.00));
                    
    /**
     * itemOrder1.
     */
    private final ItemOrder myItemOrder1 = new ItemOrder(myItem1, 4);

    /**
     * throw exveotion.
     * @throws java.lang.Exception ad.
     */
    @Before
    public void setUp() throws Exception {
        
        
    }

    /**
     * Test method for {@link model.ItemOrder#ItemOrder(model.Item, int)}.
     */
    @Test
    public void testItemOrder() {
        final ItemOrder itemOrdertest = new ItemOrder(myItem1, 4);
        assertNotNull("have item ", itemOrdertest);
    }

    /**
     * Test method for {@link model.ItemOrder#getItem()}.
     */
    @Test
    public void testGetItem() {
        assertTrue("get right item", myItemOrder1.getItem().equals(myItem1));
    }

    /**
     * Test method for {@link model.ItemOrder#getQuantity()}.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("het quantity is 4", myItemOrder1.getQuantity(), 4);
    }

    /**
     * Test method for {@link model.ItemOrder#toString()}.
     */
    @Test
    public void testToString() {
        
        assertEquals("String is same", "4of Book, $3.0 (3 for $5.0)", myItemOrder1.toString());
    }

}
