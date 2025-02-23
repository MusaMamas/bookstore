package fi.bookstore.bookstore.controller;

import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import fi.bookstore.bookstore.repository.BookRepository;
import fi.bookstore.bookstore.model.Book;

@RestController
public class BookRestIdController {

    private final BookRepository bookRepository;

    public BookRestIdController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get book by ID
    @GetMapping("/api/books/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }
}

