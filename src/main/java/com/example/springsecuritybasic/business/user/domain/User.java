package com.example.springsecuritybasic.business.user.domain;


import lombok.*;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String password;
    private String email;
    private String nickname;
    private List<Role> roles;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
