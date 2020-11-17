import java.util.Scanner;

public class RegistrationSystemDriver {

   public static void main(String[] args) {
   
      // login sets these to either true or false
      boolean isStudent = true;
      boolean isProfessor = true;
      
      Scanner scan = new Scanner(System.in);
   
      if (isStudent) {
         System.out.println("Student Registration System\n\n");
         
         // System.out.print student current schedule (current courses enrolled in)
         // If not enrolled in any course System.out.println("No courses to display");
         
         System.out.println("To add a class type ADD\n");
         System.out.println("To drop a class type DROP\n");
         System.out.println("To logout type LOGOUT\n\n");
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
}