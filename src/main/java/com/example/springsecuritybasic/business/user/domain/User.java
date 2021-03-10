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
    @Id
    @Column(name="id")
    private String id;
    @Column(name="pwd")
    private String pwd;
    @Column(name = "auth")
    private String auth;
}
