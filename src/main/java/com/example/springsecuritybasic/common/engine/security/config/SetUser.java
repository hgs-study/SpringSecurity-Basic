package com.example.springsecuritybasic.common.engine.security.config;

import com.example.springsecuritybasic.business.member.application.MemberRepository;
import com.example.springsecuritybasic.business.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetUser implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        Member member = new Member();
        //saveUser.setPwd("test");,
        member.setName("홍길동");
        member.setPassword(passwordEncoder.encode("test"));
        member.setAccount("test");
        memberRepository.save(member);
    }
}
