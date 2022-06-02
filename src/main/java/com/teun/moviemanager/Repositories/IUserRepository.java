package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<APIUser, Long> {
    Optional<APIUser> findByUserName(String name);
    Optional<APIUser> findByEmail(String email);
    Boolean existsByName(String username);
    Boolean existsByEmail(String email);
}
