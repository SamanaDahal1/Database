package DAOPractice.Model;
 public class Employee {
    private int id;
    private String name;
    private String email;
    private int departmentId;
    private double salary;


   public Employee(String name,String email,int departmentId,double salary){

        this.name=name;
        this.email=email;
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
