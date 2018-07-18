package model;

public class Card extends Transaction{
    String number = "000000000";
    String code = "000";
    
    public Card(){}
    
    public Card(String number, String code, double balance) {
        this.number = number;
        this.code = code;
        this.balance = balance;
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
    
    
}
