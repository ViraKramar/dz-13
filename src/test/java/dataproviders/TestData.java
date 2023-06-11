package dataproviders;

import org.testng.annotations.DataProvider;

import static utils.DBReader.*;

public class TestData {
    @DataProvider(name = "dataDB")
    public static Object[][] dataFromDB() {
        return getAllDataFromDB().stream().map(purchase -> new Object[] {
                        purchase.getAmount(), purchase.getPurchases(),
                        purchase.getFee()
                })
                .toArray(Object[][]::new);
    }
    @DataProvider(name = "dataFromDBLarge")
    public static Object[][] dataFromDBLarge() {
        return getDataFromDBLarge().stream().map(purchase -> new Object[] {
                        purchase.getAmount(), purchase.getPurchases(),
                        purchase.getFee()
                })
                .toArray(Object[][]::new);
    }

    @DataProvider(name = "dataFromDBSmall")
    public static Object[][] dataFromDBSmall() {
        return getDataFromDBSmall().stream().map(purchase -> new Object[] {
                        purchase.getAmount(), purchase.getPurchases(),
                        purchase.getFee()
                })
                .toArray(Object[][]::new);
    }
}