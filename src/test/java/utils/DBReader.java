package utils;
import models.Purchases;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DBReader {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "postgres";
    private final static String USER_PASSWORD = "postgres";

    private final static String QUERY_SELECT_ALL = "select * from purchases";
    private final static String QUERY_SELECT_MORE = "select * from purchases where purchases >= 7";
    private final static String QUERY_SELECT_LESS = "select * from purchases where purchases < 7 and purchases > 0";
    private final static String QUERY_INSERT = "insert into purchases values(?, ?, ?, ?, ?)";
    private final static String QUERY_DELETE = "delete from purchases where id=?";
    private final static String QUERY_UPDATE = "update purchases set username=? where id=?";

    public static List < Purchases > getAllDataFromDB() {
        List < Purchases > dataList = new ArrayList < > ();

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_ALL);

            while (resultSet.next()) {
                Purchases purchase = new Purchases(resultSet.getString("username"),
                        resultSet.getInt("amount"), resultSet.getInt("purchases"),
                        resultSet.getInt("fee"));
                dataList.add(purchase);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return dataList;
    }

    public static List < Purchases > getDataFromDBLarge() {
        List < Purchases > dataList1 = new ArrayList < > ();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_MORE);

            while (resultSet.next()) {
                Purchases purchase = new Purchases(resultSet.getString("username"),
                        resultSet.getInt("amount"), resultSet.getInt("purchases"),
                        resultSet.getInt("fee"));
                dataList1.add(purchase);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return dataList1;
    }

    public static List < Purchases > getDataFromDBSmall() {
        List < Purchases > dataList2 = new ArrayList < > ();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_LESS);

            while (resultSet.next()) {
                Purchases purchase = new Purchases(resultSet.getString("username"),
                        resultSet.getInt("amount"), resultSet.getInt("purchases"),
                        resultSet.getInt("fee"));
                dataList2.add(purchase);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return dataList2;
    }

    public static void insertDataToDB(int id, String username, int amount, int purchases, double fee) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setInt(3, amount);
            preparedStatement.setInt(4, purchases);
            preparedStatement.setDouble(5, fee);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }
    public static void deleteDataFromDB(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

    public static void updateUsernameInDB(String username, int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

}
