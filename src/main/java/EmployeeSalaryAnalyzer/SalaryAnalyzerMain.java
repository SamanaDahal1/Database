package EmployeeSalaryAnalyzer;
import DAOPractice.DAO.EmployeeDao;
import DAOPractice.DAO.EmployeeDaoImpl;
import DAOPractice.Model.Employee;
import DAOPractice.Service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SalaryAnalyzerMain {
    public static void main(String[] args) {
        EmployeeService employeeService= new EmployeeService(new EmployeeDaoImpl());
        EmployeeSalaryAnalyzer salaryAnalyzer = new EmployeeSalaryAnalyzer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Department ID: ");
        int depId = scanner.nextInt();
        List<Employee> dep = employeeService.findByDep(depId);
        if(dep.isEmpty()){
            System.out.println("Cant find");
            return;
        }
        while (true){
            System.out.println("\n1.Display All Employees\n" +
                "2. Calculate Average Salary\n" +
                "3. Find Highest Paid Employee\n" +
                "4. Sort Employees by Salary (Highest to Lowest)\n" +
                "5. Display Complete Salary Analysis\n" +
                "0. Exit\n");
            System.out.println("Choose 0-5:");
            int choose = scanner.nextInt();
            if(choose==1){
                salaryAnalyzer.displayEmployees(dep);
            }
            else if (choose==2){
               double average = salaryAnalyzer.calculateAverageSalary(dep);
               System.out.println("Average Salary of Department: "+average);
            }
            else if (choose==3) {
               Optional<Employee> highestPaid= salaryAnalyzer.findHighestPaidEmployee(dep);
               highestPaid.ifPresentOrElse(System.out::println,()->System.out.println("Not found"));
            }
            else if (choose==4) {
                List<Employee> sorting=salaryAnalyzer.sortEmployeesBYSalary(dep);
                salaryAnalyzer.displayEmployees(sorting);
            }
            else if (choose==5) {
                System.out.println("--- Display Complete Salary Analysis---");
                salaryAnalyzer.displayEmployees(dep);
                double average = salaryAnalyzer.calculateAverageSalary(dep);
                System.out.println("Average Salary of Department: "+average);
                Optional<Employee> highestPaid= salaryAnalyzer.findHighestPaidEmployee(dep);
                highestPaid.ifPresentOrElse(System.out::println,()->System.out.println("Not found"));
                List<Employee> sorting=salaryAnalyzer.sortEmployeesBYSalary(dep);
                salaryAnalyzer.displayEmployees(sorting);
            }
            else if (choose==0) {
                System.out.println("Thanks for Visiting");
                System.exit(0);
            }
            else {
                System.out.println("Please Enter Valid Number (0-5)");
            }
        }
    }
}
