import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private int pages;
    private int year;

    public Book(Long id, String title, int pages, int year) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.year = year;
    }

    public boolean isAfterMillenia() {
        return year > 2000;
    }

    public Long getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book id: " + id + ". " + title + ". Pages: " + pages + ". Year: " + year + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                pages == book.pages &&
                year == book.year &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id & 11, title, pages * 11, year * 11);
    }
}
