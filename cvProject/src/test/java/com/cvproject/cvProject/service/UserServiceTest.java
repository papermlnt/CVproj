package com.cvproject.cvProject.service;

import com.cvproject.cvProject.model.Role;
import com.cvproject.cvProject.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.cvproject.cvProject.repository.UserRepository;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser() {
        User user = new User();
        user.setFirstName("Hortex juicy");
        user.setPassword("Password");
        user.setLastName("Tymbark");
        user.setId(1L);
        user.setRoles(Role.USER);

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertThat(createdUser.getFirstName()).isSameAs(user.getFirstName());
        verify(userRepository).save(user);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.getAllUsers();
        assertEquals(expected, users);
        verify(userRepository).findAll();
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setFirstName("Animal");
        user.setId(2L);
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        userService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    //@Rollback(value = false)
    void updateUser() {

    }

    @Test
    void findUserById() {
        //Optional<User> user = userRepository.findById(1L);
    }
}