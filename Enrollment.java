import java.util.ArrayList;

public class Enrollment {
   
   private int numberOfCourses;
   private ArrayList<Course> courses;
   private Student student;
   
   // to-do: implement checkEligibility()
   
   public Enrollment(int numberOfCoursesIn, ArrayList<Course> coursesIn, Student studentIn) {
      numberOfCourses = numberOfCoursesIn;
      courses = coursesIn;
      student = studentIn;
   }
   
   public ArrayList<Course> getCourses() {
      return courses;
   }
   
   public boolean addCourse(Course c, Student s) {
      numberOfCourses++;
      courses.add(c);
      
      c.add(s); // Add student to the course
      boolean isAdded = s.addCourse(c); // Add course to student's list of current courses
      
      if (isAdded) {
         return true;
      }
      
      else {
         return false;
      }
   }
   
   public void dropCourse(Course c, Student s){
      numberOfCourses--;
      courses.remove(c);
      
      c.drop(s); // Remove student from the course
      s.removeCourse(c); // Remove course from student's list of current courses
   }
   
   public void printCourses() {
      for (Course c : courses) {
         System.out.println(c.toString() + "\n");
      }
   }
}