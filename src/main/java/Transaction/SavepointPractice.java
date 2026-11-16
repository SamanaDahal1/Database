package Transaction;
import JDBCPractice.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class SavepointPractice {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public static void main(String[] args) throws SQLException {
        SavepointPractice savepointPractice = new SavepointPractice();
        savepointPractice.databaseConnection.conn();
        Connection connection = savepointPractice.databaseConnection.connection;
        //1st update
        String updateOne = "update bank_account set balance = balance-1000 where id =1";
        savepointPractice.databaseConnection.connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(updateOne);
        preparedStatement.executeUpdate();
        Savepoint savepoint = connection.setSavepoint("BeforeSecondUpadate");
        //2nd update
        String updateTwo= "update bank_account set balance = balance-500 where id =2";
        PreparedStatement preparedStatement1 = connection.prepareStatement(updateTwo);
        preparedStatement1.executeUpdate();
        connection.rollback(savepoint);
        //commit
        connection.commit();
        //restore auto commit
        connection.setAutoCommit(true);






    }
}
