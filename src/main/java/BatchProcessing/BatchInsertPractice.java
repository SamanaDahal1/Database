package BatchProcessing;
import JDBCPractice.DatabaseConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class BatchInsertPractice {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.conn();
        String person1 = "insert into bank_account(id,name,balance) values (7,'Minilie',96000)";
        String person2 = "insert into bank_account(id,name,balance) values (8,'Raye',180000)";
        String person3 = "insert into bank_account(id,name,balance) values (9,'Maddie',100000)";
        String person4 = "insert into bank_account(id,name,balance) values (10,'Cassie',65500)";
        String person5 = "insert into bank_account(id,name,balance) values (11,'Brak',70000)";
        databaseConnection.connection.setAutoCommit(false);
        Statement statement = databaseConnection.connection.createStatement();
        statement.addBatch(person1);
        statement.addBatch(person2);
        statement.addBatch(person3);
        statement.addBatch(person4);
        statement.addBatch(person5);
        int[] results = statement.executeBatch();
        for (int result : results){
            System.out.println("Row Affected: "+ result);
        }
        databaseConnection.connection.commit();
        databaseConnection.connection.setAutoCommit(true);

    }
}
