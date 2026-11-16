package JDBCPractice;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;
public class DatabaseConnection {
   public Connection connection;
   public void conn() {
       Properties properties = new Properties();
       try(InputStream inputStream = getClass()
               .getClassLoader()
               .getResourceAsStream("db.properties");) {
           if(inputStream==null){
               throw  new IOException("File not found");
           }
           properties.load(inputStream);
           if (properties.isEmpty()) {
               throw  new IOException("File Empty");
           }
           String url = properties.getProperty("db.url");
           String username = properties.getProperty("db.username");
           String password = properties.getProperty("db.password");
           try {

               connection = DriverManager.getConnection(url, username, password);
               System.out.println("Database Connected Successfully");
           }

           catch (SQLException e) {
               e.printStackTrace();
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }


   }

}
