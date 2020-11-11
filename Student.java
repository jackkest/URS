public class Student {
 
   private int uid;
   private String name;
   private String major;
   private Course[] currentCourses;
   private Course[] allCourses;
   private int totalCreditHours;
   private float currentGPA;
   private char[] currentGrades;
   
   public Student(int uidIn, String nameIn, String majorIn) {
      uid = uidIn;
      name = nameIn;
      major = majorIn;
   }
   
   public Student(int uidIn, String nameIn, String majorIn, Course[] currentCoursesIn, Course[] allCoursesIn,
      int totalCreditHoursIn, float currentGPAIn, char[] currentGradesIn) {
      uid = uidIn;
      name = nameIn;
      major = majorIn;
      currentCourses = currentCoursesIn;
      allCourses = allCoursesIn;
      totalCreditHours = totalCreditHoursIn;
      currentGPA = currentGPAIn;
      currentGrades = currentGradesIn;
   }
   
   public int getUID() {
      return uid;
   }
   
   public String getName() {
      return name;
   }
   
   public String getMajor() {
      return major;
   }
   
   public Course[] getCurrentCourses() {
      return currentCourses;
   }

   public Course[] getAllCourses() {
      return allCourses;
   }

   public int getCreditHours() {
      return totalCreditHours;
   }
   
   public float getGPA() {
      return currentGPA;
   }
   
   public char[] getGrades() {
      return currentGrades;
   }
}