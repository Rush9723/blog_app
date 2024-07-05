package com.blog.app.services.impl;


import com.blog.app.dto.UserDto;
import com.blog.app.entities.UserEntity;
import com.blog.app.exceptions.CustomDataIntegrityViolationException;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.UserService;
import com.blog.app.utils.UserModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserModelMapper userModelMapper;
    @Autowired
    private CustomDataIntegrityViolationException customDataIntegrityViolationException;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntityEntity2 = this.modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUserEntity = this.userRepository.save(userEntityEntity2);
        return this.modelMapper.map(savedUserEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        if (userDto.getName() != null) {
            userEntity.setName(userDto.getName());
        } else {
            customDataIntegrityViolationException.dataIntegrityViolationException(new DataIntegrityViolationException("Name cannot be null"));
        }
        if (userDto.getEmail() != null) {
            userEntity.setEmail(userDto.getEmail());
        } else {
            customDataIntegrityViolationException.dataIntegrityViolationException(new DataIntegrityViolationException("Email cannot be null"));
        }

        if (userDto.getPhone() != null) {
            userEntity.setPhone(userDto.getPhone());
        } else {
            customDataIntegrityViolationException.dataIntegrityViolationException(new DataIntegrityViolationException("Phone cannot be null"));
        }
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());
        UserEntity updatedUserEntity = this.userRepository.save(userEntity);
        return this.modelMapper.map(updatedUserEntity,UserDto.class);
    }

    @Override
    public UserDto getUser(Integer userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.modelMapper.map(userEntity,UserDto.class);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntity = this.userRepository.findAll();
        return userEntity.stream().map(user->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(userEntity);
    }


}
