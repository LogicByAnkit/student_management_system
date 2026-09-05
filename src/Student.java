interface Displayable{
    void displayInfo();
}
public class Student implements Displayable {
    String name;
    int rollNumber;
    double marks;
    Course course;
    public Student(String name, int roll_number, double marks, Course course) {
        this.name = name;
        this.rollNumber = roll_number;
        this.marks = marks;
        this.course = course;
    }
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("roll number: " + rollNumber);
        System.out.println("marks: " + marks);
        System.out.println("Grade : "+calculateGrade());
        course.displayInfo();
    }

    public boolean isPassing() {
        if (marks >= 40) {
            System.out.println(marks + " student has passed");
            return true;
        } else {
            System.out.println(marks + " student has failed");
            return false;
        }
    }

    public String calculateGrade() {
        String grade;
        if (marks >= 90) grade = "A";
        else if (marks >= 75) grade = "B";
        else if (marks >= 40) grade = "C";
        else grade = "F";
        //System.out.println("Grade: " + grade);
        return grade;
    }

    public boolean isTopper() {
        if (marks >= 95) {
            System.out.println(marks + " You are Topper");
            return true;
        } else {
            return false;
        }
    }
}