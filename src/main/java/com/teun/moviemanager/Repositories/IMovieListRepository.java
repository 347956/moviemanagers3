package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMovieListRepository extends JpaRepository<MovieList, Long> {
    List<MovieList> findAllByUserId(Long userid);
    Optional<MovieList> findById(Long id);
}
