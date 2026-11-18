package DAOPractice.Service;

import DAOPractice.DAO.EmployeeDao;
import DAOPractice.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
   private final EmployeeDao employeeDao;
    public EmployeeService(EmployeeDao employeeDao){
       this.employeeDao=employeeDao;
   }

    public boolean addEmployee(Employee employee){
        if(employee.getName()!=null&&!employee.getName().trim().isEmpty()
                && employee.getEmail()!=null&&!employee.getEmail().trim().isEmpty()
                &&employee.getSalary()>0)
        {
            return employeeDao.addEmployee(employee);
        }

        return false;
    }
    public boolean updateEmployee(Employee employee){
        if(employee.getId()>0
                &&employee.getName()!=null&&!employee.getName().trim().isEmpty()
                && employee.getEmail()!=null&&!employee.getEmail().trim().isEmpty()
                &&employee.getSalary()>0)
        {
            return employeeDao.updateEmployee(employee);
        }
        return false;
    }
    public boolean deleteEmployee(int id){
        if(id>0){
            return employeeDao.deleteEmployee(id);
        }
        return false;
    }
    public Employee findById(int id){
        if(id>0){
            return employeeDao.findById(id);
        }
        return null;
    }
    public List<Employee> searchByName(String name){
        if(name!=null&&!name.trim().isEmpty()){
            return employeeDao.searchByName(name);
        }
        return new ArrayList<>();
    }
    public List<Employee> findByDep(int dep){
        if(dep>0){
            return employeeDao.findByDep(dep);
        }
        return new ArrayList<>();
    }
    public List<Employee> findAllPagination(int pageNum ,int pageSize){
        if(pageNum>0 && pageSize>0){
            return employeeDao.findAllPagination(pageNum,pageSize);
        }
        return new ArrayList<>();
    }

}
