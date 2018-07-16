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
    String uid;
    String email;
    long purchase_id;
    long date_added = 0;
    double balance = 0.0;
    String note = ""; // purchase note. Default: empty string, in the event that user does not enter any note.
    // this is a long and lat used for the store position
    double latPostion = 0.0;
    double longPosition = 0.0;

    public Purchase() {
    }

    public Purchase(double balance, String note) {
        this.balance = balance;
        this.note = note;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.uid = uid;
    }	
	
	public long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(long purchase_id) {
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
