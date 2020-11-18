public class Course {
   
   private int crn;
   private int creditHours;
   private String courseName;
   private String courseSubject;
   private int courseNumber;
   private String classTime;
   private Professor instructor;
   private int instructorID;
   private String instructMethod;
   private String courseLocation;
   private Student[] classList;
   
   public Course(int crnIn, int creditHoursIn, String courseNameIn, String courseSubjectIn, int courseNumberIn,
      String classTimeIn, Professor instructorIn, int instructorIDIn, String instructMethodIn, String courseLocationIn) {
      crn = crnIn;
      creditHours = creditHoursIn;
      courseName = courseNameIn;
      courseSubject = courseSubjectIn;
      courseNumber = courseNumberIn;
      classTime = classTimeIn;
      instructor = instructorIn;
      instructorID = instructorIDIn;
      instructMethod = instructMethodIn;
      courseLocation = courseLocationIn;
   }
   
   // To-do: Jack
   public void add(Student s) {
   
   }
   
   // To-do: Jack
   public void drop(Student s) {
   
   }
   
   public void setInstructor(Professor p) {
      instructor = p;
   }
   
   public Professor getInstructor() {
      return instructor;
   }
   
   public Student[] getClassList(){
      return classList;
   }
   
   public String toString() {
      
      String s = courseSubject + " " + courseNumber + " | " + courseName + " | CRN: " + crn + " | Hours: " + creditHours
         + "\n\t\tTime: " + classTime + " | Location: " + courseLocation
         + " | Instructional Method: " + instructMethod + "\n\t\tInstructor: " + instructor;
      
      return s;
   }
}