public class Course {
   
   private int crn;
   private int creditHours;
   private String courseName;
   private String courseSubject;
   private int courseNumber;
   private String classTime;
   private Professor instructor;
   private String instructMethod;
   private String courseLocation;
   private Student[] classList;
   
   public Course(int crnIn, int creditHoursIn, String courseNameIn, String courseSubjectIn, int courseNumberIn,
      String classTimeIn, Professor instructorIn, String instructMethodIn, String courseLocationIn, Student[] classListIn) {
      crn = crnIn;
      creditHours = creditHoursIn;
      courseName = courseNameIn;
      courseSubject = courseSubjectIn;
      courseNumber = courseNumberIn;
      classTime = classTimeIn;
      instructor = instructorIn;
      instructMethod = instructMethodIn;
      courseLocation = courseLocationIn;
      classList = classListIn;
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
   
   public Student[] getClassList() {
      return classList;
   }
}