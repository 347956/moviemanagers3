package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
