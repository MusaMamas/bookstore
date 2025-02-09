package fi.bookstore.bookstore.repository;

import fi.bookstore.bookstore.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {}

