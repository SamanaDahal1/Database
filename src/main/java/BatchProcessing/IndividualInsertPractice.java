package BatchProcessing;
import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IndividualInsertPractice {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection =new DatabaseConnection();
        databaseConnection.conn();
        String query ="insert into bulk_test (id,name,salary) values (?,?,?)";
        databaseConnection.connection.setAutoCommit(false);
        PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(query);
        long startTime = System.currentTimeMillis();
        for(int i = 0;i<10000;i++){
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,"Salina "+i);
            preparedStatement.setDouble(3,i*5);
            try {
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                System.out.println("Something went wrong here " + i);
                databaseConnection.connection.rollback();
                throw e;
            }
        }
        databaseConnection.connection.commit();
        long endTime = System.currentTimeMillis();
        System.out.println("Inserting Time: "+(endTime-startTime));
        databaseConnection.connection.setAutoCommit(true);
    }
}
