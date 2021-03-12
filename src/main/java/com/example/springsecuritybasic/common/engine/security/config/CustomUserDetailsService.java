//package com.example.springsecuritybasic.common.engine.security.config;
//
//import com.example.springsecuritybasic.business.member.domain.Member;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class CustomUserDetailsService  implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    private final CustomUserDetails customUserDetails;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member user = Optional.ofNullable(userRepository.findById(username))
//                                    .orElseThrow(()-> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
//        customUserDetails.setId(user.getId());
//        customUserDetails.setPwd(user.getPwd());
//        customUserDetails.setAuth(user.getAuth());
//        log.info("user :"+user);
//        log.info("user.getId() :"+user.getId());
//        log.info("user.getPwd() :"+user.getPwd());
//        log.info("user.getAuth() :"+user.getAuth());
//        log.info("username :"+username);
//
//        return customUserDetails;
//    }
//}
