package Transaction;
import JDBCPractice.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class SerializablePractice {
    public static void main(String[] args) throws SQLException, InterruptedException {
        DatabaseConnection databaseConnection1 = new DatabaseConnection();
        DatabaseConnection databaseConnection2 = new DatabaseConnection();
        CountDownLatch firstReadDone = new CountDownLatch(1);
        CountDownLatch allowACommit = new CountDownLatch(1);
        CountDownLatch updateAttempting = new CountDownLatch(1);

        databaseConnection1.conn();
        databaseConnection2.conn();

        //TransacationB
        Thread transactionA = new Thread(()->{
            String read1 = "select * from bank_account where id=1";
            try {
                databaseConnection1.connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                databaseConnection1.connection.setAutoCommit(false);PreparedStatement connection1 = databaseConnection1.connection.prepareStatement(read1);
                ResultSet resultSet = connection1.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double balance = resultSet.getDouble("balance");
                    System.out.println("Id: " + id + "\nName: " + name + "\nBalance: " + balance);

                }
                firstReadDone.countDown();
                allowACommit.await();
                //TransactionA
                String read2= "select * from bank_account where id=1";
                PreparedStatement connection3 =databaseConnection1.connection.prepareStatement(read2);
                ResultSet resultSet1 = connection3.executeQuery();
                if(resultSet1.next()){
                    int id1 = resultSet1.getInt("id");
                    String name1 = resultSet1.getString("name");
                    double balance1 = resultSet1.getDouble("balance");
                    System.out.println("Id: "+id1+"\nName: "+name1+"\nBalance: "+balance1);
                }


                databaseConnection1.connection.commit();
                databaseConnection1.connection.setAutoCommit(true);

            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //TransactionB
        Thread transactionB = new Thread(()-> {
            String update = "update bank_account set balance= 60000 where id = 1";
            try {
                firstReadDone.await();
                databaseConnection2.connection.setAutoCommit(false);
                PreparedStatement connection2 = databaseConnection2.connection.prepareStatement(update);
                System.out.println("Transaction B attempting update");
                updateAttempting.countDown();
                connection2.executeUpdate();
                databaseConnection2.connection.commit();
                databaseConnection2.connection.setAutoCommit(true);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }


        });

        transactionA.start();
        transactionB.start();
        updateAttempting.await();
        Thread.sleep(2000);
        allowACommit.countDown();
        transactionA.join();
        transactionB.join();

    }

    }

