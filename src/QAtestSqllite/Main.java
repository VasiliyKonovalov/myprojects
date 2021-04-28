package QAtestSqllite;
import java.sql.*;

public class Main {
    // Блок объявления констант
    public static final String DB_URL = "jdbc:sqlite:D:/myprojects/db/RiderSoft.db";
    public static final String DB_Driver = "java.sql.Driver";

    public static void main(String[] args) {
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL);//соединение с БД
            System.out.println("Соединение с СУБД выполнено.");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

//          Считаем Development

            int salaryDevelopment = 0;
            ResultSet ds = statement.executeQuery("SELECT * FROM EMPLOYEES WHERE DEPARTMENT ='Development'");
            while (ds.next()) {
                salaryDevelopment += ds.getInt("SALARY");
            }
            System.out.println("Development: " + salaryDevelopment);

//          Считаем Sales

            int salarySales = 0;
            ResultSet ss = statement.executeQuery("SELECT * FROM EMPLOYEES WHERE DEPARTMENT ='Sales'");
            while (ss.next()) {
                salarySales += ss.getInt("SALARY");
            }
            System.out.println("Sales: " + salarySales);

//          Считаем Management

            int salaryManagement = 0;
            ResultSet bs = statement.executeQuery("SELECT * FROM EMPLOYEES WHERE DEPARTMENT ='Management'");
            while (bs.next()) {
                salaryManagement += bs.getInt("SALARY");
            }
            System.out.println("Management: " + salaryManagement);

//          отключение от БД

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");


        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }
}