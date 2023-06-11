package com.rd.calculator;

public class CalculateTotalAmount {

    public double calculateAmountWithFee(int amount, int purchases, double fee) {

        double totalAmount;
        if (purchases < 0 || amount <= 0 || fee < 0) {
            throw new RuntimeException(String.format("Check purchasing data " +
                    "amount [%s], purchases [%s], fee[%s]", amount, purchases, fee));
        }
        if (purchases < 7) {
            totalAmount = amount + (fee * amount / 100);
        } else {
            totalAmount = amount + (fee * amount / 200);
        }
        purchases++;
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Total Purchases: " + purchases);
        return totalAmount;
    }
}
