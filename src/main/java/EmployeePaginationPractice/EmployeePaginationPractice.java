package EmployeePaginationPractice;
import ConnectionPooling.HikariCPConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeePaginationPractice {
    void getTotalEmployees() {
        String query = "select count(*) from employee";
        try (PreparedStatement preparedStatement = HikariCPConnection.getConnection().prepareStatement(query);
        ){

        }
        catch (SQLException e){
            System.out.println("Something went wrong: "+e.getMessage());
        }

    }
}
