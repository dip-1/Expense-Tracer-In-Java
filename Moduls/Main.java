import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.SignUp");
        System.out.println("2.Login(Existing Account)");
        System.out.println("Enter Choice:");
        String User_Choice = scanner.nextLine();
        switch (User_Choice) {
            case "1":
                SignUp(scanner);
                break;
            case "2":
                logon(scanner);
                break;
            default:
                throw new AssertionError();
        }
        scanner.close();
    }

    public static void SignUp(Scanner scanner) {

        String url = "jdbc:oracle:thin:@//king:1521/XE";

        String host_username = "system";
        String host_password = "9714399098";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conn = DriverManager.getConnection(url, host_username, host_password);

            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Server Problem Contact Admin");
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
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, Expense_Name);
            pstmt.setString(2, Expense_Email);
            pstmt.setString(3, Expense_Password);
            pstmt.executeUpdate();
            System.out.println("You Have Successfully Sign Up.");
            System.out.println("You Can Login Now.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logon(Scanner scanner) {
        String host_username = "system";
        String host_password = "9714399098";

        System.out.println("Enter UserName:");
        String User_Name = scanner.nextLine();
        System.out.println("Enter Password:");
        String User_password = scanner.nextLine();

        System.out.println("Your user name is: " + User_Name);
        System.out.println("Your password is: " + User_password);

        String url = "jdbc:oracle:thin:@//king:1521/XE";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean isAuthenticated = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, host_username, host_password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT EXPENSE_NAME, EXPENSE_PASSWORD FROM users_expense");

            while (rs.next()) {
                if (rs.getString("EXPENSE_NAME").equals(User_Name)
                        && rs.getString("EXPENSE_PASSWORD").equals(User_password)) {
                    isAuthenticated = true;
                    break;
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Print the login result outside the try block
        System.out.println(
                isAuthenticated ? "✅ Login Successful! Welcome, " + host_username : "❌ Invalid Username or Password.");

    }
}