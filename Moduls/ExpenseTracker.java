// import java.sql.*;
// import java.util.Scanner;

// public class ExpenseTracker {
//     private static final String DB_URL = "jdbc:oracle:thin:@//king:1521/XE";
//     private static final String DB_USER = "system";
//     private static final String DB_PASSWORD = "9714399098";  // Change this to a secure password

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int userExpenseId = -1;

//         while (true) {
//             System.out.println("\n1. Sign Up");
//             System.out.println("2. Login (Existing Account)");
//             System.out.println("3. Exit");
//             System.out.print("Enter Choice: ");
//             String userChoice = scanner.nextLine();

//             switch (userChoice) {
//                 case "1":
//                     signUp(scanner);
//                     break;
//                 case "2":
//                     userExpenseId = logon(scanner);
//                     if (userExpenseId != -1) {
//                         System.out.println("Proceeding to insert expense...");
//                         insertExpense(scanner, userExpenseId);
//                     } else {
//                         System.out.println("Login failed. Try again.");
//                     }
//                     break;
//                 case "3":
//                     System.out.println("Exiting program.");
//                     scanner.close();
//                     System.exit(0);
//                 default:
//                     System.out.println("Invalid choice. Please enter 1, 2, or 3.");
//             }
//         }
//     }

//     private static void signUp(Scanner scanner) {
//         System.out.println("\nEnter Details to Sign Up:");
//         System.out.print("Enter Your Name: ");
//         String expenseName = scanner.nextLine();
//         System.out.print("Enter Your Email Address: ");
//         String expenseEmail = scanner.nextLine();
//         System.out.print("Enter Your Password: ");
//         String expensePassword = scanner.nextLine();

//         String insertSQL = "INSERT INTO users_expense(EXPENSE_ID, EXPENSE_NAME, EXPENSE_EMAIL, EXPENSE_PASSWORD) " +
//                            "VALUES (users_expense_seq.NEXTVAL, ?, ?, ?)";

//         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//              PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//             pstmt.setString(1, expenseName);
//             pstmt.setString(2, expenseEmail);
//             pstmt.setString(3, expensePassword);
//             pstmt.executeUpdate();
//             System.out.println("You have successfully signed up. You can login now.");
//         } catch (SQLException e) {
//             System.out.println("Error while signing up. Contact Admin.");
//             e.printStackTrace();
//         }
//     }

//     private static int logon(Scanner scanner) {
//         System.out.print("\nEnter Username: ");
//         String userName = scanner.nextLine();
//         System.out.print("Enter Password: ");
//         String userPassword = scanner.nextLine();

//         String query = "SELECT EXPENSE_ID FROM users_expense WHERE EXPENSE_NAME = ? AND EXPENSE_PASSWORD = ?";
//         int expenseId = -1;

//         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//              PreparedStatement pstmt = conn.prepareStatement(query)) {
//             pstmt.setString(1, userName);
//             pstmt.setString(2, userPassword);
//             ResultSet rs = pstmt.executeQuery();

//             if (rs.next()) {
//                 expenseId = rs.getInt("EXPENSE_ID");
//                 System.out.println("Login successful! Welcome to Expense Tracker, " + userName);
//             } else {
//                 System.out.println("Invalid Username or Password.");
//             }
//             rs.close();
//         } catch (SQLException e) {
//             System.out.println("Database connection failed. Contact Admin.");
//             e.printStackTrace();
//         }

//         return expenseId;
//     }

//     private static void insertExpense(Scanner scanner, int userExpenseId) {
//         System.out.print("\nEnter Expense Date (YYYY-MM-DD): ");
//         String expenseDate = scanner.nextLine();
//         System.out.print("Enter Expense Amount: ");
//         double expenseAmount;
        
//         try {
//             expenseAmount = Double.parseDouble(scanner.nextLine()); // Prevent scanner issues
//         } catch (NumberFormatException e) {
//             System.out.println("Invalid amount. Please enter a valid number.");
//             return;
//         }

//         System.out.print("Enter Expense Category: ");
//         String expenseCategory = scanner.nextLine();
//         System.out.print("Enter Expense Description: ");
//         String expenseDescription = scanner.nextLine();
//         System.out.print("Enter Expense Payment Method: ");
//         String expensePaymentMethod = scanner.nextLine();

//         String insertQuery = "INSERT INTO expenses_expense (EXPENSE_ID, USER_EXPENSE_ID, EXPENSE_DATE, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_PAYMENT_METHOD) " +
//                              "VALUES (?, ?, ?, ?, ?, ?, ?)";

//         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//              PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
//             pstmt.setInt(1, userExpenseId);
//             pstmt.setInt(2, userExpenseId);
//             pstmt.setDate(3, Date.valueOf(expenseDate)); // Ensure valid date format
//             pstmt.setDouble(4, expenseAmount);
//             pstmt.setString(5, expenseCategory);
//             pstmt.setString(6, expenseDescription);
//             pstmt.setString(7, expensePaymentMethod);
//             pstmt.executeUpdate();
//             System.out.println("Expense entry added successfully.");
//         } catch (SQLException e) {
//             System.out.println("Error while inserting expense. Contact Admin.");
//             e.printStackTrace();
//         } catch (IllegalArgumentException e) {
//             System.out.println("Invalid date format. Please use YYYY-MM-DD.");
//         }
//     }
// }




























































// import java.lang.classfile.instruction.SwitchCase;
import java.sql.*;
import java.util.Scanner;

public class ExpenseTracker
{
    private static final String DB_URL = "jdbc:oracle:thin:@//king:1521/XE";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "9714399098";  // Change this to a secure password

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int userExpenseId = -1;

        while (true)
        {
            System.out.println("\n1. Sign Up");
            System.out.println("2. Login (Existing Account)");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            String userChoice1 = scanner.nextLine();

            switch(userChoice1)
            {
                case "1":
                    signUp(scanner);
                    break;
                case "2":
                    userExpenseId = logon(scanner);
                    break;
                case "3":
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void signUp(Scanner scanner)
    {
        System.out.println("\nEnter Details to Sign Up:");
        System.out.print("Enter Your Name: ");
        String expenseName = scanner.nextLine();
        System.out.print("Enter Your Email Address: ");
        String expenseEmail = scanner.nextLine();
        System.out.print("Enter Your Password: ");
        String expensePassword = scanner.nextLine();

        String insertSQL = "INSERT INTO users_expense(EXPENSE_ID, EXPENSE_NAME, EXPENSE_EMAIL, EXPENSE_PASSWORD) " +
                           "VALUES (users_expense_seq.NEXTVAL, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL))
             {
            pstmt.setString(1, expenseName);
            pstmt.setString(2, expenseEmail);
            pstmt.setString(3, expensePassword);
            pstmt.executeUpdate();
            System.out.println("You have successfully signed up. You can login now.");
        }
        catch (SQLException e)
        {
            System.out.println("Error while signing up. Contact Admin.");
            e.printStackTrace();
        }
    }

    private static int logon(Scanner scanner)
    {
        System.out.print("\nEnter Username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String userPassword = scanner.nextLine();

        String query = "SELECT EXPENSE_ID FROM users_expense WHERE EXPENSE_NAME = ? AND EXPENSE_PASSWORD = ?";
        int expenseId = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query))
            {
            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                expenseId = rs.getInt("EXPENSE_ID");
                System.out.println("Login successful! Welcome to Expense Tracker, " + userName);
            }
            else
            {
                System.out.println("Invalid Username or Password.");
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Database connection failed. Contact Admin.");
            e.printStackTrace();
        }

        System.out.println("1.Insert Expense");
        System.out.println("2.Exit");
        String userChoice2 = scanner.nextLine();
        switch(userChoice2)
        {
            case "1":
            if (expenseId != -1)
            {
                System.out.println("Proceeding to insert expense...");
                insertExpense(scanner, expenseId);
            }
            else
            {                           
                System.out.println("Login failed. Try again.");
            }
                break;
            case "2":
                System.out.println("Exiting program.");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }
        return expenseId;
    }

    private static void insertExpense(Scanner scanner, int userExpenseId)
    {
        System.out.print("\nEnter Expense Date (YYYY-MM-DD): ");
        String expenseDate = scanner.nextLine();
        System.out.print("Enter Expense Amount: ");
        double expenseAmount;
        
        try
        {
            expenseAmount = Double.parseDouble(scanner.nextLine()); // Prevent scanner issues
        }
        catch
        (NumberFormatException e)
        {
            System.out.println("Invalid amount. Please enter a valid number.");
            return;
        }

        System.out.print("Enter Expense Category: ");
        String expenseCategory = scanner.nextLine();
        System.out.print("Enter Expense Description: ");
        String expenseDescription = scanner.nextLine();
        System.out.print("Enter Expense Payment Method: ");
        String expensePaymentMethod = scanner.nextLine();

        String insertQuery = "INSERT INTO expenses_expense (EXPENSE_ID, USER_EXPENSE_ID, EXPENSE_DATE, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_PAYMENT_METHOD) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try
        (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery))
            {
            pstmt.setInt(1, userExpenseId);
            pstmt.setInt(2, userExpenseId);
            pstmt.setDate(3, Date.valueOf(expenseDate)); // Ensure valid date format
            pstmt.setDouble(4, expenseAmount);
            pstmt.setString(5, expenseCategory);
            pstmt.setString(6, expenseDescription);
            pstmt.setString(7, expensePaymentMethod);
            pstmt.executeUpdate();
            System.out.println("Expense entry added successfully.");
        }
        catch (SQLException e)
        {
            System.out.println("Error while inserting expense. Contact Admin.");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
}
