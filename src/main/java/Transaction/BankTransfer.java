package Transaction;
import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BankTransfer {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public static void main(String[] args) throws SQLException {
        BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.databaseConnection.conn();
        Scanner src = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        int fromId = src.nextInt();
        System.out.print("Enter account number of receiver: ");
        int toId = src.nextInt();
        System.out.print("Enter amount: ");
        double amount= src.nextDouble();
        try {
            bankTransfer.transfer(fromId,toId,amount);
        }
        catch (SQLException e){
            System.out.println("Transfer Failed: " +e.getMessage());
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid Amount: "+e.getLocalizedMessage());
        }
    }
    public void transfer(int fromId,int toId, double amount) throws SQLException {
     String deduct = "update bank_account set balance = balance - ? where id = ? and balance>=?";
     String add = "update bank_account set balance= balance + ? where id= ?";
     if(amount<=0){
         throw new IllegalArgumentException("Amount must be greater than 0");
     }
     databaseConnection.connection.setAutoCommit(false);
      try( PreparedStatement preparedDeduct = databaseConnection.connection.prepareStatement(deduct);
        PreparedStatement preparedAdd = databaseConnection.connection.prepareStatement(add)){
          preparedDeduct.setDouble(1,amount);
          preparedDeduct.setInt(2,fromId);
          preparedDeduct.setDouble(3,amount);
          preparedAdd.setDouble(1, amount);
          preparedAdd.setInt(2,toId);
          int deducted= preparedDeduct.executeUpdate();
          if(deducted!=1 ){
              throw new SQLException("Invalid sender account or insufficient balance");
          }
          int added= preparedAdd.executeUpdate();
          if(added!=1 ){
              throw new SQLException("Invalid receiver account");
          }
          databaseConnection.connection.commit();
          System.out.println("Transfer Successfully");
        }
      catch (SQLException e){
          databaseConnection.connection.rollback();
          throw e;
      }
      finally {
          databaseConnection.connection.setAutoCommit(true);
      }

    }
}
