package com.flightbookingsystem.service;

import com.flightbookingsystem.model.MyUserPrincipal;
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

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer userId){
        return userRepository.getUserById(userId)
                .orElseThrow(()->new IllegalArgumentException(
                        String.format("User with id: %d does not exist.", userId)
                ));
    }


    @Transactional
    public User addUser(User user){
        if(userRepository.findByuserName(user.getUserName()).isPresent()){
            throw new IllegalArgumentException("User with this username already exists");
        }
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

    }

    @Transactional
    public User disableUser(Integer userId) {
        User disabledUser = userRepository.getUserById(userId)
                .orElseThrow(()->new IllegalArgumentException(
                        String.format("User with id: %d does not exist.", userId)
                ));
        disabledUser.setIsEnabled(Boolean.FALSE);
        return userRepository.save(disabledUser);

    }

    @Transactional
    public User enableUser(Integer userId) {
        User disabledUser = userRepository.getUserById(userId)
                .orElseThrow(()->new IllegalArgumentException(
                        String.format("User with id: %d does not exist.", userId)
                ));
        disabledUser.setIsEnabled(Boolean.TRUE);
        return userRepository.save(disabledUser);

    }

    @Transactional
    public User deleteUser(Integer userId){
        User user = getUserById(userId);
        userRepository.delete(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByuserName(username)
                .map(MyUserPrincipal::new)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username: %s cannot be found", username))
                );
    }

    public boolean isUserAuthenticated(String username) {
        try {
            this.loadUserByUsername(username);
            return true;
        } catch (UsernameNotFoundException ex) {
            return false;
        }
    }

}
