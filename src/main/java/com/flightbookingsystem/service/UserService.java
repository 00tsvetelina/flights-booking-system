package com.flightbookingsystem.service;

import com.flightbookingsystem.dto.MyUserPrincipal;
import com.flightbookingsystem.model.User;
import com.flightbookingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // get all users
    public List<User> getAllUsers(){
        List<User> users = userRepository.getAllUsers();
        return users;
    }

    // get user by userId
    public User getUserById(Integer userId){
        return userRepository.getUserById(userId)
                .orElseThrow(()->new IllegalArgumentException(
                        String.format("User with id: %d does not exist.", userId)
                ));
    }

    // get user by email

    // save user
    @Transactional
    public User addUser(User user){
        if(userRepository.findByuserName(user.getUserName()).isPresent()){
            throw new IllegalArgumentException("User with this username already exists");
        }
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

    }

    // edit user
    @Transactional
    public User editUser(Integer userId, User updatedUser){
        Optional<User> result = Optional.of(getUserById(userId));
        User user;

        if(result.isPresent()){
            user = result.get();

            user.setFirstAndLastNames(updatedUser.getFirstAndLastNames());
            user.setUserName(updatedUser.getUserName());
            user.setRoles(updatedUser.getRoles());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setIsEnabled(updatedUser.getIsEnabled());
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
            throw new IllegalArgumentException(String.format("Object with id: %d cannot be found", userId));
        }

        User user = result.get();
        userRepository.delete(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByuserName(username) // find user and db
                .map(MyUserPrincipal::new) // if found, wrap the returned user instance in a MyUserPrincipal instance
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username: %s cannot be found", username))
                        // or else throw new UNFE and format it into string value
                );
    }
}
