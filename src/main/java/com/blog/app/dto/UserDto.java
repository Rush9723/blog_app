package com.blog.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;
    @NotEmpty
    @Size(min = 4,message = "Name should have at least 4 characters")
    private  String name;
    @Email(message = "Email address is not valid")
    private  String email;
    @NotEmpty
    private String password;
    private String about;
    @NotEmpty
    @Size(min = 10,max = 10,message = "Phone number should have 10 digits")
    private String phone;
}
