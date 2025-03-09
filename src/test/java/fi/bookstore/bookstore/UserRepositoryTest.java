package fi.bookstore.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.bookstore.bookstore.model.User;
import fi.bookstore.bookstore.repository.UserRepository;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndFindUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setRole("USER");

        userRepository.save(user);
        User foundUser = userRepository.findByUsername("testuser");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setUsername("deleteuser");
        user.setPassword("password");
        user.setEmail("delete@example.com");
        user.setRole("USER");

        user = userRepository.save(user);
        Long userId = user.getId();

        userRepository.deleteById(userId);

        Optional<User> deletedUser = userRepository.findById(userId);
        assertThat(deletedUser).isEmpty();
    }
}

