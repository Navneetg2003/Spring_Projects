package com.myapp.p_23_maplocator.Controller;

import com.myapp.p_23_maplocator.Model.Users;
import com.myapp.p_23_maplocator.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/perform_register")
    public String register(@ModelAttribute Users user){
        userService.register(user);
        return "login";
    }
}
