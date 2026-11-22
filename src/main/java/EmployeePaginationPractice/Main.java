
package EmployeePaginationPractice;

import DAOPractice.Model.Employee;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeePaginationPractice employeePaginationPractice = new EmployeePaginationPractice();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter page size: ");
        int pageSize = scanner.nextInt();
        if (pageSize <= 0) {
            System.out.println("Page size must be greater than 0");
            return;
        }
        int totalEmployees = employeePaginationPractice.getTotalEmployees();
        int totalPages = employeePaginationPractice.getTotalPages(pageSize);
        System.out.println("Total Employees: " + totalEmployees);
        System.out.println("Total Pages: " + totalPages);
        for (int i= 1; i<= totalPages; i++) {
            System.out.println("\n--- Page " + i+ " ---");
            List<Employee> employees = employeePaginationPractice.getEmployees(i,pageSize);
            for (Employee employee : employees) {
                System.out.println(
                        employee.getId() + " | " +
                                employee.getName() + " | " +
                                employee.getEmail() + " | " +
                                employee.getSalary()
                );
            }
        }
    }
}
