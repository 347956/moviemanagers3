package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
