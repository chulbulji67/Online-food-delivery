package com.restaurant.service.user;

import com.restaurant.dto.UserDto;
import com.restaurant.model.User;

import java.util.List;

public interface UserService {

    public UserDto registerUser(User user);

    public UserDto getUserById(long id);

    public List<UserDto> getAllUsers();

    public UserDto updateUserById(long id, User user);

    public String deleteUserById(long id);

    public User getUserProfile(String jwt);
}
