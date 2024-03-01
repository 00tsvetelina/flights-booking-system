package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserById(Integer userId);

    Optional<User> findByuserName(String username);

    List<User> findAll();
}
