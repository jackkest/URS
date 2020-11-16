public class Professor {

   private int uid;
   private String name;
   private String department;
   private Course[] courses;
   
   public Professor(int uidIn, String nameIn, String departmentIn, Course[] coursesIn) {
      uid = uidIn;
      name = nameIn;
      department = departmentIn;
      courses = coursesIn;
   }
   
   public int getUID() {
      return uid;
   }
   
   public String getName() {
      return name;
   }
   
   public String getDepartment() {
      return department;
   }
   
   public Course[] getCourses() {
      return courses;
   }
}