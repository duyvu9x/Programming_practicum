/**
 * TCSS 305B Assignment 2 UW Bookstore.
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.Before;
import org.junit.Test;


/**
 * to test the class Item.
 * @author DuyVu
 * @version Winter 2021
 *
 */
public class ItemTest {
    /**
     *  item 1 with bulk.
     */
    private Item myItem1;
    /**
     * item 2 same item 1.
     */
    private Item myItem2;
    
    /**
     * item 3 same name, price but no bulk.
     */
    private Item myItem3;
    /**
     * item 4 same item 3.
     */
    private Item myItem4;

    /**
     * throw exception.
     * @throws java.lang.Exception throw exception.
     */
    @Before
    public void setUp() throws Exception {
        myItem1 = new Item("Book", BigDecimal.valueOf(3.00),
                           3, BigDecimal.valueOf(5.00));
        myItem2 = new Item("Book", BigDecimal.valueOf(3.00),
                           3, BigDecimal.valueOf(5.00));
        myItem3 = new Item("Book", BigDecimal.valueOf(3.00));
        myItem4 = new Item("Book", BigDecimal.valueOf(3.00));
        
    }

    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals("hash code item1 same item2", myItem1.hashCode(), myItem2.hashCode());
        assertNotEquals("hash code item1 != item2", myItem1.hashCode(), myItem3.hashCode());
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimal() {
        assertEquals("item 1 have bulk", true, myItem1.isBulk());
        assertEquals("itiem3 have not bulk", false, myItem3.isBulk());
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal,
     *                                                     int, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        assertEquals("check same with bulk", "Book, $3.0 (3 for $5.0)", myItem1.toString());
        assertEquals("check same display", "Book, $3.0", myItem3.toString());
    }

    /**
     * Test method for {@link model.Item#getPrice()}.
     */
    @Test
    public void testGetPrice() {
        assertEquals("return price 3.00", BigDecimal.valueOf(3.00), myItem1.getPrice());
        assertEquals("return price 3.00", BigDecimal.valueOf(3.00), myItem2.getPrice());
        assertEquals("return price 3.00", BigDecimal.valueOf(3.00), myItem3.getPrice());
        assertEquals("return price 3.00", BigDecimal.valueOf(3.00), myItem4.getPrice());
    }

    /**
     * Test method for {@link model.Item#getBulkQuantity()}.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals("return quantity 3", 3, myItem1.getBulkQuantity());
        assertEquals("returb quabtity 0", 0, myItem3.getBulkQuantity());
    }

    /**
     * Test method for {@link model.Item#getBulkPrice()}.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals("return bulk price 5.00",
                     BigDecimal.valueOf(5.00), myItem1.getBulkPrice());
        assertNotEquals("return bulk price 5.00",
                     BigDecimal.valueOf(5.00), myItem3.getBulkPrice());
    }

    /**
     * Test method for {@link model.Item#isBulk()}.
     */
    @Test
    public void testIsBulk() {
        assertEquals("true if have bulk", true, myItem1.isBulk());
        assertEquals("false if have not bulk", false, myItem3.isBulk());
    }

    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("String of item with bulk", 
                     "Book, $3.0 (3 for $5.0)", myItem1.toString());
        assertEquals("String of item with oulk bulk", 
                     "Book, $3.0", myItem3.toString());
    }

    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertEquals("item 1 == item 2", myItem1, myItem2);
        assertNotEquals("item 1 != item 3", myItem1, myItem3);
    }

}
