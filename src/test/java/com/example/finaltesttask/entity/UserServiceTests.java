package com.example.finaltesttask.entity;

import com.example.finaltesttask.FinalTestTaskApplication;
import com.example.finaltesttask.service.UserService;
import com.example.finaltesttask.service.exception.UserNotFoundException;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ContextConfiguration(classes = {
        FinalTestTaskApplication.class
})
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    @SneakyThrows
    public void findUserByIdTest() {
        Assert.assertThrows(UserNotFoundException.class, () -> userService.getById(1000L));
        Assert.assertNotNull(userService.getById(1000L));
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setAge(20);
        user.setLastName("Dunny");
        user.setFirstName("Bobby");
        userService.createUser(user);
    }
}