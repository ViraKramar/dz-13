package operations;
import com.rd.calculator.CalculateTotalAmount;

import dataproviders.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFeeCalculation {

    @Test(dataProvider = "dataFromDBSmall", dataProviderClass = TestData.class,
            description = "Test Total Amount where purchases value < 7")
    public void calculateAmountSmall(int amount, int purchases, double fee) {
        CalculateTotalAmount calculateTotalAmount = new CalculateTotalAmount();

        Assert.assertEquals(calculateTotalAmount.calculateAmountWithFee(amount, purchases, fee),
                amount + (fee * amount / 100),
                String.format("The total amount calculation works with mistake " +
                        "amount [%s], purchases [%s], fee[%s]", amount, purchases, fee));
    }
    @Test(dataProvider = "dataFromDBLarge", dataProviderClass = TestData.class,
            description = "Test Total Amount where purchases value >= 7")
    public void calculateAmountLarge(int amount, int purchases, double fee) {
        CalculateTotalAmount calculateTotalAmount = new CalculateTotalAmount();

        Assert.assertEquals(calculateTotalAmount.calculateAmountWithFee(amount, purchases, fee),
                amount + (fee * amount / 200),
                String.format("The total amount calculation works with mistake " +
                        "amount [%s], purchases [%s], fee[%s]", amount, purchases, fee));
    }
}
