package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMovieRepository extends JpaRepository <Movie, Long> {
    Optional <List<Movie>> findAllByGenre(String genre);
    Optional <List<Movie>> findAllByName(String term);
    Optional <Movie> findByName(String name);
}
