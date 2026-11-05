package JDBCPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CURD {
     DatabaseConnection databaseConnection;
    public static void main(String[] args) {
        CURD curd = new CURD();
        curd.databaseConnection = new DatabaseConnection();
        curd.databaseConnection.conn();

        curd.create();
        curd.insert(1,"Happy",22);
        curd.insert(2,"Georgie",23);
        System.out.println("\nAll Students: ");
        curd.read();
        curd.update(20,1);
        System.out.println("\nAll Students after update: ");
        curd.read();
        curd.delete(1);
        System.out.println("\nAll Students after deleting: ");
        curd.read();


    }
   void create() {

        try{
            String query = "create table if not exists students(rollnum int primary key, name varchar(50),age int )";
            PreparedStatement statement = databaseConnection.connection.prepareStatement(query);
            statement.executeUpdate();
            System.out.println("Student table created successfully");

        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    void insert(int rollnum ,String name,int age ){
       try {
           String insert = "insert into students (rollnum,name,age)values (?,?,?)";
           PreparedStatement statement = databaseConnection.connection.prepareStatement(insert);
           statement.setInt(1,rollnum);
           statement.setString(2,name);
           statement.setInt(3 ,age);
           statement.executeUpdate();
           System.out.println("Student detail inserted successfully");
       }
       catch (SQLException e){
           e.printStackTrace();
       }
    }
    void read(){
       try {
           String read = "select * from students";
           PreparedStatement statement = databaseConnection.connection.prepareStatement(read);
           ResultSet s = statement.executeQuery();
           while(s.next()){
               System.out.println(s.getInt("rollnum")+" "+s.getString("name")+" "+s.getInt("age") );
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
    void update(int age,int rollnum){
        try {
            String read = "update students set age=? where rollnum=?";
            PreparedStatement statement = databaseConnection.connection.prepareStatement(read);

            statement.setInt(1,age);
            statement.setInt(2,rollnum);
            statement.executeUpdate();
            System.out.println("Successfully updated");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    void delete(int rollnum){
        try {
            String read = "delete from students where rollnum=?";
            PreparedStatement statement = databaseConnection.connection.prepareStatement(read);
            statement.setInt(1,rollnum);
            statement.executeUpdate();
            System.out.println("\nSuccessfully deleted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



}
