import java.util.ArrayList;

public class Student {
 
   private int uid;
   private String firstName;
   private String lastName;
   private String major;
   private ArrayList<Course> currentCourses;
   private ArrayList<Course> allCourses;
   private int totalCreditHours;
   private float currentGPA;
   
   public Student(int uidIn, String firstNameIn, String lastNameIn, String majorIn) {
      uid = uidIn;
      firstName = firstNameIn;
      lastName = lastNameIn;
      major = majorIn;
   }
   
   public Student(int uidIn, String firstNameIn, String lastNameIn, String majorIn,
      int totalCreditHoursIn, float currentGPAIn) {
      uid = uidIn;
      firstName = firstNameIn;
      lastName = lastNameIn;
      major = majorIn;
      totalCreditHours = totalCreditHoursIn;
      currentGPA = currentGPAIn;
      currentCourses = new ArrayList<Course>();
   }
   
   public int getUID() {
      return uid;
   }
   
   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }
   
   public String getMajor() {
      return major;
   }
   
   public ArrayList<Course> getCurrentCourses() {
      return currentCourses;
   }

   public ArrayList<Course> getAllCourses() {
      return allCourses;
   }

   public int getCreditHours() {
      return totalCreditHours;
   }
   
   public float getGPA() {
      return currentGPA;
   }
   
   public void addCourse(Course c) {
      currentCourses.add(c);
   }
   
   public void removeCourse(Course c) {
      currentCourses.remove(c);
   }
   
   public void printCurrentCourses() {
      String tableOutput =  "+------------------------------------------------" +
              "------------------------------------------------------+";
      if (!currentCourses.isEmpty()) {
         System.out.println(tableOutput);
         for (Course c : currentCourses) {
            System.out.println(c.toString() + "\n" + tableOutput);
         }
      }
      else {
         System.out.println("\n+-------------------------------------+\n" +
                              "|        No Courses to display        |\n" +
                              "+-------------------------------------+\n");
      }
   }
   
   public String toString() {
      String s = firstName + " " + lastName + " | Banner ID: " + uid;
      return s;
   }
}