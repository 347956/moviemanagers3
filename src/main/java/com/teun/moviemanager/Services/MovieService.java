package com.teun.moviemanager.Services;

import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Repositories.IMovieRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final IMovieRepository repository;

    public Movie GetMovie(long Id){
        return repository.getById(Id);

    }
    public List<Movie> GetAllMovies(){
        return repository.findAll();

    }
    public int CreateMovie(Movie movie){
        repository.save(movie);
        return 0;
    }
    public void DeleteMovie(Long Id){
        repository.deleteById(Id);
    }
    public boolean UpdateMovie(Movie movie){
        repository.save(movie);
        return false;
    }
}
