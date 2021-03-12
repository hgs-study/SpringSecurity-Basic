package com.example.springsecuritybasic.business.member.application;

import com.example.springsecuritybasic.business.member.domain.Member;
import com.example.springsecuritybasic.business.member.domain.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;

    //loadUserByUsernae 메소드는 입력한 account를 이용해 회원을 조회합니다.
    // 그리고 회원 정보와 권한 정보가 담긴 User 클래스를 반환합니다.
    // (User 클래스는 UserDetails 인터페이스를 구현하고 있습니다.)
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepository.findByAccount(account);
        Member memberEntity = memberEntityWrapper.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new User(memberEntity.getAccount(), memberEntity.getPassword(), authorities);
    }

    //비밀번호 인증은 SpringSecurity의 AuthenticationProvider 객체에서 진행합니다.
    // 직접 커스텀해서 비밀번호 인증 로직을 구현할 수 있지만, 이번에는 기본적으로 지원하는 AuthenticationProvide를 사용하겠습니다.
    @Override
    @Transactional
    public Long save(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        member.setLastAccessDt(LocalDateTime.now());
        member.setRegDt(LocalDateTime.now());

        //비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getId();
    }

}
