package fi.bookstore.bookstore;

import fi.bookstore.bookstore.model.Book;
import fi.bookstore.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            repository.save(new Book("Spring Boot Guide", "John Doe", 2021, "123456789", 29.99));
            repository.save(new Book("Java Fundamentals", "Jane Smith", 2019, "987654321", 39.99));
            System.out.println("Books inserted successfully!");
        };
    }
}
