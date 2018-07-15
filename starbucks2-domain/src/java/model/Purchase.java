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
public class Purchase {
    long uid;
    long date_added = 0;
    double balance;
    String note; // purchase note
    // this is a long and lat used for the store position
    double latPostion;
    double longPosition;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getDate_added() {
        return date_added;
    }

    public void setDate_added(long date_added) {
        this.date_added = date_added;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getLatPostion() {
        return latPostion;
    }

    public void setLatPostion(long latPostion) {
        this.latPostion = latPostion;
    }

    public double getLongPosition() {
        return longPosition;
    }

    public void setLongPosition(long longPosition) {
        this.longPosition = longPosition;
    }
    
    
}
