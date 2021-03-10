package com.example.springsecuritybasic.common.engine.security.config;

import com.example.springsecuritybasic.business.user.domain.User;
import com.example.springsecuritybasic.business.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("customAuthProvider")
//@RequiredArgsConstructor
@Slf4j
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    //로그인 요청시 AuthenticationProvider로 요청이 전달되며 authenticate()메소드가 호출되며 인증받기 전의
    //사용자 정보가 authentication 객체에 담긴다.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{

        String id = authentication.getName();
        String pwd = (String) authentication.getCredentials();

        CustomUserDetails user =(CustomUserDetails) customUserDetailsService.loadUserByUsername(id);
        if(!passwordEncoder.matches(pwd, user.getPassword()))
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        log.info("id:"+id);
        log.info("pwd:"+pwd);
        log.info("user.getPassword():"+user.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getAuth()));

        return new UsernamePasswordAuthenticationToken(id,pwd,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
