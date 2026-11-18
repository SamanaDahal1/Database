package ConnectionPooling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPConnection {
    private static final HikariDataSource dataSource ;
    private static final DataSource data;
    static {
        Properties properties = new Properties();
        try(InputStream inputStream = HikariCPConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (inputStream==null){
                throw new IOException("File not found");
            }
            properties.load(inputStream);
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
            hikariConfig.setUsername(properties.getProperty("db.username"));
            hikariConfig.setPassword(properties.getProperty("db.password"));
            hikariConfig.setMaximumPoolSize(2);
            hikariConfig.setConnectionTimeout(3000);
            hikariConfig.setPoolName("HikariPool");
            dataSource=new HikariDataSource(hikariConfig);
            data=dataSource;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static Connection getDataSourceConn() throws SQLException{
        return data.getConnection();
    }
    public static void test(){
        try (Connection connection = getConnection()){
            System.out.println("Connected Successfully");
            System.out.println("Connection valid: "+ connection.isValid(2));
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }
    public static void close(){
        if(dataSource!=null){
            dataSource.close();
        }
    }
}
