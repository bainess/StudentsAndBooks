import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private String surname;
    private String course;
    List<Book> books = new ArrayList<>();

    public Student(Long id, String name, String surname, String course) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course = course;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Long getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Course: " + course + "\n";
    }
}
