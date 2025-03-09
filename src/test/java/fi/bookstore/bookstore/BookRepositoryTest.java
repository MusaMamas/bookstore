package fi.bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.bookstore.bookstore.model.Book;
import fi.bookstore.bookstore.repository.BookRepository;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateAndFindBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublicationYear(2022);
        book.setIsbn("123-456-789");
        book.setPrice(19.99);

        bookRepository.save(book);
        
        List<Book> books = bookRepository.findByTitle("Test Book");
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getAuthor()).isEqualTo("Test Author");
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setTitle("Delete Test");
        book.setAuthor("Author");
        book.setPublicationYear(2022);
        book.setIsbn("789-456-123");
        book.setPrice(29.99);

        book = bookRepository.save(book);
        Long bookId = book.getId();
        
        bookRepository.deleteById(bookId);
        
        Optional<Book> deletedBook = bookRepository.findById(bookId);
        assertThat(deletedBook).isEmpty();
    }
}

