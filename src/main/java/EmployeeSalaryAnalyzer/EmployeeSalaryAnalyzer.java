package EmployeeSalaryAnalyzer;

import DAOPractice.Model.Employee

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeSalaryAnalyzer {
    void displayEmployees (List<Employee> employees){
        for(Employee employee: employees){
            System.out.println("Id: "+employee.getId()+
                    "\nName: "+employee.getName()+
                    "\nEmail: "+employee.getEmail()+
                    "\nDepartment Id: "+employee.getDepartmentId()+
                    "\nSalary: "+employee.getSalary()+"\n");
        }
    }
    double calculateAverageSalary(List<Employee> employees){

    }
    Optional<Employee> findHighestPaidEmployee(List<Employee>employees){

    }
    List<Employee> sortEmployeesBYSalary(List<Employee> employees){

    }
}
