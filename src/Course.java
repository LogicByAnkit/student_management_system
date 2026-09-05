//Class
public class Course implements Displayable {
    String courseName;
    int courseCode;
    int courseFee;
    //Constructor
    public  Course(String courseName, int courseCode,int courseFee){
      this.courseName=courseName;
      this.courseCode = courseCode;
        this.courseFee=courseFee;
    }
    //Method
    public void displayInfo(){ //same method as the interface
System.out.println("Course : "+courseName);
System.out.println("Course id : "+courseCode);
System.out.println("Fee of course : "+courseFee);
    }
}