package Transaction;

import JDBCPractice.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirtyReadPractice {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection1 = new DatabaseConnection();
        DatabaseConnection databaseConnection = new DatabaseConnection();

        //both object call conn() method from DatabasesConnection to connect db
        databaseConnection1.conn();
        databaseConnection.conn();


        //transaction A
        String query1 = "update bank_account set balance = 5000 where id = 1 ";
        databaseConnection1.connection.setAutoCommit(false);
        PreparedStatement connection1 = databaseConnection1.connection.prepareStatement(query1);
        connection1.executeUpdate();

        //transaction B
        String readQuery = "Select * from bank_account where id = 1";
        databaseConnection.connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        PreparedStatement connection2 = databaseConnection.connection.prepareStatement(readQuery);
        ResultSet resultSet= connection2.executeQuery();
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double balance = resultSet.getDouble("balance");
            System.out.println("ID: "+id+"\nName: "+name+"\nBalance: "+balance);
        }

        //transaction A
        databaseConnection1.connection.rollback();


    }
}
