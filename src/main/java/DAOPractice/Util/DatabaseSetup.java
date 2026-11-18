package DAOPractice.Util;

import ConnectionPooling.HikariCPConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        String query = "create table if not exists employees (id int primary key auto_increment,name varchar(50) not null,email varchar(100) unique not null,department_id int not null,salary decimal(10,2)not null)";
       try(Connection connection = HikariCPConnection.getConnection();
           Statement statement = connection.createStatement()){
           statement.executeUpdate(query);
           System.out.println("Employee Table created Successfully");
       }
       catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }

}
