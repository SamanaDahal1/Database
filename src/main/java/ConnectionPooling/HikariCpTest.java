package ConnectionPooling;

public class HikariCpTest {
    public static void main(String[] args) {
        HikariCPConnection.test();
        HikariCPConnection.close();
    }
}
