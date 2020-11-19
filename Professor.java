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
   
   public void printCourses() {
      if (!courses.isEmpty()) {
         for (Course c : courses) {
            System.out.println(c.toString() + "\n");
         }
      }
      
      else {
         System.out.println("\n+-------------------------------------+\n" +
                              "|        No Courses to display        |\n" +
                              "+-------------------------------------+\n");
      }
   }
   
   public void printClassList(Course c) {
      System.out.println(c.getCourseHeader() + "\n");
      
      ArrayList<Student> classList = c.getClassList();
      for (Student s : classList) {
         System.out.println(s.toString());
      }
   }
}