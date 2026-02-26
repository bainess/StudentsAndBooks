import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentBookService sbs = new StudentBookService();
        List<Student> students = sbs.getStudentsFromFile(Path.of("resources/csv"));

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
