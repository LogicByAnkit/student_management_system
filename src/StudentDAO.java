import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String username = "root";
        String password = "your password";
        return DriverManager.getConnection(url, username, password);
    }

   public boolean addStudent(Student s) {
    String sql = "INSERT INTO student (roll_number, name, marks, grade, course_code) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, s.rollNumber);
        ps.setString(2, s.name);
        ps.setDouble(3, s.marks);
        ps.setString(4, s.calculateGrade());
        ps.setInt(5, s.course.courseCode);
        ps.executeUpdate();
        return true;

    } catch (SQLIntegrityConstraintViolationException e) {
        return false; // roll number already exists
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        CourseDAO courseDAO = new CourseDAO();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course course = courseDAO.getCourseByCode(rs.getInt("course_code"));
                Student s = new Student(rs.getString("name"), rs.getInt("roll_number"), rs.getDouble("marks"), course);
                students.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    // Updates an existing student's marks and grade
public void updateStudent(int rollNumber, double newMarks, String newGrade) {
    String sql = "UPDATE student SET marks = ?, grade = ? WHERE roll_number = ?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setDouble(1, newMarks);
        ps.setString(2, newGrade);
        ps.setInt(3, rollNumber);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Deletes a student by roll number
public void deleteStudent(int rollNumber) {
    String sql = "DELETE FROM student WHERE roll_number = ?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, rollNumber);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Finds one student by roll number
public Student getStudentByRoll(int rollNumber) {
    String sql = "SELECT * FROM student WHERE roll_number = ?";
    CourseDAO courseDAO = new CourseDAO();
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, rollNumber);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Course course = courseDAO.getCourseByCode(rs.getInt("course_code"));
            return new Student(rs.getString("name"), rs.getInt("roll_number"), rs.getDouble("marks"), course);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
