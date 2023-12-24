package com.flightbookingsystem.service;

import com.flightbookingsystem.error.InvalidInputException;
import com.flightbookingsystem.model.User;
import com.flightbookingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users
    public List<User> getAllUsers(){
        List<User> users = userRepository.getAllUsers();
        return users;
    }

    // get user by userId
    public User getUserById(Integer userId){
        return userRepository.getUserById(userId)
                .orElseThrow(()->new InvalidInputException(
                        String.format("User with id: %d does not exist.", userId)
                ));
    }

    // save user
    @Transactional
    public User addUser(User user){
        return userRepository.save(user);
    }

    // edit user
    @Transactional
    public User editUser(Integer userId, User updatedUser){
        Optional<User> result = Optional.of(getUserById(userId));
        User user;

        if(result.isPresent()){
            user = result.get();

            user.setUserName(updatedUser.getUserName());
            user.setRole(updatedUser.getRole());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setIsBanned(updatedUser.getIsBanned());
        } else {
            throw  new IllegalArgumentException("No existing users with id " + userId);
        }

        return userRepository.save(user);
    }

    // delete user
    @Transactional
    public User deleteUser(Integer userId){
        Optional<User> result = userRepository.getUserById(userId);
        if(result.isEmpty()) {
            throw new InvalidInputException(String.format("Object with id: %d cannot be found", userId));
        }

        User user = result.get();
        userRepository.delete(user);
        return user;
    }
}
