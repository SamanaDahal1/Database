package EmployeePaginationPractice;
import ConnectionPooling.HikariCPConnection;
import EmployeeObjectMapping.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    List<Employee> getEmployees(int pageNumber, int pageSize) {
        List<Employee> employees = new ArrayList<>();
        if (pageNumber <= 0 || pageSize <= 0) {
            return employees;
        }
        int offset = (pageNumber - 1) * pageSize;
        String query = "SELECT * FROM employee ORDER BY id ASC LIMIT ? OFFSET ?";
        try (Connection connection = HikariCPConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getDouble("salary")
                    );

                    employees.add(employee);
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return employees;
    }

}
