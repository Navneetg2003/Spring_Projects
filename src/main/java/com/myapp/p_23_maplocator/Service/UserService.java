package com.myapp.p_23_maplocator.Service;

import com.myapp.p_23_maplocator.Model.Users;
import com.myapp.p_23_maplocator.Repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    private final BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    public void register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public String verify(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            if (authentication.isAuthenticated()) {
                return "success";
            }
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
        return "error";
    }
}
