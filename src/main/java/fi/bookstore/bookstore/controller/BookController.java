package fi.bookstore.bookstore.controller;

import fi.bookstore.bookstore.repository.BookRepository;
import fi.bookstore.bookstore.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_create";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/booklist")
    public String showBooks(Model model) {
    model.addAttribute("books", repository.findAll());
    return "booklist";
    }

}