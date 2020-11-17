public class Enrollment {
   
   private int numberOfCourses;
   private Course[] courses;
   private Student student;
   
   // to-do: implement checkEligibility()
   
   public Enrollment(int numberOfCoursesIn, Course[] coursesIn, Student studentIn) {
      numberOfCourses = numberOfCoursesIn;
      courses = coursesIn;
      student = studentIn;
   }
   
   public void printCourses() {
   
   }
   
   public void getCourse() {
   
   }
   
   // database information
   public void addCourse(int crn) {
      // Course.add(student)
   }
   
   // database information
   public void dropCourse(int crn) {
      // Course.drop(student)
   }
   
}