public class GraduateStudent extends Student{
    String thesisTitle;
    
    public GraduateStudent(String name, int roll_number, double marks, Course course,String thesisTitle){
        super(name, roll_number, marks, course);
        this.thesisTitle = thesisTitle;
    }
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Thesis title :"+thesisTitle);
    }
}
