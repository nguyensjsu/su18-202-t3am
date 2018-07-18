/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author syle
 */
public class Transaction {
    protected String uid;
    protected double balance;
    protected long date_added;

    // long and lat where the transaction takes
    protected double latPostion = 0.0;
    protected double longPosition = 0.0;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getDate_added() {
        return date_added;
    }

    public void setDate_added(long date_added) {
        this.date_added = date_added;
    }

    public double getLatPostion() {
        return latPostion;
    }

    public void setLatPostion(double latPostion) {
        this.latPostion = latPostion;
    }

    public double getLongPosition() {
        return longPosition;
    }

    public void setLongPosition(double longPosition) {
        this.longPosition = longPosition;
    }
    
    
}
