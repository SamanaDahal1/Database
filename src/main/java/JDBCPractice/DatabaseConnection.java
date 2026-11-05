package JDBCPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection connection;
   public void conn() {
        String url = "jdbc:mysql://localhost:3306/jdbc_practice";
        String username = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
