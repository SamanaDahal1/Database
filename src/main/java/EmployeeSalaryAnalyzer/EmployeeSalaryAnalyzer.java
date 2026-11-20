package EmployeeSalaryAnalyzer;

import DAOPractice.Model.Employee;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeeSalaryAnalyzer {
    void displayEmployees (List<Employee> employees){
        if(employees==null||employees.isEmpty()){
            System.out.println("List is Empty");
            return;
        }
        for(Employee employee: employees){
            System.out.println("Id: "+employee.getId()+
                    "\nName: "+employee.getName()+
                    "\nEmail: "+employee.getEmail()+
                    "\nDepartment Id: "+employee.getDepartmentId()+
                    "\nSalary: "+employee.getSalary()+"\n");
        }
    }
    double calculateAverageSalary(List<Employee> employees){
        if(employees==null||employees.isEmpty()){
            return 0.0;
        }
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
    Optional<Employee> findHighestPaidEmployee(List<Employee>employees){
        if(employees==null||employees.isEmpty()){
            return Optional.empty();
        }
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }
    List<Employee> sortEmployeesBYSalary(List<Employee> employees){
        if(employees==null||employees.isEmpty()){
            return employees;
        }
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .toList();

    }
}
