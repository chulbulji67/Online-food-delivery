package com.restaurant;

import com.restaurant.dto.UserDto;
import com.restaurant.model.User;
import com.restaurant.service.user.UserService;
import com.restaurant.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserController {
    @Autowired
    private UserService userService;

    @Test
    public void addUser(){
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@example.com");
        user.setRole("ROLE_USER");
        UserDto userDto = userService.registerUser(user);
        log.info("user id, userName {} {}",userDto.getId(), userDto.getUsername());
    }
}
