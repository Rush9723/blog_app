package com.blog.app.services;

import com.blog.app.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer id);

    UserDto getUser(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
