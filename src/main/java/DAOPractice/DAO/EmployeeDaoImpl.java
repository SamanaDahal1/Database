package DAOPractice.DAO;

import ConnectionPooling.HikariCPConnection;
import DAOPractice.Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public boolean addEmployee(Employee employee) {
        String addQuery = "insert into employees (name,email,department_id,salary) values (?,?,?,?)";

        try (Connection connection = HikariCPConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery))
        {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2,employee.getEmail());
            preparedStatement.setInt(3,employee.getDepartmentId());
            preparedStatement.setDouble(4,employee.getSalary());
            int result =preparedStatement.executeUpdate();
            return result>0;
        }
        catch (SQLException e){
            System.out.println("Something went wrong during adding: "+e.getMessage());
            return false;
        }
    }
    @Override
    public boolean updateEmployee(Employee employee) {
//        String queryUpdate = "update employees set name = ? , "
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public List<Employee> searchByName(String name) {
        return List.of();
    }

    @Override
    public List<Employee> findByDep(int departmentId) {
        return List.of();
    }

    @Override
    public List<Employee> findAllPagination(int pageNum, int pageSize) {
        return List.of();
    }
}
