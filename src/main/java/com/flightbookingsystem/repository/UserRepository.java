package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // find user by userId
    Optional<User> getUserById(Integer userId);

    // select all users from db
    @Query(value = "select  * from user", nativeQuery = true)
    List<User> getAllUsers();

}
