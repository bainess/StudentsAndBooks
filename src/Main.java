import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentBookService sbs = new StudentBookService();
        sbs.getBookYear(Path.of("resources/csv"));
    }
}
