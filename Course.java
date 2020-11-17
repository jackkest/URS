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
   
   // To-do
   public void add(Student s) {
   
   }
   
   // To-do
   public void drop(Student s) {
   
   }
   
   public void setInstructor(Professor p) {
      instructor = p;
   }
   
   public Professor getInstructor() {
      return instructor;
   }
   
   public Student[] getClassList(){
	return null;
   }
}