package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface IMemberRepository extends JpaRepository<Member, Long> {
    //Make sure to match the field from the Model
    Optional<Member> findByName(String username);
}
