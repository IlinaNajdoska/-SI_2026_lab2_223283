import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    @Test
    public void searchBookEveryStatementTest() {
        Library library = new Library();

        Book book1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        Book book2 = new Book("Effective Java", "Joshua Bloch", "Programming");
        book2.setBorrowed(true);

        library.addBook(book1);
        library.addBook(book2);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            library.searchBookByTitle("");
        });
        assertEquals("Invalid title", ex.getMessage());

        List<Book> foundBooks = library.searchBookByTitle("Clean Code");
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
        assertEquals("Clean Code", foundBooks.get(0).getTitle());

        List<Book> noResults = library.searchBookByTitle("Effective Java");
        assertNull(noResults);
    }

    @Test
    public void borrowBookEveryBranchTest() {
        Library library = new Library();

        Book book1 = new Book("Design Patterns", "Gang of Four", "Programming");
        Book book2 = new Book("Clean Architecture", "Robert C. Martin", "Programming");
        book2.setBorrowed(true);

        library.addBook(book1);
        library.addBook(book2);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "Author");
        });
        assertEquals("Invalid search query", ex1.getMessage());

        library.borrowBook("Design Patterns", "Gang of Four");

        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Clean Architecture", "Robert C. Martin");
        });
        assertEquals("Book is already borrowed.", ex2.getMessage());

        RuntimeException ex3 = assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Non Existent", "Unknown Author");
        });
        assertEquals("Book not found", ex3.getMessage());
    }
}