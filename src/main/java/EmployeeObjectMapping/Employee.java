package EmployeeObjectMapping;


public class Employee {
    private int id;
    private String name;
    private String email;
    private double salary;

    public Employee(int id,String name,String email,double salary){
        this.id=id;
        this.name=name;
        this.email=email;
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

    public double getSalary() {
        return salary;
    }
}
