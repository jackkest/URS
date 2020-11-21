import java.util.ArrayList;

public class Student {
 
   private int uid;
   private String firstName;
   private String lastName;
   private String major;
   private ArrayList<Course> currentCourses;
   private ArrayList<Integer> enrolled;
   private ArrayList<Course> allCourses;
   private int totalCreditHours;
   private float currentGPA;

   public Student(int uidIn, String firstNameIn, String lastNameIn, String majorIn,
      int totalCreditHoursIn, float currentGPAIn) {
      uid = uidIn;
      firstName = firstNameIn;
      lastName = lastNameIn;
      major = majorIn;
      totalCreditHours = totalCreditHoursIn;
      currentGPA = currentGPAIn;
      currentCourses = new ArrayList<Course>();
      enrolled = new ArrayList<Integer>();
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
   
   public boolean checkEnrollment(Course c) {
      for(Course toCheck : currentCourses){
         if(toCheck.getCRN() == c.getCRN()){ // all we need to compare is the CRN since it is a unique identifier
            return true;
         }
      }
      return false;
   }

   public boolean addCourse(Course c) {
      for(Course toSearch : currentCourses){
         if(toSearch.getCRN() == c.getCRN()){   // if the course is found in currentCourses, they cannot add it
            return false;
         }
         else if(toSearch.checkConflictingDate(c)){
            return false;
         }
      }
      currentCourses.add(c);
      return true;
   }
   
   public void removeCourse(Course c) {
      currentCourses.remove(c);
   }
   
   public void printCurrentCourses() {
      //todo: output column descriptions in table format
      String tableOutput = "+------+------+--------------------------------" +
              "---------+-------+---+---------------------+------------+";

      //StringBuilder sb = new StringBuilder();

      if (!currentCourses.isEmpty()) {
         System.out.println("+-------------+\n" +
                            "|   Schedule  |");

         System.out.println(tableOutput);
         for (Course c : currentCourses) {
            System.out.println(c.toString());
         }
         System.out.println(tableOutput);
      }
      else {
         System.out.println("+-------------+\n" +
                            "|   Schedule  |\n" +
                            "+-------------------------------------+\n" +
                            "|        No Courses to display        |\n" +
                            "+-------------------------------------+\n");
      }
   }
   
   public String toString() {

      StringBuilder sb = new StringBuilder();
      sb.append(String.format("| %-10s", firstName));
      sb.append(String.format("| %-10s", lastName));
      sb.append(String.format("| %-5s", uid) + "|");
      return sb.toString();
   }
}