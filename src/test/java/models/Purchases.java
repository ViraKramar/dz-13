package models;

public class Purchases {
    private String username;
    private int amount;
    private int purchases;
    private double fee;

    public Purchases(String username, int amount, int purchases, double fee) {
        this.username = username;
        this.amount = amount;
        this.purchases = purchases;
        this.fee = fee;
    }

    public int getAmount() {
        return amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

}
