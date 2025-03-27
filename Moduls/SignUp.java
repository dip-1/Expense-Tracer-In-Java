import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;



public class SignUp
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        String url = "jdbc:oracle:thin:@//king:1521/XE";

        String host_username = "system"; 
        String host_password = "9714399098";
        
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conn = DriverManager.getConnection(url, host_username, host_password);

            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Server Down Contact Admin");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed! Contact Admin");
            e.printStackTrace();
        }
        System.out.println("Enter Details To Sign Up");
        System.out.println("Enter Your NAME:");
        String Expense_Name = scanner.nextLine();
        System.out.println("Enter Your EMAIL ADDRESS:");
        String Expense_Email = scanner.nextLine();
        System.out.println("Enter Your Password");
        String Expense_Password = scanner.nextLine();


        String insertSQL = "INSERT INTO users_expense(EXPENSE_ID,EXPENSE_NAME, EXPENSE_EMAIL,EXPENSE_PASSWORD) VALUES (users_expense_seq.NEXTVAL, ?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, host_username, host_password);
        PreparedStatement pstmt = conn.prepareStatement(insertSQL))
        {
            pstmt.setString(1, Expense_Name);
            pstmt.setString(2, Expense_Email);
            pstmt.setString(3,Expense_Password);
            pstmt.executeUpdate();
            System.out.println("You Have Successfully Sign Up.");
            System.out.println("You Can Login Now.");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}