package ConnectionPooling;


import java.sql.Connection;
import java.sql.SQLException;


public class DataSourcePractice {
    public static void main(String[] args) {
       try(Connection connection =HikariCPConnection.getDataSourceConn()){
           System.out.println("Connected using Datasource");
           System.out.println("Connection Validation: "+connection.isValid(2));

       }
       catch (SQLException e){
           System.out.println(e.getMessage());
       }
       finally {
           HikariCPConnection.close();
       }

    }

}
