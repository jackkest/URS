import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.io.Console;

public class RegistrationSystemDriver {

   private static String userName;
   private static String passWord;
   private static boolean isStudent = true; // to be set in login method
   private static boolean isProfessor = true;  // to be set in login method

   public static void main(String[] args) {
      
      Scanner scan = new Scanner(System.in);

      Connection conn = connToDB();
      if(loginToSystem(conn)){
         //don't have to do anything here, just check for exception.
      }
      else
      {
         System.exit(1); // an exception occured
      }

      if (isStudent) {
         System.out.println("-------------------------------\n| Student Registration System |\n-------------------------------\n");
         
         // System.out.print student current schedule (current courses enrolled in)
         // If not enrolled in any course System.out.println("No courses to display");
         
         System.out.println("-----------------------------\n| To add a class type ADD   |\n|                           |");
         System.out.println("| To drop a class type DROP |\n|                           |");
         System.out.println("| To logout type LOGOUT     |\n-----------------------------\n\n");
         System.out.print("Please choose an option: ");
         
         String s = scan.next();
         
         while(!s.toUpperCase().equals("LOGOUT")) { 
         
            if (s.toUpperCase().equals("ADD")) {
            
               // PRINT AVAILABLE COURSES -> Database
            
               System.out.print("Please input the CRN for the class you want to add: ");
               int crn = scan.nextInt();
               // Enrollment.addCourse(crn);
               
               //System.out.println course info -> ADDED TO SCHEDULE
            
            }
         
            if (s.toUpperCase().equals("DROP")) {
               System.out.print("Please input the CRN for the class you want to drop: ");
               int crn = scan.nextInt();
               // Enrollment.dropCourse(crn);
            
               //System.out.println course info -> DROPPED FROM SCHEDULE
            }
            
            else {
               System.out.println("Invalid choice. Please try again.\n");
            }
         
            System.out.print("Please choose an option: ");
            s = scan.next();
         }
      }
      
      if (isProfessor) {
         System.out.println("Student Registration System\n\n");
         
         // System.out.print professor current schedule (current courses teaching)
         // If not teaching in any courses System.out.println("No courses to display");
         
         System.out.println("To print a class list type CLASSLIST\n\n");
         System.out.println("To logout type LOGOUT\n\n");
         System.out.print("Please choose an option: ");
         
         String s = scan.next();
         
         while(!s.toUpperCase().equals("LOGOUT")) {
         
            if(!s.toUpperCase().equals("CLASSLIST")) {
            
               System.out.print("Please choose a CRN: ");
               int crn = scan.nextInt();
            
            //while(crn is not valid) {
               System.out.println("Invalid CRN. Please try again.\n");
               System.out.print("Please choose a CRN: ");
               crn = scan.nextInt();
            //}
            
            // print class list for given CRN =-> DATABASE
            }
            
            else {
               System.out.println("Invalid choice. Please try again.\n");
            }
         
            System.out.print("Please choose an option: ");
            s = scan.next();
         }
            
      }
   }

   public static String getMD5(String passwordIn) {
      try{
         MessageDigest md = MessageDigest.getInstance("MD5"); // md5 hashing

         byte[] messageDigest = md.digest(passWord.getBytes());
         BigInteger hash = new BigInteger(1, messageDigest);
         String hashtext = hash.toString(16);

         while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
         }
         return hashtext;
      }

      catch(NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
      return null;
   }

   public static Connection connToDB() {
      try {
         Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/urs?user=root&password={B{YW%(<xB#{c]6*");
         conn.setAutoCommit(true);
         return conn;
      }
      catch(SQLException ex) {
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
         System.out.println("\nError connecting to registration database. Exiting...");
         System.exit(1);
      }
      return null;
   }

   public static String getPassWord(){
      Console console = null;
      try{
         console = System.console();

         if(console != null)
         {
            char[] pswd = console.readPassword("Password: ");
            return new String(pswd);
         }
         else{
            System.out.println("Console null, please use this application in the command line or teminal.");
            System.exit(1);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public static boolean loginToSystem(Connection conn) {

      Scanner input = new Scanner(System.in);

      while(true) {
         System.out.print("Please chose an option:\n\n(1): Student\n(2): Professor\n\nChoice: ");
         int choice = input.nextInt();

         if (choice == 1) {
            isProfessor = false;
            break;
         }
         else if (choice == 2) {
            isStudent = false;
            break;
         }
         else {
            continue;
         }
      }

      try {
         input = new Scanner(System.in); // reconstruct the scanner for whatever reason

         ResultSet resultSet = null;
         while (true) {

            System.out.print("\n----------------\n|    Login     |\n----------------\nUsername: ");
            userName = input.nextLine();
            passWord = getPassWord();

            String dbQuery = "SELECT uid, firstName, lastName, major, totalCreditHours, currentGPA, " +
                    "userName, passHash FROM student WHERE userName = ? AND passHash = ?";

            PreparedStatement loginMatch = conn.prepareStatement(dbQuery);

            loginMatch.setString(1, userName);
            loginMatch.setString(2, getMD5(passWord));

            if (loginMatch.execute()) {
               resultSet = loginMatch.getResultSet();
            }
            else {
               System.out.println("Error Logging in, exiting...");
               System.exit(1);   // 1 == problem
            }

            if (!resultSet.next()) {
               System.out.println("\nIncorrect Login information, please try again.");
               continue;
            }
            else {
               resultSet.beforeFirst();   // if the result set is not empty, roll the cursor back one row
            }

            // code to construct student object with result set
            System.out.println("\nWelcome, " + userName + "!\n");
            return true;
         }
      }
      catch(SQLException ex)
      {
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
         return false;
      }
   }
}