package com.blog.app.controllers;

import com.blog.app.dto.UserDto;
import com.blog.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class UserController {
    @Autowired
    private UserService userService;

    //POST-create user
    @PostMapping("/post")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        UserDto updatedUserDto = this.userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.CREATED);
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        UserDto userDto = this.userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = this.userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(Map.of("Message","User deleted successfully","Success",true),HttpStatus.OK);
    }


}