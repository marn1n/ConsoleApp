package app;

import java.sql.*;

public class DBUtilities {
    String url = "jdbc:mysql://localhost/?user=root&password=root";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public DBUtilities() {
        try {
            connection = DriverManager.getConnection(url);
            connection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS appDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
            connection.createStatement().executeUpdate("USE appDB");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS items (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "date DATE," +
                    "sum INT NULL," +
                    "currency VARCHAR (45)," +
                    "item VARCHAR (255)" +
                    ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeStatement(String stmt) {
        try {
            statement = connection.createStatement();
            statement.execute(stmt);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet read(String stmt) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(stmt);
            return resultSet;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultSet;
    }

    public void disconnect() {

        try {
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}