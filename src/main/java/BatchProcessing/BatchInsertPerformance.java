package BatchProcessing;
import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
//Total Time: 2194
public class BatchInsertPerformance {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.conn();

        String query = "insert into bulk_test (id,name,salary) values (?,?,?)";
        databaseConnection.connection.setAutoCommit(false);
        PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(query);
        long startTime = System.currentTimeMillis();
        for(int i= 1;i<=10000; i++){
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,"Sodina"+i);
            preparedStatement.setDouble(3,i*5);
            preparedStatement.addBatch();
        }
            try {
                int[] result =preparedStatement.executeBatch();
                for (int results: result){
                    System.out.println("Row Affected: "+ results);
                }
                databaseConnection.connection.commit();
            }
            catch (SQLException e){
                System.out.println("Something went Wrong");
                databaseConnection.connection.rollback();
                throw e;
            }
        long endTime =System.currentTimeMillis();
        System.out.println("Total Time: "+(endTime-startTime));
        databaseConnection.connection.setAutoCommit(true);
    }
}
