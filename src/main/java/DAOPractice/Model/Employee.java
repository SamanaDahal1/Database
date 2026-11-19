package DAOPractice.Model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Employee {
    private int id;
    private String name;
    private String email;
    private int departmentId;
    private double salary;


   public Employee(int id ,String name,String email,int departmentId,double salary){
        this.id=id;
        this.name=name;
        this.email=email;
        this.departmentId=departmentId;
        this.salary=salary;
    }
    public Employee(String name , String email,int departmentId,double salary){
       this.name = name;
       this.email= email;
       this.departmentId=departmentId;
       this.salary=salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getSalary() {
        return salary;
    }
}
