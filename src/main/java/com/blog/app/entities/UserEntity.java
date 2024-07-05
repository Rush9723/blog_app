package com.blog.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    private String about;

    @Column(name = "phone",nullable = false)
    private String phone;
}
