package fi.bookstore.bookstore;

import fi.bookstore.bookstore.model.Book;
import fi.bookstore.bookstore.model.Category;
import fi.bookstore.bookstore.repository.BookRepository;
import fi.bookstore.bookstore.repository.CategoryRepository;
import fi.bookstore.bookstore.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import fi.bookstore.bookstore.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
public CommandLineRunner addUsers(UserRepository userRepository) {
    return (args) -> {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setRole("ADMIN");
            admin.setEmail("admin@bookstore.com");
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            user.setRole("USER");
            user.setEmail("user@bookstore.com");
            userRepository.save(user);
        }

        System.out.println("Users added to the database!");
    };
}


    @Bean
public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
    return (args) -> {
        // Luo kategoriat
        Category fiction = new Category();
        fiction.setName("Fiction");
        categoryRepository.save(fiction);

        Category nonFiction = new Category();
        nonFiction.setName("Non-Fiction");
        categoryRepository.save(nonFiction);

        Category mystery = new Category();
        mystery.setName("Mystery");
        categoryRepository.save(mystery);

        Category science = new Category();
        science.setName("Science");
        categoryRepository.save(science);

        Category biography = new Category();
        biography.setName("Biography");
        categoryRepository.save(biography);

        System.out.println("Categories added!");

        Book book1 = new Book();
        book1.setTitle("Alice in Wonderland");
        book1.setAuthor("Lewis Carroll");
        book1.setPublicationYear(2021);
        book1.setIsbn("123456789");
        book1.setPrice(29.99);
        book1.setCategory(fiction);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Pele - The Autobiography");
        book2.setAuthor("Pele");
        book2.setPublicationYear(2019);
        book2.setIsbn("987654321");
        book2.setPrice(39.99);
        book2.setCategory(nonFiction);
        bookRepository.save(book2);

        System.out.println("Books added!");
        };
    }

}
