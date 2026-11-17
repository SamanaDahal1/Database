package Transaction;
import JDBCPractice.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NonRepeatableReadPractice {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection1 = new DatabaseConnection();
        DatabaseConnection databaseConnection2 = new DatabaseConnection();

        databaseConnection1.conn();
        databaseConnection2.conn();

        //TransactionA
        String read1= "select * from bank_account where id=1";
        databaseConnection1.connection.setAutoCommit(false);
        databaseConnection1.connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        PreparedStatement connection1 = databaseConnection1.connection.prepareStatement(read1);
        ResultSet resultSet = connection1.executeQuery();
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double balance = resultSet.getDouble("balance");
            System.out.println("Id: "+id+"\nName: "+name+"\nBalance: "+balance);
        }

        //TransactionB
        String update = "update bank_account set balance= 35000 where id = 1";
        databaseConnection2.connection.setAutoCommit(false);
        PreparedStatement connection2 = databaseConnection2.connection.prepareStatement(update);
        connection2.executeUpdate();
        databaseConnection2.connection.commit();
        databaseConnection2.connection.setAutoCommit(true);

        //TransactionA
        String read2= "select * from bank_account where id=1";
        PreparedStatement connection3 =databaseConnection1.connection.prepareStatement(read2);
        ResultSet resultSet1 = connection3.executeQuery();
        if(resultSet1.next()){
            int id = resultSet1.getInt("id");
            String name = resultSet1.getString("name");
            double balance = resultSet1.getDouble("balance");
            System.out.println("Id: "+id+"\nName: "+name+"\nBalance: "+balance);
        }
        databaseConnection1.connection.commit();
        databaseConnection1.connection.setAutoCommit(true);
    }
}
