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
      String tableHeader = "+--------------+\n" +
                           "| Your Courses |";
      String tableOutput =  "+------+------++-------------------------------" +
              "---------+-------+---------+---------------------+------------+";

      if (!courses.isEmpty()) {
         System.out.println(tableHeader + "\n" + tableOutput + "\n" + Course.getTableHeader() + "\n" + tableOutput);
         for (Course c : courses) {
            System.out.println(c.toString());
         }
         System.out.println(tableOutput + "\n");
      }
      
      else {
         System.out.println("+-------------------------------------+\n" +
                            "|        No Courses to display        |\n" +
                            "+-------------------------------------+\n");
      }
   }
   
   public void printClassList(Course c) {
      String tableOutput = "+-----------+-----------+------+";
      System.out.println(c.getCourseHeader() + "\n");
      ArrayList<Student> classList = c.getClassList();

      if(classList.isEmpty()) {
         System.out.println("+----------------------+\n" +
                            "| No Students Enrolled |\n" +
                            "+----------------------+\n");
         return;
      }
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("| %-10s", "FIRSTNAME"));
      sb.append(String.format("| %-10s", "LASTNAME"));
      sb.append(String.format("| %-5s", "ID") + "|");
      System.out.println("+-----------+-----------+\n" +
                         "|   Enrolled Students   |" + "\n" + tableOutput + "\n" + sb.toString() + "\n" + tableOutput);
      for (Student s : classList) {
         System.out.print(s.toString() + "\n");
      }
      System.out.println(tableOutput + "\n");
   }

   public void addCourse(Course c){
      courses.add(c);
   }
}