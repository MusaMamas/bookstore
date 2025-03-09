package fi.bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.bookstore.bookstore.model.Category;
import fi.bookstore.bookstore.repository.CategoryRepository;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreateAndFindCategory() {
        Category category = new Category();
        category.setName("Science Fiction");

        categoryRepository.save(category);
        Optional<Category> foundCategory = categoryRepository.findById(category.getId());

        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get().getName()).isEqualTo("Science Fiction");
    }

    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setName("Fantasy");

        category = categoryRepository.save(category);
        Long categoryId = category.getId();

        categoryRepository.deleteById(categoryId);

        Optional<Category> deletedCategory = categoryRepository.findById(categoryId);
        assertThat(deletedCategory).isEmpty();
    }
}

