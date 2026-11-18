package ConnectionPooling;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCpTest {
    public static void main(String[] args) throws SQLException {
        HikariCPConnection.test();
        Connection connection1=null;
        Connection connection2=null;
        Connection connection3=null;
        Connection connection4=null;
        try{
        connection1 = HikariCPConnection.getConnection();
        connection2 = HikariCPConnection.getConnection();
        connection3 = HikariCPConnection.getConnection();
        connection4 = HikariCPConnection.getConnection();
            System.out.println("1: "+connection1);
            System.out.println("2: "+connection2);
            System.out.println("3: "+connection3);
            System.out.println("4: "+connection4);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if(connection1!=null){
                connection1.close();
            }
            if(connection2!=null){
                connection2.close();
            }
            if(connection3!=null){
                connection3.close();
            }
            if(connection4!=null){
                connection4.close();
            }

            HikariCPConnection.close();

        }



    }
}
