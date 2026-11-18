package DAOPractice.Main;

import DAOPractice.DAO.EmployeeDaoImpl;
import DAOPractice.Model.Employee;

public class test {
    public static void main(String[] args) {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.addEmployee(new Employee(1,"Gimmy","gimmy@example.com",1,35000));
    }
}
