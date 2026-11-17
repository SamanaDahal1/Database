package BatchProcessing;
import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementBatchPractice {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.conn();
        String query ="insert into bank_account (id,name,balance) values (?,?,?)";
        databaseConnection.connection.setAutoCommit(false);
        PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(query);
        preparedStatement.setInt(1,12);
        preparedStatement.setString(2,"Bella");
        preparedStatement.setDouble(3,90000);
        preparedStatement.addBatch();
        preparedStatement.setInt(1,13);
        preparedStatement.setString(2,"Alix");
        preparedStatement.setDouble(3,95000);
        preparedStatement.addBatch();
        preparedStatement.setInt(1,14);
        preparedStatement.setString(2,"Diana");
        preparedStatement.setDouble(3,85000);
        preparedStatement.addBatch();
        preparedStatement.setInt(1,15);
        preparedStatement.setString(2,"Ethen");
        preparedStatement.setDouble(3,90000);
        preparedStatement.addBatch();
        int[] result =preparedStatement.executeBatch();
        for (int results: result){
            System.out.println("Row Affected: "+ results);
        }
        databaseConnection.connection.commit();
        databaseConnection.connection.setAutoCommit(true);
    }
}
