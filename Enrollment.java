import java.util.ArrayList;

public class Enrollment {

   private ArrayList<Course> courses;
   private Student student;


   public Enrollment(ArrayList<Course> coursesIn, Student studentIn) {
      courses = coursesIn;
      student = studentIn;
   }
   
   public ArrayList<Course> getCourses() {
      return courses;
   }
   
   public boolean addCourse(Course c, Student s) {
         // because of way enrollment object is constructed, adding here creates duplicate records
      return s.addCourse(c);
   }
   
   public void dropCourse(Course c, Student s){
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