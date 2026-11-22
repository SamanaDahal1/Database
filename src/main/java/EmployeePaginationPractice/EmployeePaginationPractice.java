package EmployeePaginationPractice;
import ConnectionPooling.HikariCPConnection;
import EmployeeObjectMapping.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    int getTotalPages(int pageSize){
        if(pageSize>0){
            int totalEmp = getTotalEmployees();
            int totalPage = ((totalEmp +pageSize)-1)/pageSize;
            return totalPage;

        }
        return 0;
    }

}
