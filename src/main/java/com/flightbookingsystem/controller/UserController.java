package com.flightbookingsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.flightbookingsystem.dto.TicketDto;
import com.flightbookingsystem.dto.UserDto;
import com.flightbookingsystem.dto.UserInputDto;
import com.flightbookingsystem.model.User;
import com.flightbookingsystem.service.UserService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // fetch user by  username
    @GetMapping(value = "/load")
    // http://localhost:8080/api/admin/users/load?username=userName
    public ResponseEntity<UserDto> loadUserByUsername(@RequestParam("username") String userName){
        UserDetails user = userService.loadUserByUsername(userName);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    // fetch user by userId
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("userId") Integer userId){
        User user = userService.getUserById(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    //create a user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserInputDto userInputDto){
        User entity = modelMapper.map(userInputDto, User.class);
        entity = userService.addUser(entity);
        UserDto responseDto = modelMapper.map(entity, UserDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // edit user
   // TODO make ban user
//    @PutMapping(value = "/{userId}")
//    public ResponseEntity<UserDto> editUser(@PathVariable ("userId") Integer userId,
//                                            @Valid @RequestBody UserDto userDto){
//        User entity = modelMapper.map(userDto, User.class);
//        User updatedUser = userService.editUser(userId, entity);
//        UserDto responseDto = modelMapper.map(updatedUser, UserDto.class);
//        return ResponseEntity.ok(responseDto);
//    }

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

    // delete user
    @RequestMapping(value = "/{userId}",  method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer userId) {
        User deletedUser = userService.deleteUser(userId);
        UserDto userDto = modelMapper.map(deletedUser, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    // fetch user by  username
    @GetMapping(value = "/login")
    public ResponseEntity<Boolean> authenticateUser(@RequestParam("username") String userName){
        UserDetails fetchedUser =  userService.loadUserByUsername(userName);

        return ResponseEntity.ok(this.userService.isUserAuthenticated(userName));
    }

}
