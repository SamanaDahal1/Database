package Pagination;

import ConnectionPooling.HikariCPConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Pagination {
    public static void main(String[] args) {
        Pagination pagination = new Pagination();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Page Number: ");
        int pageNum = scanner.nextInt();
        System.out.print("Enter Page Size: ");
        int pageSize = scanner.nextInt();
        if(pageNum > 0 && pageSize > 0){
        pagination.page(pageNum,pageSize);
        }
        else{
            System.out.println("Page Number and Page Size must be greater than 0");
        }
    }
    void page(int pageNum ,int pageSize){
        int offset = (pageNum-1)*pageSize;
        String query = "select * from bulk_test limit ? offset ?";
        try(Connection connection =HikariCPConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2,offset);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                System.out.println("---Name---");
                while(resultSet.next()){
                    String name = resultSet.getString("name");
                    System.out.println(name);
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
