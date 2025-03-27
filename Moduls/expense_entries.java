// import java.sql.*;
// import java.util.Scanner;

// class expense_entries {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
        
//         // Call the logon method and get the user expense ID
//         int userExpenseId = logon(scanner);
        
//         // If login was successful (userExpenseId is not -1), call insertExpense
//         if (userExpenseId != -1) {
//             System.out.println("Proceeding to insert expense...");
//             insertExpense(scanner, userExpenseId);
//         } else {
//             System.out.println("Login failed. Exiting program.");
//         }
        
//         scanner.close();
//     }

//     public static int logon(Scanner scanner) {
//         String host_username = "system";
//         String host_password = "9714399098";

//         System.out.println("Enter UserName:");
//         String userName = scanner.nextLine();
//         System.out.println("Enter Password:");
//         String userPassword = scanner.nextLine();

//         String url = "jdbc:oracle:thin:@//king:1521/XE";

//         Connection conn = null;
//         PreparedStatement pstmt = null;
//         ResultSet rs = null;
//         int expenseId = -1; // Default to -1 to indicate failure

//         try {
//             Class.forName("oracle.jdbc.driver.OracleDriver");

//             conn = DriverManager.getConnection(url, host_username, host_password);

//             // Prepare statement to fetch EXPENSE_ID
//             String idQuery = "SELECT EXPENSE_ID FROM users_expense WHERE EXPENSE_NAME = ? AND EXPENSE_PASSWORD = ?";
//             pstmt = conn.prepareStatement(idQuery);
//             pstmt.setString(1, userName);
//             pstmt.setString(2, userPassword);
//             rs = pstmt.executeQuery();

//             if (rs.next()) {
//                 expenseId = rs.getInt("EXPENSE_ID");
//                 System.out.println("Login successful Welcome To Expense Tracer : "+userName);
//                 System.out.println("Your Id is:"+expenseId);
                   
//             } else {
//                 System.out.println("Invalid Username or Password.");
//             }

//         } catch (ClassNotFoundException e) {
//             System.out.println("Oracle JDBC Driver not found. Include it in your library path.");
//             e.printStackTrace();
//         } catch (SQLException e) {
//             System.out.println("Database connection failed. Contact Admin");
//             e.printStackTrace();
//         } finally {
//             try {
//                 if (rs != null) rs.close();
//                 if (pstmt != null) pstmt.close();
//                 if (conn != null) conn.close();
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         }
        
//         return expenseId; // Return the expense ID (or -1 if login failed)
        
//     }

    // public static void insertExpense(Scanner scanner, int userExpenseId)
    // {
    //     String host_username = "system";
    //     String host_password = "9714399098";
    //     String url = "jdbc:oracle:thin:@//king:1521/XE";

    //     try (Connection conn = DriverManager.getConnection(url, host_username, host_password)) {
    //         System.out.println("Enter Expense Date (YYYY-MM-DD):");
    //         String expenseDate = scanner.nextLine();
    //         System.out.println("Enter Expense Amount:");
    //         double expenseAmount = scanner.nextDouble();
    //         scanner.nextLine(); // Consume newline
    //         System.out.println("Enter Expense Category:");
    //         String expenseCategory = scanner.nextLine();
    //         System.out.println("Enter Expense Description:");
    //         String expenseDescription = scanner.nextLine();
    //         System.out.println("Enter Expense Payment Method:");
    //         String expensePaymentMethod = scanner.nextLine();

    //         String insertQuery = "INSERT INTO expenses_expense (EXPENSE_ID, USER_EXPENSE_ID, EXPENSE_DATE, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_PAYMENT_METHOD) VALUES (?, ?, ?, ?, ?, ?, ?)";
    //         try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
    //             pstmt.setInt(1, userExpenseId); // Use the fetched EXPENSE_ID
    //             pstmt.setInt(2, userExpenseId); // Assuming USER_EXPENSE_ID is the same as EXPENSE_ID
    //             pstmt.setDate(3, Date.valueOf(expenseDate)); // Ensure the date format is correct
    //             pstmt.setDouble(4, expenseAmount);
    //             pstmt.setString(5, expenseCategory);
    //             pstmt.setString(6, expenseDescription);
    //             pstmt.setString(7, expensePaymentMethod);
    //             pstmt.executeUpdate();
    //             System.out.println("Expense entry added successfully.");
    //         }

    //     } catch (SQLException e) {
    //         System.out.println("Error while inserting expense. Contact Admin");
    //         e.printStackTrace();
    //     } catch (IllegalArgumentException e) {
    //         System.out.println("Invalid date format. Please use YYYY-MM-DD.");
    //         e.printStackTrace();
    //     }
    // }
// }

















































































import java.sql.*;
import java.util.Scanner;

public class expense_entries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userExpenseId=-1;

        System.out.println("1.SignUp");
        System.out.println("2.Login(Existing Account)");
        System.out.println("3.Inser Expense.");
        System.out.println("Enter Choice:");
        String User_Choice = scanner.nextLine();
        switch (User_Choice) {
            case "1":
                SignUp(scanner);
                break;
            case "2":
                // logon(scanner);
                userExpenseId = logon(scanner);
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

    public static int logon(Scanner scanner) {
        String host_username = "system";
        String host_password = "9714399098";

        System.out.println("Enter UserName:");
        String userName = scanner.nextLine();
        System.out.println("Enter Password:");
        String userPassword = scanner.nextLine();

        String url = "jdbc:oracle:thin:@//king:1521/XE";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int expenseId = -1; // Default to -1 to indicate failure

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, host_username, host_password);

            // Prepare statement to fetch EXPENSE_ID
            String idQuery = "SELECT EXPENSE_ID FROM users_expense WHERE EXPENSE_NAME = ? AND EXPENSE_PASSWORD = ?";
            pstmt = conn.prepareStatement(idQuery);
            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                expenseId = rs.getInt("EXPENSE_ID");
                System.out.println("Login successful Welcome To Expense Tracer : "+userName);                   
            } else {
                System.out.println("Invalid Username or Password.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed. Contact Admin");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
         // Return the expense ID (or -1 if login failed)
        System.out.println("1.Inser Data:");
        System.out.println("Enter Your Choice:");
        String User_Choice = scanner.nextLine();
        switch (User_Choice) {
        case "1":
            
            if (expenseId != -1) {
                System.out.println("Proceeding to insert expense...");
                insertExpense(scanner, expenseId);
            } else {
                System.out.println("Login failed. Exiting program.");
            }
                break;
            default:
                throw new AssertionError();
            break;
        }
        
    }

    public static void insertExpense(Scanner scanner, int userExpenseId)
    {
        String host_username = "system";
        String host_password = "9714399098";
        String url = "jdbc:oracle:thin:@//king:1521/XE";

        try (Connection conn = DriverManager.getConnection(url, host_username, host_password)) {
            System.out.println("Enter Expense Date (YYYY-MM-DD):");
            String expenseDate = scanner.nextLine();
            System.out.println("Enter Expense Amount:");
            double expenseAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Expense Category:");
            String expenseCategory = scanner.nextLine();
            System.out.println("Enter Expense Description:");
            String expenseDescription = scanner.nextLine();
            System.out.println("Enter Expense Payment Method:");
            String expensePaymentMethod = scanner.nextLine();

            String insertQuery = "INSERT INTO expenses_expense (EXPENSE_ID, USER_EXPENSE_ID, EXPENSE_DATE, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_PAYMENT_METHOD) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setInt(1, userExpenseId); // Use the fetched EXPENSE_ID
                pstmt.setInt(2, userExpenseId); // Assuming USER_EXPENSE_ID is the same as EXPENSE_ID
                pstmt.setDate(3, Date.valueOf(expenseDate)); // Ensure the date format is correct
                pstmt.setDouble(4, expenseAmount);
                pstmt.setString(5, expenseCategory);
                pstmt.setString(6, expenseDescription);
                pstmt.setString(7, expensePaymentMethod);
                pstmt.executeUpdate();
                System.out.println("Expense entry added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting expense. Contact Admin");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            e.printStackTrace();
        }
    }
}