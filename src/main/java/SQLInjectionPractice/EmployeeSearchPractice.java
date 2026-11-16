package SQLInjectionPractice;
import EmployeeObjectMapping.Employee;
import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeSearchPractice {
    public DatabaseConnection databaseConnection = new DatabaseConnection();
    public static void main(String[] args) {
        EmployeeSearchPractice employeeSearchPractice = new EmployeeSearchPractice();
        employeeSearchPractice.databaseConnection.conn();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name which you want to search: ");
        String name = scanner.nextLine();
        //unsafe (SQL Injection)
//        ArrayList<Employee> employees= employeeSearchPractice.unsafe(name);
//        if(employees.isEmpty()){
//            System.out.println("No employees found");
//        }
//        else{
//            for(Employee employee : employees) {
//            System.out.println("---DETAIL---\n" +
//                    "Id: " + employee.getId() +
//                    "\nName: " + employee.getName() +
//                    "\nEmail: " + employee.getEmail() +
//                    "\nSalary: " + employee.getSalary());
//            }
//        }
        //safe
        ArrayList<Employee> employees1 =employeeSearchPractice.safe(name);
        if(employees1.isEmpty()){
            System.out.println("No employees found");
        }
        else{
            for(Employee employee : employees1) {
                System.out.println("---DETAIL---\n" +
                        "Id: " + employee.getId() +
                        "\nName: " + employee.getName() +
                        "\nEmail: " + employee.getEmail() +
                        "\nSalary: " + employee.getSalary());
            }
        }
    }

    ArrayList<Employee> unsafe(String name) {
        ArrayList<Employee> employees = new ArrayList<>();
        String search = "select * from employee where name ='"+name+"'";
        try (Statement statement = databaseConnection.connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(search)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String names = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    double salary = resultSet.getDouble("salary");
                    Employee employee = new Employee(id, names, email, salary);
                    employees.add(employee);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    ArrayList<Employee> safe(String name){
        ArrayList<Employee>employees= new ArrayList<>();
        String search = "select * from employee where name = ? ";
        try(PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(search)){
            preparedStatement.setString(1,name);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String names = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    double salary = resultSet.getDouble("salary");
                    Employee employee = new Employee(id, names, email, salary);
                    employees.add(employee);
                }
                }
            }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }
}
