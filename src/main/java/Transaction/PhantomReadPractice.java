package Transaction;
import JDBCPractice.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhantomReadPractice {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection1 = new DatabaseConnection();
        DatabaseConnection databaseConnection2 = new DatabaseConnection();

        databaseConnection1.conn();
        databaseConnection2.conn();
        //transactionA
        String read1 = "Select name from bank_account";
        //Here for demonstration we are using read committed ,to prevent phantom use repeatable read but as default readable read is used so no need to set isolation
        databaseConnection1.connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        databaseConnection1.connection.setAutoCommit(false);
        PreparedStatement preparedStatement = databaseConnection1.connection.prepareStatement(read1);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("---All Account Holders---");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            System.out.println(name);
        }

        //transactionB
        String add = "insert into bank_account (id,name,balance) values (6,'Gimmy',80000)";
        databaseConnection2.connection.setAutoCommit(false);
        PreparedStatement preparedStatement1 = databaseConnection2.connection.prepareStatement(add);
        preparedStatement1.executeUpdate();
        databaseConnection2.connection.commit();

        //transactionA
        String read2 = "Select name from bank_account";
        PreparedStatement preparedStatement2 = databaseConnection1.connection.prepareStatement(read2);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        System.out.println("---All Account Holders---");
        while(resultSet2.next()){
            String name = resultSet2.getString("name");
            System.out.println(name);
        }
        databaseConnection1.connection.commit();
        databaseConnection1.connection.setAutoCommit(true);
        databaseConnection2.connection.setAutoCommit(true);


    }
}
