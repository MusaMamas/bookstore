package fi.bookstore.bookstore.controller;

import fi.bookstore.bookstore.repository.BookRepository;
import fi.bookstore.bookstore.model.Book;
import fi.bookstore.bookstore.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {
    private final BookRepository repository;

    private final CategoryRepository categoryRepository;
    
    public BookController(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "add_create";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        // Hakee kirjan tietokannasta ja lisää sen malliin
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
    
        // Lisää kategoriat dropdown-listalle
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit_book";
    }
    
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Book updatedBook) {
        // Päivittää olemassa olevan kirjan
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    
        // Päivitetään kirjan tiedot
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setIsbn(updatedBook.getIsbn());
        book.setPrice(updatedBook.getPrice());
        book.setCategory(updatedBook.getCategory());
    
        repository.save(book);
        return "redirect:/booklist";
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