package com.example.springsecuritybasic.business.member.domain;

import java.time.LocalDateTime;

public class MemberDTO {
    private Long id;

    private String name;

    private String account;

    private String password;

    private LocalDateTime lastAccessDt;

    private LocalDateTime regDt;

    public Member toEntity() {
        return new Member(id, name, account, password);
    }
}
