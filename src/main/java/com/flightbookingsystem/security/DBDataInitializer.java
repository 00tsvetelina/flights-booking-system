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

//        User abc = new User();
//        abc.setUserName("abc");
//        abc.setRoles(List.of("admin"));
//        abc.setEmail("admin@ab.cd");
//        abc.setPassword("123456");
//        abc.setIsEnabled(true);

//        this.userService.addUser(abc);
//        this.userService.deleteUser(25);
//        System.out.println("admin: " + abc);
//        System.out.println("admin: " + abc.getRoles());


    }
}
