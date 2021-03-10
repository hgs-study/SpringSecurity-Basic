package com.example.springsecuritybasic.common.engine.security.config;

import com.example.springsecuritybasic.business.user.domain.User;
import com.example.springsecuritybasic.business.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetUser implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User saveUser = new User();
        saveUser.setId("test");
        saveUser.setPwd(passwordEncoder.encode("password"));
        saveUser.setAuth("USER");
        userRepository.save(saveUser);
    }
}
