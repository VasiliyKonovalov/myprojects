package sqlite;

import java.io.IOException;
import java.sql.*;

public class HelloSQLite {
    public static void main(String[] args) throws IOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\myprojects\\database\\test.sqlite");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            //statement.executeUpdate("drop table if exists person");
            ResultSet rs = statement.executeQuery("select * from test");
            while (rs.next()) {
                System.out.print("id = " + rs.getInt("id") + "   ");
                System.out.println("user = " + rs.getString("user"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
