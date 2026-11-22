package EmployeePaginationPractice;
import ConnectionPooling.HikariCPConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeePaginationPractice {
    int getTotalEmployees() {
        int count = 0;
        String query = "select count(*) from employees";

        try (Connection connection = HikariCPConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()
        ){

               if( resultSet.next()){
                   count= resultSet.getInt(1);
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong: "+e.getMessage());
        }
        return count;
    }
}
