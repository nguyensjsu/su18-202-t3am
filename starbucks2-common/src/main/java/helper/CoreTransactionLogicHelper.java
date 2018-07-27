/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.List;
import model.Card;
import model.Purchase;
import model.Transaction;

/**
 *
 * @author syle
 */
public class CoreTransactionLogicHelper {
    public static boolean isUserIdValid(String uid){
        return uid != null && uid.trim().length() > 0;
    }
    
    public static boolean isCardNumberValid(String s){
        return s != null && s.trim().length() == 9;
    }
    
    public static boolean isCardCodeValid(String s){
        return s != null && s.trim().length() == 3;
    }
    
    public static boolean isTransactionValid(double remaining_bal, double item_cost){
        return remaining_bal > item_cost;
    }
    
    public static double getRemainingBalance(List<Card> cards, List<Purchase> purchases){
        double remaining_bal = 0;
        
        for(Transaction t : cards){
            remaining_bal += t.getBalance();
        }
        
        for(Transaction t : purchases){
            remaining_bal -= t.getBalance();
        }
        
        return remaining_bal;
    }
}
