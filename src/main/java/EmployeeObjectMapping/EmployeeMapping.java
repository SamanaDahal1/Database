package EmployeeObjectMapping;

import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeMapping {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    public static void main(String[] args) {
       EmployeeMapping employeeMapping = new EmployeeMapping();
       employeeMapping.databaseConnection.conn();
       employeeMapping.create();

    }

    void create(){
        String createQuery = "create table if not exists employee( id int primary key auto_increment,name varchar(30),email varchar(50),salary double)";
        try(
            PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(createQuery))
        {
            preparedStatement.executeUpdate();
            System.out.println("Successfully Created Table ");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    }
