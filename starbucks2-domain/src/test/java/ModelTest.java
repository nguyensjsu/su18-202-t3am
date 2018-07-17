
package model ;


import java.lang.Math;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Card;

/**
 * Testing the Model Classes
 * Hyunwook Shin
 */

public class ModelTest {

     public ModelTest() {
     }

     @Before
     public void setUp() {
        // todo expand
     }

     @Test
     public void CardTest() throws Exception {
        Card card = new Card("123456789", "123", 0);

        // Test that card number apis are working
        assertEquals("123456789", card.getNumber());
        card.setNumber("999999999");
        assertEquals("999999999", card.getNumber());

        // Test that card code apis are working
        assertEquals("123", card.getCode());
        card.setCode("456");
        assertEquals("456", card.getCode());

        // Test that the balance is correct to the nearest cent
        assertTrue(Math.abs( 0 - card.getBalance()) < 0.01);
        card.setBalance(1000);
        assertTrue(Math.abs( 1000 - card.getBalance()) < 0.01);
     }

     @After
     public void tearDown() {
        // todo expand
     }
}
