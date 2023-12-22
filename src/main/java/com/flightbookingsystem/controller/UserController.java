package com.flightbookingsystem.controller;

import com.flightbookingsystem.dto.UserDto;
import com.flightbookingsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin/users")
public class UserController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // fetch all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers()
                .stream().map(user -> modelMapper
                        .map(user, UserDto.class))
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    // fetch user by userId

    //create a user

    // edit user

    // delete user
}
