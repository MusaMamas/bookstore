package fi.bookstore.bookstore.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import fi.bookstore.bookstore.repository.BookRepository;
import fi.bookstore.bookstore.model.Book;

import java.util.List;

@RestController
public class BookRestAllController {

    private final BookRepository bookRepository;

    // Constructor Injection
    public BookRestAllController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books (JSON)
    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
}

