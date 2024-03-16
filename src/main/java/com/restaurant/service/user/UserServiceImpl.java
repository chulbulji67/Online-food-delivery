package com.restaurant.service.user;

import com.restaurant.config.JwtProvider;
import com.restaurant.dto.UserDto;
import com.restaurant.exception.userexception.UserAlreadyExist;
import com.restaurant.exception.userexception.UserNotFoundException;
import com.restaurant.model.User;
import com.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDto registerUser(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser != null){
            throw new UserAlreadyExist("User Already Exist with this email");
        }

        existingUser = userRepo.findByUsername(user.getUsername());
        if(existingUser != null){
            throw new UserAlreadyExist("User Already Exist with this UserName");
        }

        return mapUserToUserDto(userRepo.save(user));
    }

    @Override
    public User getUserProfile(String jwt) {
        String email = JwtProvider.getEmailFromjwtToken(jwt);

        return userRepo.findByEmail(email);

    }


    @Override
    public UserDto getUserById(long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found with given Id"));
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(this::mapUserToUserDto).toList();
    }

    @Override
    public UserDto updateUserById(long id, User user) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found with given Id"));
        if(user.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }
        if(user.getUsername() != null){
            existingUser.setUsername(user.getUsername());
        }
        if(user.getPassword() != null){
            existingUser.setPassword(user.getPassword());
        }
        return mapUserToUserDto(userRepo.save(existingUser));
    }

    @Override
    public String deleteUserById(long id) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found with given Id"));
        userRepo.deleteById(id);
        return "User Deleted SuccessFully";
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }
}
