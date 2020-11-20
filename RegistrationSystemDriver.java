import java.io.IOException;
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
import java.util.ArrayList;

public class RegistrationSystemDriver {

   private static String userName;
   private static String passWord;
   private static Student studentUser;
   private static Professor profUser;
   private static boolean isStudent = true; // if it is a student, then it is not a professor

   public static void main(String[] args) {
      
      Scanner scan = new Scanner(System.in);
      Connection conn = connToDB();
   
      if(loginToSystem(conn)){
         //don't have to do anything here, just check for exception.
      }
      else {
         System.exit(1); // an exception occurred
      }
   
      if (isStudent) studentPanel(scan, conn);
      else professorPanel(scan, conn);
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
            System.out.println("Console null, please use this application in the command line or terminal.");
            System.exit(1);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public static boolean loginToSystem(Connection conn) {   // need to try to split this up for readability
      System.out.println("\n-----------------------\n| Registration System |\n-----------------------");
   
      Scanner input = new Scanner(System.in);
   
      while(true) {
         System.out.print("Please chose an option:\n\n(1): Student\n(2): Professor\n\nChoice: ");
         int choice = input.nextInt();
      
         if (choice == 1) {
            break;
         }
         else if (choice == 2) {
            isStudent = false;
            break;
         }
      }
   
      clearConsole();
      try {
         input = new Scanner(System.in); // reconstruct the scanner for whatever reason
      
         ResultSet resultSet = null;
         String dbQuery = null;
         while (true) {
         
            System.out.print("\n+-----------+\n|   Login   |\n+-----------+\nUsername: ");
            userName = input.nextLine();
            passWord = getPassWord();
         
            if (isStudent) {
               dbQuery = "SELECT uid, firstName, lastName, major, totalCreditHours, currentGPA, " +
                       "userName, passHash FROM student WHERE userName = ? AND passHash = ?";
            } else {
               dbQuery = "SELECT uid, firstName, lastName, department, username, passhash " +
                       "FROM professor WHERE username = ? AND passhash = ?";
            }
         
            PreparedStatement loginMatch = conn.prepareStatement(dbQuery);
            loginMatch.setString(1, userName);
            loginMatch.setString(2, getMD5(passWord));
         
            if (loginMatch.execute()) {
               resultSet = loginMatch.getResultSet();
            } else {
               System.out.println("Error Logging in, exiting...");
               System.exit(1);   // 1 == problem
            }
         
            if (!resultSet.next()) {
               System.out.println("\nIncorrect Login information, please try again.");
               continue;
            } else {
               resultSet.first();   // if the result set is not empty, roll the cursor back one row
               clearConsole();
               break;
            }
         }
      
            // code to construct student object with result set
         if(isStudent) {
            studentUser =  new Student(resultSet.getInt("uid"), resultSet.getString("firstName"),
                    resultSet.getString("lastName"), resultSet.getString("major"),
                    resultSet.getInt("totalCreditHours"), resultSet.getFloat("currentGPA"));
            resultSet.close();
         
            System.out.println("+-----------------------------+\n" +
                               "| Student Registration System |\n" +
                               "+-----------------------------+\n");
            System.out.println("Welcome, " + studentUser.getFirstName()
                                 + " " + studentUser.getLastName() + "\n\n");
            String query = "SELECT Course_crn FROM studentToCourse WHERE Student_uid = ?";
         
            PreparedStatement courseMatch = conn.prepareStatement(query);
            courseMatch.setInt(1, studentUser.getUID());
         
            if (courseMatch.execute()) {
               resultSet = courseMatch.getResultSet();
            
            }
            else {
               System.out.println("Database error, exiting...");
               System.exit(1);   // 1 == problem
            }
         
            if (!resultSet.next()) {      // the student isn't currently enrolled in any courses
               System.out.println("\n+-------------------------------------+\n" +
                                    "|        No Courses to display        |\n" +
                                    "+-------------------------------------+\n");
            }
            else {
               resultSet.beforeFirst();   // if the result set is not empty, roll the cursor back to before first entry
            
               while(resultSet.next()) {
                  // construct a course object for every matching record
                  int courseCRN = resultSet.getInt("Course_crn");
               
                  query = "SELECT crn, creditHours, courseName, courseSubject, " +
                             "courseNumber, classTime, instructorID, instructMethod, courseLocation FROM course WHERE crn = ?";
                  courseMatch = conn.prepareStatement(query);
                  courseMatch.setInt(1, courseCRN);
                  courseMatch.execute();
                  ResultSet schedule = courseMatch.getResultSet();
               
                  while(schedule.next()){
                     Course c = new Course(schedule.getInt("crn"), schedule.getInt("creditHours"),
                             schedule.getString("courseName"), schedule.getString("courseSubject"),
                             schedule.getInt("courseNumber"), schedule.getString("classTime"),
                             schedule.getInt("instructorID"), schedule.getString("instructMethod"),
                             schedule.getString("courseLocation"));
                  
                     studentUser.addCourse(c);
                  }
                  schedule.close();
               }
               generateCourseInstructor(conn, studentUser);
            }
         }
         else{
            profUser = new Professor(resultSet.getInt("uid"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("department"));
            String query = "SELECT crn, creditHours, courseName, courseSubject, " +
                    "courseNumber, classTime, instructorID, instructMethod, courseLocation FROM course WHERE instructorID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, profUser.getInstructorID());
            ps.execute();
            ResultSet schedule = ps.getResultSet();
         
            while(schedule.next()){
               Course c = new Course(schedule.getInt("crn"), schedule.getInt("creditHours"),
                       schedule.getString("courseName"), schedule.getString("courseSubject"),
                       schedule.getInt("courseNumber"), schedule.getString("classTime"),
                       schedule.getInt("instructorID"), schedule.getString("instructMethod"),
                       schedule.getString("courseLocation"));
            
               profUser.addCourse(c);
            }
         }
         return true;
      }
      catch(SQLException ex)
      {
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
         return false;
      }
   }

   public static void clearConsole(){
      //Clears Screen
      try {
         if (System.getProperty("os.name").contains("Windows")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
         }
         else {
            Runtime.getRuntime().exec("clear");
         }
      }
      catch (IOException | InterruptedException ex) {
         ex.printStackTrace();
      }
   }

   public static void professorPanel(Scanner scannerIn, Connection db){
      Scanner scan = scannerIn;
   
      System.out.println("+-----------------------------+\n| Student" +
              " Registration System |\n+----------------------------" +
              "-+\n|     Professor Panel      |\n+--------------------------+\n");
      System.out.println("Welcome, " + profUser.getFirstName()
              + " " + profUser.getLastName() + "\n\n");
   
      for(Course c: profUser.getCourses())
      {
         c.setInstructor(profUser);    // professors can only see classes they teach, therefore we can assume they are the teacher
      }
      
      profUser.printCourses(); // Not sure if this is needed here still.
   
      showOptions(isStudent);
      String s = scan.next();
   
      while(!s.toUpperCase().equals("LOGOUT")) {
         try {
            if(s.toUpperCase().equals("CLASSLIST")) {
            
               System.out.print("Please choose a CRN: ");
               int crn = scan.nextInt();
            
               ArrayList<Course> courses = profUser.getCourses();
               
               String query = "SELECT crn, creditHours, courseName, courseSubject, " +
                             "courseNumber, classTime, instructorID, instructMethod, courseLocation FROM course WHERE crn = ?";
               PreparedStatement courseMatch = db.prepareStatement(query);
               courseMatch.setInt(1, crn);
                  
               if (courseMatch.execute()) { // if CRN is found then print the classList for the course
                  ResultSet course = courseMatch.getResultSet();
                  Course c = new Course(course.getInt("crn"), course.getInt("creditHours"),
                             course.getString("courseName"), course.getString("courseSubject"),
                             course.getInt("courseNumber"), course.getString("classTime"),
                             course.getInt("instructorID"), course.getString("instructMethod"),
                             course.getString("courseLocation"));
                  
                  profUser.printClassList(c);
               }
               else {
                  System.out.println("Invalid CRN. Unable to print class list.");
               }
            }
            
            else {
               clearConsole();
               System.out.print("----------------------------------------\n" +
                             "****Invalid choice. Please try again****\n" +
                              "----------------------------------------\n");
            }
         }
         catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            break;
         }
         
         showOptions(isStudent);
         s = scan.next();
      }
   }

   public static void studentPanel(Scanner scannerIn, Connection db){
      Scanner scan = scannerIn;
      studentUser.printCurrentCourses(); // Not sure if this is needed here still.
   
      showOptions(isStudent);
      String s = scan.next();
   
      while(!s.toUpperCase().equals("LOGOUT")) {
         
         try {
            if (s.toUpperCase().equals("ADD")) {
            
            // PRINT AVAILABLE COURSES
               String query = "SELECT crn, creditHours, courseName, courseSubject, " +
                    "courseNumber, classTime, instructorID, instructMethod, courseLocation FROM course";
               Statement stmt = db.createStatement();
               ResultSet coursesAvailable = stmt.executeQuery(query);
            
               while(coursesAvailable.next()){
                  Course c = new Course(coursesAvailable.getInt("crn"), coursesAvailable.getInt("creditHours"),
                       coursesAvailable.getString("courseName"), coursesAvailable.getString("courseSubject"),
                       coursesAvailable.getInt("courseNumber"), coursesAvailable.getString("classTime"),
                       coursesAvailable.getInt("instructorID"), coursesAvailable.getString("instructMethod"),
                       coursesAvailable.getString("courseLocation"));
                  
                  // If student is not enrolled, It is a course that they can add
                  if (!studentUser.checkEnrollment(c)) {
                     System.out.println(c.toString());
                  }
               }
               
               System.out.println("\n\n");
               
               System.out.print("Please input the CRN for the class you want to add: ");
               int crn = scan.nextInt();
               ArrayList<Course> courses = studentUser.getCurrentCourses();
               Enrollment e = new Enrollment(courses.size(), courses, studentUser);
            
               //todo: fix sqlexception
               query = "SELECT crn, creditHours, courseName, courseSubject, " +
                             "courseNumber, classTime, instructorID, instructMethod, courseLocation FROM course WHERE crn = ?";
               PreparedStatement courseMatch = db.prepareStatement(query);
               courseMatch.setInt(1, crn);
                  
               if (courseMatch.execute()) { // if CRN is found then add the course
                  ResultSet course = courseMatch.getResultSet();
                  Course c = new Course(course.getInt("crn"), course.getInt("creditHours"),
                             course.getString("courseName"), course.getString("courseSubject"),
                             course.getInt("courseNumber"), course.getString("classTime"),
                             course.getInt("instructorID"), course.getString("instructMethod"),
                             course.getString("courseLocation"));
                  
                  boolean isAdded = e.addCourse(c, studentUser);
                  if (isAdded) {
                     System.out.println("COURSE ADDED TO SCHEDULE:");
                     System.out.println(c.toString());
                  }
                  
                  else {
                     System.out.println("Class already added.");
                  }
               }
               
               else {
                  System.out.println("Invalid CRN. Unable to add class to schedule.");
               }
            }
         
            if (s.toUpperCase().equals("DROP")) {
            
               System.out.print("Please input the CRN for the class you want to drop: ");
               int crn = scan.nextInt();
               ArrayList<Course> courses = studentUser.getCurrentCourses();
               Enrollment e = new Enrollment(courses.size(), courses, studentUser);
            
               String query = "SELECT crn, creditHours, courseName, courseSubject, " +
                             "courseNumber, classTime, instructorID, instructMethod, courseLocation FROM course WHERE crn = ?";
               PreparedStatement courseMatch = db.prepareStatement(query);
               courseMatch.setInt(1, crn);
                  
               if (courseMatch.execute()) { // if CRN is found then remove the course
                  ResultSet course = courseMatch.getResultSet();
                  Course c = new Course(course.getInt("crn"), course.getInt("creditHours"),
                             course.getString("courseName"), course.getString("courseSubject"),
                             course.getInt("courseNumber"), course.getString("classTime"),
                             course.getInt("instructorID"), course.getString("instructMethod"),
                             course.getString("courseLocation"));
                  
                  e.dropCourse(c, studentUser);
                  
                  System.out.println("COURSE REMOVED FROM SCHEDULE:");
                  System.out.println(c.toString());
               }
               
               else {
                  System.out.println("Invalid CRN. Unable to drop class from schedule.");
               }
            }
            
            else {
               clearConsole();
               System.out.print("----------------------------------------\n" +
                             "****Invalid choice. Please try again****\n" +
                              "----------------------------------------\n");
            }
         }
         catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            break;
         }
         
         showOptions(isStudent);
         s = scan.next();
      }
   }

   public static void showOptions(boolean isStudentIn){
      if(isStudentIn){
         System.out.println("+---------------------------+\n" +
                            "|  To add a class type ADD  |\n" +
                            "|                           |");
         System.out.println("| To drop a class type DROP |\n" +
                            "|                           |");
         System.out.println("| To logout type LOGOUT     |\n" +
                            "+---------------------------+\n\n");
         System.out.print("Please choose an option: ");
      }
      else{
         System.out.println("+--------------------------------------+\n" +
                            "| To print a class list type CLASSLIST |");
         System.out.println("| To logout type LOGOUT                |\n" +
                            "+--------------------------------------+\n");
         System.out.print("Please choose an option: ");
      }
   }

   public static void generateCourseInstructor(Connection db, Student st) throws SQLException {
      String query = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
   
      for(Course c : st.getCurrentCourses()){
         query = "SELECT uid, firstName, lastName, department " +
                 "FROM professor WHERE uid = ?";
      
         ps = db.prepareStatement(query);
         ps.setInt(1, c.getInstructorID());
         ps.execute();
         rs = ps.getResultSet();
      
         if(rs.next()){
            Professor instructor = new Professor(rs.getInt("uid"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("department"));
            c.setInstructor(instructor);
         }
      }
   }
}