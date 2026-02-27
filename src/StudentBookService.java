import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class StudentBookService {

    private HashMap<Long,Student> students = new HashMap<>();

    public void getBookYear(Path path) {
        getStudentsFromFile(path).stream()
                .peek(Student::printStudent)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Book::getPages).reversed())
                .distinct()
                .filter(Book::isAfterMillenia)
                .limit(3)
                .map(Book::getYear)
                .findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("Book was not found"));
    }

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
        String[] arrayedData = linedData.split(",");
        if (arrayedData.length < 8) {throw new IllegalArgumentException("Invalid data " + linedData);}
        Student student = new Student(Long.parseLong(arrayedData[0]),
                arrayedData[1],
                arrayedData[2],
                arrayedData[3]);

        Book book = new Book(Long.parseLong(arrayedData[4]),
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
