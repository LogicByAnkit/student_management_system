import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Course c1= new Course("Internet of things", 1, 1000);
        Student s1=new Student("Aryan",101,88.5,c1);
        Course c2= new Course("Internet of things", 1, 1000);
        Student s2=new Student("Ankit",102,30,c2);
        Course c3= new Course("Internet of things", 1, 1000);
         Student s3=new Student("Anurag",103,90,c3);
        Course c4= new Course("Internet of things", 1, 1000);
        Student s4=new Student("Akash",104,98,c4);
        Student s5 = new Student("Aditya", 86, 98, c4);
        System.out.println("------------------------");
        ArrayList <Student> studentList= new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        for(Student s : studentList){
         s.displayInfo();
         s.isPassing();
         s.calculateGrade();
         s.isTopper();
         System.out.println("------------------------");
        }
        GraduateStudent g1 = new GraduateStudent("Abhishek", 34, 100, c4, "AOT");
        g1.displayInfo();
    }
}
