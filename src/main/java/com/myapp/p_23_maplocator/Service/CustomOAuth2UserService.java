package com.myapp.p_23_maplocator.Service;

import com.myapp.p_23_maplocator.Model.Users;
import com.myapp.p_23_maplocator.Repository.UsersRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepository userRepository;

    public CustomOAuth2UserService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");

        Optional<Users> existingUser = userRepository.findByEmailId(email);

        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            user.setUsername(username);
            System.out.println(user);
            userRepository.save(user);
        } else {
            Users newUser = new Users();
            newUser.setEmailId(email);
            newUser.setUsername(username);
            System.out.println(newUser);
            userRepository.save(newUser);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new OAuth2UserAuthority(oAuth2User.getAttributes())),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}
