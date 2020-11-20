import java.util.ArrayList;

public class Professor {

   private int instructorID;
   private String firstName;
   private String lastName;
   private String department;
   private ArrayList<Course> courses;
   
   public Professor(int instructorIDIn, String firstNameIn, String lastNameIn, String departmentIn) {
      instructorID = instructorIDIn;
      firstName = firstNameIn;
      lastName = lastNameIn;
      department = departmentIn;
      courses = new ArrayList<Course>();
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

   public Course getCourseFromCRN(int crnIn){
      for(Course c : getCourses()){
         if(c.getCRN() == crnIn){
            return c;
         }
      }
      return null;   // this should never happen
   }
   
   public void printCourses() {
      String tableOutput =  "+------+------+--------------------------------" +
              "---------+-------+---+---------------------+------------+";

      if (!courses.isEmpty()) {
         System.out.println(tableOutput);
         for (Course c : courses) {
            System.out.println(c.toString());
         }
         System.out.println(tableOutput);
      }
      
      else {
         System.out.println("+-------------------------------------+\n" +
                            "|        No Courses to display        |\n" +
                            "+-------------------------------------+\n");
      }
   }
   
   public void printClassList(Course c) {
      System.out.println("\n" + c.getCourseHeader() + "\n");
      
      ArrayList<Student> classList = c.getClassList();

      if(classList.isEmpty())
         System.out.println("+----------------------+\n" +
                            "| No Students Enrolled |\n" +
                            "+----------------------+");

      for (Student s : classList) {
         System.out.println(s.toString());
      }
   }

   public void addCourse(Course c){
      courses.add(c);
   }
}