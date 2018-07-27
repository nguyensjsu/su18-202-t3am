
import helper.CoreTransactionLogicHelper;
import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.Purchase;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author syle
 */
public class CoreTransactionLogicHelperTest {
    @Before
     public void setUp() {
        // todo expand
     }

     @Test
     public void testValidTransaction() throws Exception {
        assertEquals("Has enougn balance to buy", true, CoreTransactionLogicHelper.isTransactionValid(2, 1));
     }
     
     @Test
     public void testInvalidTransaction() throws Exception {
        assertEquals("Not enougn balance to buy", false, CoreTransactionLogicHelper.isTransactionValid(1, 2));
     }
     
     @Test
     public void testGetRemainingBalance(){
         List<Card> cards = new ArrayList<>();
         List<Purchase> purchases = new ArrayList<>();
         
         // add 20, 40, 60
         // deduct 30
         // remaining = 20 + 40 + 60 - 30  - 10 = 80
         
         cards.add(new Card("111111111", "111", 20));
         cards.add(new Card("111111112", "222", 40));
         cards.add(new Card("111111113", "333", 60));
         
         purchases.add(new Purchase(30, "Team Offsite"));
         purchases.add(new Purchase(10, "Morning Drink"));
         
         
         assertEquals("remaining = 20 + 40 + 60 - 30  - 10 = 80", 80, CoreTransactionLogicHelper.getRemainingBalance(cards, purchases), 0.0000000001);
     }
     
     
     @Test
     public void testIsValidUserId(){
         assertEquals("null", false, CoreTransactionLogicHelper.isUserIdValid(null));
         assertEquals("empty string", false, CoreTransactionLogicHelper.isUserIdValid(""));
         assertEquals("non-empty whitespace", false, CoreTransactionLogicHelper.isUserIdValid("     "));
         
         assertEquals("valid", true, CoreTransactionLogicHelper.isUserIdValid("abcdef-12345"));
         
         
     }

}
