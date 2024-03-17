package com.restaurant.service.user;


import com.restaurant.exception.userexception.UserNotFoundException;
import com.restaurant.model.User;
import com.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null){
            throw new UserNotFoundException("User NOt found with email"+username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        String role = user.getRole();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });
        System.out.println(authorities.get(0).getAuthority());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
    }
}

