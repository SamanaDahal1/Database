package DAOPractice.DAO;

import ConnectionPooling.HikariCPConnection;
import DAOPractice.Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String queryUpdate = "update employees set name = ?,email = ?,department_id=?,salary=? where id = ? ";
        try (Connection connection = HikariCPConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate))
        {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3,employee.getDepartmentId());
            preparedStatement.setDouble(4,employee.getSalary());
            preparedStatement.setInt(5,employee.getId());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
        catch (SQLException e){
            System.out.println("Something went wrong during updating: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        String queryDelete= "delete from employees where id = ?";
        try (Connection connection = HikariCPConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryDelete))
        {
            preparedStatement.setInt(1,id);
            int result = preparedStatement.executeUpdate();
            return result>0;
        }
        catch (SQLException e){
            System.out.println("Something went wrong during deleting: "+e.getMessage());
            return false;
        }
    }

    @Override
    public Employee findById(int id) {
       String queryFindById = "select * from employees where id = ?";
       try (Connection connection = HikariCPConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryFindById))
       {
           preparedStatement.setInt(1,id);
           try(ResultSet resultSet = preparedStatement.executeQuery()){
               if (resultSet.next()){
               return new Employee(
                       resultSet.getInt("id"),
                       resultSet.getString("name"),
                       resultSet.getString("email"),
                       resultSet.getInt("department_id"),
                       resultSet.getDouble("salary"));
               }
           }
       }
       catch (SQLException e){
           System.out.println("Something went wrong while finding employee detail by id");
       }
        return null;
    }

    @Override
    public List<Employee> searchByName(String name) {
        String querySearchByName = "select * from employees where name  like ?";
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = HikariCPConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(querySearchByName)){
            preparedStatement.setString(1,"%"+name+"%");
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    employees.add(new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("department_id"),
                            resultSet.getDouble("salary")));
                }
                return employees;
            }
        }
        catch (SQLException e){
            System.out.println("Something went Wrong: "+e.getMessage());
        }
        return employees;
    }

    @Override
    public List<Employee> findByDep(int departmentId) {
        String queryFindByDep = "Select * from employees where department_id = ?";
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = HikariCPConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryFindByDep))
        {
            preparedStatement.setInt(1,departmentId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    employees.add(new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("department_id"),
                            resultSet.getDouble("salary")));
                }
                return employees;
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong while Finding detail by department: "+ e.getMessage());
        }
        return employees;
    }

    @Override
    public List<Employee> findAllPagination(int pageNum, int pageSize) {
        return List.of();
    }
}
