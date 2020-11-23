import java.util.ArrayList;

public class Student {
 
   private int uid;
   private String firstName;
   private String lastName;
   private String major;
   private ArrayList<Course> currentCourses;
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
   
   public ArrayList<Course> getCurrentCourses() {
      return currentCourses;
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
      String tableOutput = "+------+------+--------------------------------" +
              "---------+-------+---------+---------------------+------------+";

      if (!currentCourses.isEmpty()) {
         System.out.println("+-------------+\n" +
                            "|   Schedule  |");
         /*
         StringBuilder sb = new StringBuilder();
         sb.append(String.format("| %-5s", "SUBJ"));
         sb.append(String.format("| %-5s", "NUM"));
         sb.append(String.format("| %-40s", "NAME"));
         sb.append(String.format("| %-6s", "CRN"));
         sb.append(String.format("| %-8s", "CREDITS"));
         sb.append(String.format("| %-20s","CLASSTIME"));
         sb.append(String.format("| %-10s", "LOCATION") + " |");

          */

         System.out.println(tableOutput + "\n" + Course.getTableHeader() + "\n" + tableOutput);
         for (Course c : currentCourses) {
            System.out.println(c.toString());
         }
         System.out.println(tableOutput + "\n");
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