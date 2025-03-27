import java.sql.*;

public class OracleDBConnection {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@//king:1521/XE";

        String host_username = "system"; 
        String host_password = "9714399098";
        
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conn = DriverManager.getConnection(url, host_username, host_password);
            System.out.println("Connected to Oracle Database!");

            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
