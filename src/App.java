import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
//courseDAO.addCourse(new Course("Data Structures", 1, 1200));
//courseDAO.addCourse(new Course("Web Development", 2, 1500));

ArrayList<Course> courses = courseDAO.getAllCourses();
for (Course c : courses) {
    c.displayInfo();
    StudentDAO studentDAO = new StudentDAO();
Course course1 = courseDAO.getAllCourses().get(0); // grabs the first saved course
//studentDAO.addStudent(new Student("Ankit", 101, 85, course1));
//studentDAO.addStudent(new Student("Priya", 102, 92, course1));
//studentDAO.addStudent(new Student("Aryan", 250, 96, course1));

//studentDAO.updateStudent(101, 70, "C");
//
studentDAO.getStudentByRoll(101);

ArrayList<Student> students = studentDAO.getAllStudents();
for (Student s : students) {
    s.displayInfo();
}
}
        // Course c1= new Course("Internet of things", 1, 1000);
        // Student s1=new Student("Aryan",101,88.5,c1);
        // Course c2= new Course("Internet of things", 1, 1000);
        // Student s2=new Student("Ankit",102,30,c2);
        // Course c3= new Course("Internet of things", 1, 1000);
        //  Student s3=new Student("Anurag",103,90,c3);
        // Course c4= new Course("Internet of things", 1, 1000);
        // Student s4=new Student("Akash",104,98,c4);
        // Student s5 = new Student("Aditya", 86, 98, c4);
        // System.out.println("------------------------");
        // ArrayList <Student> studentList= new ArrayList<>();
        // studentList.add(s1);
        // studentList.add(s2);
        // studentList.add(s3);
        // studentList.add(s4);
        // studentList.add(s5);
        // for(Student s : studentList){
        //  s.displayInfo();
        //  s.isPassing();
        //  s.calculateGrade();
        //  s.isTopper();
        //  System.out.println("------------------------");
        // }
        // GraduateStudent g1 = new GraduateStudent("Abhishek", 34, 100, c4, "AOT");
        // g1.displayInfo();
    }
}
