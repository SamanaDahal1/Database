package SQLInjectionPractice;
import EmployeeObjectMapping.Employee;
import JDBCPractice.DatabaseConnection;
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
        ArrayList<Employee> employees= employeeSearchPractice.unsafe(name);
        if(employees.isEmpty()){
            System.out.println("No employees found");
        }
        else{
            for(Employee employee : employees) {
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
}
