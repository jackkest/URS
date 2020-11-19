import java.util.ArrayList;

public class Course {
   
   private int crn;
   private int creditHours;
   private String courseName;
   private String courseSubject;
   private int courseNumber;
   private String classTime;
   private Professor instructor; // todo: set this in it's own method
   private int instructorID;
   private String instructMethod;
   private String courseLocation;
   private ArrayList<Student> classList;
   
   public Course(int crnIn, int creditHoursIn, String courseNameIn, String courseSubjectIn, int courseNumberIn,
      String classTimeIn, int instructorIDIn, String instructMethodIn, String courseLocationIn) {
      crn = crnIn;
      creditHours = creditHoursIn;
      courseName = courseNameIn;
      courseSubject = courseSubjectIn;
      courseNumber = courseNumberIn;
      classTime = classTimeIn;
      instructorID = instructorIDIn;
      instructMethod = instructMethodIn;
      courseLocation = courseLocationIn;
   }
   
   public void add(Student s) {
      classList.add(s);
   }
   
   public void drop(Student s) {
      classList.remove(s);
   }
   
   public void setInstructor(Professor p) {
      instructor = p;
   }
   
   public Professor getInstructor() {
      return instructor;
   }
   
   public ArrayList<Student> getClassList(){
      return classList;
   }
   
   public String getCourseHeader() {
      String s = courseSubject + " " + courseNumber + " | " + courseName;
      return s;
   }
   
   public String toString() {
      
      String s = courseSubject + " " + courseNumber + " | " + courseName + " | CRN: " + crn + " | Hours: " + creditHours
         + "| Time: " + classTime + " | Location: " + courseLocation
         + " | Instructional Method: " + instructMethod + "| Instructor: " + instructor;
      
      return s;
   }
}