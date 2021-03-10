package com.example.springsecuritybasic.business.user.domain;


import lombok.*;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    @Column(name="seq")
    private Long id;
    @Column(name="username")
    private String username;
    private String password;
    @Column(name = "authority")
    private String authority;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
