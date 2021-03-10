package com.example.springsecuritybasic.common.engine.security.config;

import com.example.springsecuritybasic.business.user.domain.User;
import com.example.springsecuritybasic.business.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    private final CustomUserDetails customUserDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUsername(username))
                                    .orElseThrow(()-> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
        customUserDetails.setId(user.getUsername());
        customUserDetails.setPwd(user.getPassword());
        customUserDetails.setAuth(user.getAuthority());

        return customUserDetails;
    }
}
