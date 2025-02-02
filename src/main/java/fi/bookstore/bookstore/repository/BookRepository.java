package fi.bookstore.bookstore.repository;

import fi.bookstore.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {}

