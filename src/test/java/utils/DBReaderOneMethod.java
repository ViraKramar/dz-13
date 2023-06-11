package utils;

import models.Purchases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReaderOneMethod {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "postgres";
    private final static String USER_PASSWORD = "postgres";
    private final static String QUERY_SELECT = "select * from purchases where id=?";
    private final static String QUERY_INSERT = "insert into purchases values(?, ?, ?, ?, ?)";
    private final static String QUERY_DELETE = "delete from purchases where id=?";
    private final static String QUERY_UPDATE = "update purchases set username=? where id=?";
    public static List < Purchases > applyMethodsToDB() {
        List < Purchases > dataList = new ArrayList < > ();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement1 = connection.prepareStatement(QUERY_SELECT);
            preparedStatement1.setInt(1, 3);
            ResultSet resultSet = preparedStatement1.executeQuery();

            while (resultSet.next()) {
                Purchases purchase = new Purchases(resultSet.getString("username"),
                        resultSet.getInt("amount"), resultSet.getInt("purchases"),
                        resultSet.getInt("fee"));
                dataList.add(purchase);
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement(QUERY_INSERT);
            preparedStatement2.setInt(1, 400);
            preparedStatement2.setString(2, "some400");
            preparedStatement2.setInt(3, 4000);
            preparedStatement2.setInt(4, 40);
            preparedStatement2.setDouble(5, 1);
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement3.setString(1, "newName");
            preparedStatement3.setInt(2, 8);
            preparedStatement3.executeUpdate();

            PreparedStatement preparedStatement4 = connection.prepareStatement(QUERY_DELETE);
            preparedStatement4.setInt(1, 10);
            preparedStatement4.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " +
                    "URL [%s], Name [%s], Pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return dataList;
    }
    public static void main(String[] args) {
        applyMethodsToDB();
    }

}