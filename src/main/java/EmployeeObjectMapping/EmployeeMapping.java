package EmployeeObjectMapping;

import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class EmployeeMapping {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public static void main(String[] args) {
        EmployeeMapping employeeMapping = new EmployeeMapping();
        //connecting
        employeeMapping.databaseConnection.conn();
        //creating table
        employeeMapping.create();
        //inserting inside table
        employeeMapping.insert("Gimmy", "gimmy@gmail.com", 30000);
        employeeMapping.insert("Adam", "adam@gmail.com", 45000);
        employeeMapping.insert("Lucia", "lucia@gmail.com", 80000);
        //finding detail by id
        Employee employee = employeeMapping.findById(2);
        if (employee != null) {
            System.out.println("------Finding by ID'------");
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Salary: " + employee.getSalary());
        } else {
            System.out.println("Employee not found!");
        }
        //
        List<Employee> employees = employeeMapping.findAll();
        System.out.println("----All Employee Detail----");
        for(Employee employeeOne : employees){
                System.out.println("-------------------------------------");
                System.out.println("ID: " + employeeOne.getId());
                System.out.println("Name: " + employeeOne.getName());
                System.out.println("Email: " + employeeOne.getEmail());
                System.out.println("Salary: " + employeeOne.getSalary());
                System.out.println("-------------------------------------");

        }
    }

    void create() {
        String createQuery = "create table if not exists employee( id int primary key auto_increment,name varchar(30),email varchar(50),salary double)";
        try (
                PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(createQuery)) {
            preparedStatement.executeUpdate();
            System.out.println("Successfully Created Table ");

        } catch (SQLException e) {
            System.out.println("(Creating Table)Something went wrong");
        }
    }

    void insert(String name, String email, double salary) {
        String insertQuery = "insert into employee (name,email,salary) value(?,?,?)";
        try (PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setDouble(3, salary);
            preparedStatement.executeUpdate();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys();){
                    if(resultSet.next()){
                        int generateId = resultSet.getInt(1);
                        System.out.println("Generated Employee ID: " + generateId);
                    }
                    System.out.println("Successfully Inserted");

                }
        }
        catch (SQLException e) {
            System.out.println("(Inserting Table)Something went wrong");
        }
    }



    Employee findById(int id) {
        String findId = "Select * from employee where id =?";
        try (PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(findId)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int ids = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(ids, name, email, salary);
                return employee;
            }
        } catch (SQLException e) {
            System.out.println("(Finding Detail by id) something went wrong");
        }

        return null;
    }

    List<Employee> findAll() {
        String findId = "Select * from employee";
        try (PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(findId)) {
            List<Employee> employeeList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int ids = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(ids, name, email, salary);
                employeeList.add(employee);

            }
            return employeeList;
        }
        catch (SQLException e){
            System.out.println("(Listing all Employee) Something went wrong");
        }
        return null;
    }
}
