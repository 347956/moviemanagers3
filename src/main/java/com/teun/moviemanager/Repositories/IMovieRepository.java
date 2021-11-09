package com.teun.moviemanager.Repositories;

import com.teun.moviemanager.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository <Movie, Long> {
}
