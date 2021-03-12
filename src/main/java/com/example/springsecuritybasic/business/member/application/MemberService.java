package com.example.springsecuritybasic.business.member.application;

import com.example.springsecuritybasic.business.member.domain.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Long save(MemberDTO memberDTO);
}
