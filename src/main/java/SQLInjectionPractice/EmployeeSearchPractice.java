package SQLInjectionPractice;
import EmployeeObjectMapping.Employee;
import JDBCPractice.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeSearchPractice {
    public DatabaseConnection databaseConnection = new DatabaseConnection();
    public static void main(String[] args) {
        EmployeeSearchPractice employeeSearchPractice = new EmployeeSearchPractice();
        employeeSearchPractice.databaseConnection.conn();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name which you want to search: ");
        String name = scanner.nextLine();
        Employee employee = employeeSearchPractice.unsafe(name);
        System.out.println("---DETAIL---\n" +
                "Id: " + employee.getId() +
                "\nName: "+employee.getName()+
                "\nEmail: "+employee.getEmail()+
                "\nSalary: "+employee.getSalary());

    }

    Employee unsafe(String name) {
        String search = "select * from employee where name ='"+name+"'";
        try (Statement statement = databaseConnection.connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(search)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String names = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    double salary = resultSet.getDouble("salary");
                    Employee employee = new Employee(id, names, email, salary);
                    return employee;

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
