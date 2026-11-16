package SQLExceptionPractice;
import JDBCPractice.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLExceptionPractice  {
    static DatabaseConnection databaseConnection = new DatabaseConnection();
    public static void main(String[] args){
        SQLExceptionPractice sqlExceptionPractice = new SQLExceptionPractice();
        databaseConnection.conn();
        String select = "select * from unknownTable";
        try (PreparedStatement preparedStatement = databaseConnection.connection.prepareStatement(select) ){
            preparedStatement.executeQuery();
        }
        catch (SQLException e){
            throw new DatabaseException("Database failed",e);
//            System.out.println("Error Message: "+e.getMessage() + "\nSQL State(category of SQL error): "+ e.getSQLState() +"\nError Code: "+e.getErrorCode());
        }
    }
}
