package com.blog.app.utils;

import com.blog.app.dto.UserDto;
import com.blog.app.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public UserEntity userDtoToUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());
        return userEntity;
    }

    public UserDto userEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPhone(userEntity.getPhone());
        userDto.setPassword(userEntity.getPassword());
        userDto.setAbout(userEntity.getAbout());
        return userDto;
    }
}
