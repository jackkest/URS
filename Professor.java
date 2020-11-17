public class Professor {

   private int instructorID;
   private String firstName;
   private String lastName;
   private String department;
   private int[] courses;
   
   public Professor(int instructorIDIn, String firstNameIn, String lastNameIn, String departmentIn, int[] coursesIn) {
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
   
   public int[] getCourses() {
      return courses;
   }
}