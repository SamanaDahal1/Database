package DAOPractice.DAO;

import DAOPractice.Model.Employee;
import java.util.List;

 public interface EmployeeDao {
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int id);
    Employee findById(int id);
    List<Employee> searchByName(String name);
    List<Employee> findByDep(int departmentId);
    List<Employee> findAllPagination(int pageNum ,int pageSize);

}
