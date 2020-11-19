import java.util.ArrayList;

public class Professor {

   private int instructorID;
   private String firstName;
   private String lastName;
   private String department;
   private ArrayList<Course> courses;
   
   public Professor(int instructorIDIn, String firstNameIn, String lastNameIn, String departmentIn, ArrayList<Course> coursesIn) {
      instructorID = instructorIDIn;
      firstName = firstNameIn;
      lastName = lastNameIn;
      department = departmentIn;
      courses = coursesIn;
   }
   
   public int getInstructorID() {
      return instructorID;
   }
   
   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }
   
   public String getDepartment() {
      return department;
   }
   
   public ArrayList<Course> getCourses() {
      return courses;
   }
}