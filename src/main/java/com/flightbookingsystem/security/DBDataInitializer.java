package com.flightbookingsystem.security;

import com.flightbookingsystem.model.User;
import com.flightbookingsystem.repository.UserRepository;
import com.flightbookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DBDataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

//        User user = new User();
//        user.setFirstAndLastNames("admin");
//        user.setUserName("my_admin");
//        user.setRoles(List.of("admin"));
//        user.setEmail("my_admin@fbs.mgt");
//        user.setPassword("123456");
//        user.setIsEnabled(true);
//
//        this.userService.addUser(user);
//        this.userService.deleteUser(29);
//        System.out.println("admin: " + abc);
//        System.out.println("admin: " + abc.getRoles());


    }
}
