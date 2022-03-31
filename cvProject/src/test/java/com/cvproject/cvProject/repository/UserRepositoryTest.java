package com.cvproject.cvProject.repository;

import com.cvproject.cvProject.model.Role;
import com.cvproject.cvProject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Test
    public void saveUserTest() {
        User user = User.builder()
                .firstName("Adrian")
                .lastName("Ramirez")
                .email("adrian@gmail.com")
                .password("password")
                .build();
        repo.save(user);
        assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    void itShouldFindUserByEmail() {
        // given
        String user1 = "User1";
        User user = new User(
                1L,
                "firstname",
                user1,
                "lastname",
                "user@mail.com",
                Role.USER
        );
        repo.save(user);

        // when
        Optional<User> userByEmail = repo.findByEmail(user1);


        // then
        assertThat(userByEmail).isPresent();
    }

    @Test
    public void getUserTest(){
        User user = repo.findById(1L).get();
        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void getAllUsers() {
        List<User> users = repo.findAll();
        assertThat(users.size()).isGreaterThan(0);
    }

}