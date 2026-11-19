package DAOPractice.Main;

import DAOPractice.DAO.EmployeeDaoImpl;
import DAOPractice.Model.Employee;
import DAOPractice.Service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class EmployeeManager {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService(new EmployeeDaoImpl());
        Scanner src = new Scanner(System.in);
        while (true){
            System.out.print("---Employee Management System---\n" +
                "1. Add Employee\n" +
                "2. Update Employee\n" +
                "3. Delete Employee\n" +
                "4. Find Employee By ID\n" +
                "5. Search Employee By Name\n" +
                "6. Find Employees By Department\n" +
                "7. Display Employees (Pagination)\n" +
                "0. Exit\n" +
                "Enter your Choice: ");
            int choice = src.nextInt();
            if(choice==1){
                System.out.print("Enter employee name: ");
                String name = src.next();
                System.out.print("Enter employee email: ");
                String email = src.next();
                System.out.print("Enter employee department Id: ");
                int depId= src.nextInt();
                System.out.print("Enter employee salary: ");
                double salary = src.nextDouble();
                boolean employee= employeeService.addEmployee(new Employee(name,email,depId,salary));
                if(employee){
                    System.out.println("Employee Detail added successfully");
                }
                else {
                    System.out.println("Failed to add employee");
                }
            }
            else if (choice==2) {
                System.out.print("Enter employee id: ");
                int id = src.nextInt();
                Employee exist =employeeService.findById(id);
                if(exist==null){
                    System.out.println("Employee "+ id + " not found");
                }
                else {
                    System.out.println("---Update Detail of Employee---" + id);
                    System.out.print("\nEnter employee name: ");
                    String name = src.next();
                    System.out.print("Enter employee email: ");
                    String email = src.next();
                    System.out.print("Enter employee department Id: ");
                    int depId = src.nextInt();
                    System.out.print("Enter employee salary: ");
                    double salary = src.nextDouble();
                    boolean employee = employeeService.updateEmployee(new Employee(id,name, email, depId, salary));
                    if (employee) {
                        System.out.println("Employee " + id + " Detail Updated Successfully");
                    } else {
                        System.out.println("Failed to update detail of employee " + id);
                    }
                }
            }
            else if (choice==3) {
                System.out.print("Enter Employee id: ");
                int id = src.nextInt();
                Employee employee = employeeService.findById(id);
                if(employee==null){
                    System.out.println("Employee "+ id + " not found");
                }
                else {
                    boolean deleteEmployee = employeeService.deleteEmployee(id);
                    if (deleteEmployee){
                        System.out.println("Employee "+id+" Deleted Successfully");
                    }
                    else {
                        System.out.println("Failed to delete employee "+id);
                    }
                }
            }
            else if (choice==4) {
                System.out.print("Enter Employee Id: ");
                int id = src.nextInt();
                Employee employee = employeeService.findById(id);
                if(employee==null){
                    System.out.println("Employee "+ id + " not found");
                }
                else {
                    System.out.println("Employee Id: "+employee.getId()+
                            "\nName: "+employee.getName()+
                            "\nEmail: "+employee.getEmail()+
                            "\nDepartment Id: "+employee.getDepartmentId()+
                            "\nSalary: "+employee.getSalary());
                }
            }
            else if (choice==5) {
                System.out.print("Enter Employee Name: ");
                String name = src.next();
                List<Employee> employee = employeeService.searchByName(name);
                if(employee.isEmpty()){
                    System.out.println("Employee not found");
                }
                else {
                    for (Employee employee1 : employee){
                        System.out.println("Employee Id: "+employee1.getId()+
                                "\nName: "+employee1.getName()+
                                "\nEmail: "+employee1.getEmail()+
                                "\nDepartment Id: "+employee1.getDepartmentId()+
                                "\nSalary: "+employee1.getSalary());
                    }
                }
            }
            else if (choice==6) {
                System.out.print("Enter Department Id: ");
                int depId = src.nextInt();
                List<Employee> employees = employeeService.findByDep(depId);
                if(employees.isEmpty()){
                    System.out.println("Employee Not Found in Department");
                }
                else {
                    for (Employee employee1 : employees){
                        System.out.println("Employee Id: "+employee1.getId()+
                                "\nName: "+employee1.getName()+
                                "\nEmail: "+employee1.getEmail()+
                                "\nDepartment Id: "+employee1.getDepartmentId()+
                                "\nSalary: "+employee1.getSalary());
                    }
                }
            }
            else if (choice==7) {
                System.out.print("Enter Page Number: ");
                int pageNum = src.nextInt();
                System.out.print("Enter Page Size: ");
                int pageSize = src.nextInt();
                List<Employee> employee = employeeService.findAllPagination(pageNum,pageSize);
                if(employee.isEmpty()){
                    System.out.println("No Employees Found");
                }
                else {
                    System.out.println("Page: "+pageNum);
                    for(Employee employees : employee){
                        System.out.println("Employee Id: "+employees.getId()+
                                "\nName: "+employees.getName()+
                                "\nEmail: "+employees.getEmail()+
                                "\nDepartment Id: "+employees.getDepartmentId()+
                                "\nSalary: "+employees.getSalary());
                    }
                }
            }
            else if (choice==0) {
                System.out.println("Thanks For Visiting Employee Management System");
                System.exit(0);
            }
            else {
                System.out.println("Please enter valid number (0-7)");
            }
        }
    }
}

