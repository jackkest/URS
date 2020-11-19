public class Student {
 
   private int uid;
   private String firstName;
   private String lastName;
   private String major;
   private Course[] currentCourses;
   private Course[] allCourses;
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
   
   public String toString() {
      String s = firstName + " " + lastName + " | Banner ID: " + uid;
      return s;
   }
}