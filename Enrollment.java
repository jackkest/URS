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
   
   // database information: Jack
   public void addCourse(int crn) {
      // Student info for user
      // Course matching CRN needs to be added to the Student's course list
      //Student.currentCourses[n] = course
      // Course.add(student)
   }
   
   // database information: Jack
   public void dropCourse(int crn) {
      // Course.drop(student)
   }
   
}