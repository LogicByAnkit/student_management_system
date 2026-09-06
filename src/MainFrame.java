import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;          
import java.util.ArrayList;                          
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centers the window on screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, with spacing

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View All Students");
        JButton searchButton = new JButton("Search Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        viewButton.addActionListener(e -> showAllStudents());     
        addButton.addActionListener(e -> showAddStudentForm());  
        searchButton.addActionListener(e -> showSearchForm());
        updateButton.addActionListener(ec -> showUpdateForm());  
        deleteButton.addActionListener(ec -> showDeleteForm()); //added
        add(panel);
    }
    private void showAllStudents() {                        
    StudentDAO studentDAO = new StudentDAO();
    ArrayList<Student> students = studentDAO.getAllStudents();

    String[] columns = {"Roll Number", "Name", "Marks", "Grade", "Course"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);

    for (Student s : students) {
        model.addRow(new Object[]{
            s.rollNumber, s.name, s.marks, s.calculateGrade(), s.course.courseName
        });
    }

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(450, 200));

    JOptionPane.showMessageDialog(this, scrollPane, "All Students", JOptionPane.PLAIN_MESSAGE);
}
private void showAddStudentForm() {                         
    JTextField nameField = new JTextField();
    JTextField rollField = new JTextField();
    JTextField marksField = new JTextField();
    JTextField courseCodeField = new JTextField();

    Object[] fields = {
        "Name:", nameField,
        "Roll Number:", rollField,
        "Marks:", marksField,
        "Course Code:", courseCodeField
    };

    int result = JOptionPane.showConfirmDialog(this, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        try {
            String name = nameField.getText();
            int roll = Integer.parseInt(rollField.getText());
            double marks = Double.parseDouble(marksField.getText());
            int courseCode = Integer.parseInt(courseCodeField.getText());

            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getCourseByCode(courseCode);

            if (course == null) {
                JOptionPane.showMessageDialog(this, "No course found with that code.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

           StudentDAO studentDAO = new StudentDAO();
           boolean success = studentDAO.addStudent(new Student(name, roll, marks, course));

          if (success) {
           JOptionPane.showMessageDialog(this, "Student added successfully!");
           } else {
          JOptionPane.showMessageDialog(this, "A student with that roll number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
       }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Roll, Marks, and Course Code.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
private void showSearchForm() {                         
    String input = JOptionPane.showInputDialog(this, "Enter Roll Number to search:");

    if (input != null && !input.trim().isEmpty()) {
        try {
            int roll = Integer.parseInt(input);
            StudentDAO studentDAO = new StudentDAO();
            Student s = studentDAO.getStudentByRoll(roll);

            if (s != null) {
                String message = "Name: " + s.name + "\n"
                        + "Roll Number: " + s.rollNumber + "\n"
                        + "Marks: " + s.marks + "\n"
                        + "Grade: " + s.calculateGrade() + "\n"
                        + "Course: " + s.course.courseName;
                JOptionPane.showMessageDialog(this, message, "Student Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that roll number.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
private void showUpdateForm() {                   
    String input = JOptionPane.showInputDialog(this, "Enter Roll Number to update:");

    if (input != null && !input.trim().isEmpty()) {
        try {
            int roll = Integer.parseInt(input);
            StudentDAO studentDAO = new StudentDAO();
            Student s = studentDAO.getStudentByRoll(roll);

            if (s == null) {
                JOptionPane.showMessageDialog(this, "No student found with that roll number.", "Not Found", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JTextField marksField = new JTextField(String.valueOf(s.marks));
            Object[] fields = {
                "Current Name: " + s.name,
                "New Marks:", marksField
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Update Student", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                double newMarks = Double.parseDouble(marksField.getText());
                String newGrade = new Student(s.name, s.rollNumber, newMarks, s.course).calculateGrade();

                studentDAO.updateStudent(roll, newMarks, newGrade);
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
private void showDeleteForm() {                            //added
    String input = JOptionPane.showInputDialog(this, "Enter Roll Number to delete:");

    if (input != null && !input.trim().isEmpty()) {
        try {
            int roll = Integer.parseInt(input);
            StudentDAO studentDAO = new StudentDAO();
            Student s = studentDAO.getStudentByRoll(roll);

            if (s == null) {
                JOptionPane.showMessageDialog(this, "No student found with that roll number.", "Not Found", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete " + s.name + " (Roll " + s.rollNumber + ")?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                studentDAO.deleteStudent(roll);
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}