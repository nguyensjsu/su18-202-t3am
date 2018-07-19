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
public class Purchase extends Transaction {
    long purchase_id = 0;
    String note = ""; // purchase note. Default: empty string, in the event that user does not enter any note.
    
    public Purchase(){}

    public Purchase(double balance, String note) {
        this.balance = balance;
        this.note = note;
    }

    public long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(long purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
