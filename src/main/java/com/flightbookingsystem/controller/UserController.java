package com.flightbookingsystem.controller;

import com.flightbookingsystem.dto.UserDto;
import com.flightbookingsystem.dto.UserInputDto;
import com.flightbookingsystem.model.User;
import com.flightbookingsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
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

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers()
                .stream().map(user -> modelMapper
                        .map(user, UserDto.class))
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping(value = "/load")
    public ResponseEntity<UserDto> loadUserByUsername(@RequestParam("username") String userName){
        UserDetails user = userService.loadUserByUsername(userName);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("userId") Integer userId){
        User user = userService.getUserById(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserInputDto userInputDto){
        User entity = modelMapper.map(userInputDto, User.class);
        entity = userService.addUser(entity);
        UserDto responseDto = modelMapper.map(entity, UserDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/disable/{userId}")
    public ResponseEntity<UserDto> disableUser(@PathVariable("userId") Integer userId) {
        User disableUser = userService.disableUser(userId);
        UserDto disabledUserDto = modelMapper.map(disableUser, UserDto.class);
        return ResponseEntity.ok(disabledUserDto);
    }

    @PatchMapping(value = "/enable/{userId}")
    public ResponseEntity<UserDto> enableUser(@PathVariable("userId") Integer userId) {
        User enabledUser = userService.enableUser(userId);
        UserDto enabledUserDto = modelMapper.map(enabledUser, UserDto.class);
        return ResponseEntity.ok(enabledUserDto);
    }

    @RequestMapping(value = "/{userId}",  method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer userId) {
        User deletedUser = userService.deleteUser(userId);
        UserDto userDto = modelMapper.map(deletedUser, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Boolean> authenticateUser(@RequestParam("username") String userName){
        UserDetails fetchedUser =  userService.loadUserByUsername(userName);
        return ResponseEntity.ok(this.userService.isUserAuthenticated(userName));
    }

}
