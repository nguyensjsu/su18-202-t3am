package model;

public class Card {

    public Card(String number, String code, double balance) {
        this.number = number;
        this.code = code;
        this.balance = balance;
    }

    public Card() {
    }

    String number = "000000000";
    String code = "000";
    double balance = 0;
    long date_added = 0;
    long uid;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
