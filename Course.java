import java.util.ArrayList;

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
      classList = new ArrayList<Student>();
   }

   public int getCRN(){
      return crn;
   }

   public String getClassTime(){
      return classTime;
   }

   public int getInstructorID(){
      return instructorID;
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
   
   public ArrayList<Student> getClassList(){
      return classList;
   }

   public boolean checkConflictingDate(Course c){
      return classTime.equals(c.getClassTime());
   }
   
   public String getCourseHeader() {
      StringBuilder sb = new StringBuilder();
      String tableOutput = "+------+------+-----------------------------------------+";
      sb.append(tableOutput + "\n");
      sb.append(String.format("| %-5s", courseSubject));
      sb.append(String.format("| %-5s", courseNumber));
      sb.append(String.format("| %-40s", courseName) + "|\n");
      sb.append(tableOutput);
      return sb.toString();
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();

      String[] date = classTime.split("\\.");
      date[1] = date[1].substring(0, 2) + ":" + date[1].substring(2);

      // pretty table output
      sb.append(String.format("| %-5s", courseSubject));
      sb.append(String.format("| %-5s", courseNumber));
      sb.append(String.format("| %-40s", courseName));
      sb.append(String.format("| %-6s", crn));
      sb.append(String.format("| %-8s", creditHours));
      sb.append(String.format("| %-20s", date[0] + " " + date[1]));
      sb.append(String.format("| %-10s", courseLocation) + " |");

      return sb.toString();
   }

   public static String getTableHeader(){
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("| %-5s", "SUBJ"));
      sb.append(String.format("| %-5s", "NUM"));
      sb.append(String.format("| %-40s", "NAME"));
      sb.append(String.format("| %-6s", "CRN"));
      sb.append(String.format("| %-8s", "CREDITS"));
      sb.append(String.format("| %-20s","CLASSTIME"));
      sb.append(String.format("| %-10s", "LOCATION") + " |");
      return sb.toString();
   }
}