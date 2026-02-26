import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class StudentBookService {
  //  private List<Student> studentList = new ArrayList<>();

    private HashMap<Long,Student> students = new HashMap<>();


    public List<Student> getStudentsFromFile(Path path) {
        try {
            readFromFile(path);
        } catch (IOException e) {
            e.getMessage();
        }
        return List.copyOf(students.values());
    }

    private void readFromFile(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            lines.map(this::getDataFromLine)
                    .forEach(this::addStudentOrBook);
        }
    }

    private  Student getDataFromLine(String linedData) {
        System.out.println(linedData);
        String[] arrayedData = linedData.split(",");
        System.out.println(arrayedData);
        if (arrayedData.length < 8) {throw new IllegalArgumentException("Invalid data " + linedData);}
        Student student = new Student(Long.parseLong(arrayedData[0]),
                arrayedData[1],
                arrayedData[2],
                arrayedData[3]);

        Book book = new Book(Integer.parseInt(arrayedData[4]),
                arrayedData[5],
                Integer.parseInt(arrayedData[6]),
                Integer.parseInt(arrayedData[7]));
        student.addBook(book);
        return student;
    }

    private void addStudentOrBook(Student student) {
        Student s = students.get(student.getId());
        if (s != null) {
            s.getBooks().add(student.getBooks().getFirst());
        } else students.put(student.getId(), student);
    }
}
