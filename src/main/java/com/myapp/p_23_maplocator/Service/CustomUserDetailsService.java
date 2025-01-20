package com.myapp.p_23_maplocator.Service;

import com.myapp.p_23_maplocator.Model.Users;
import com.myapp.p_23_maplocator.Repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> userOptional = usersRepository.findByUsername(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Users user = userOptional.get();
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
