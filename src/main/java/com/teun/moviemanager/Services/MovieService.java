package com.teun.moviemanager.Services;

import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Repositories.IMovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
//git test
//hello

@Service
public class MovieService {
    @Autowired
    private IMovieRepository repository;

    public Movie GetMovie(Long Id){

        return repository.findById(Id).orElse(null);

    }
    public List<Movie> GetAllMovies(){
        return repository.findAll();

    }
    public Movie CreateMovie(Movie movie){
        return repository.save(movie);
    }
    public void DeleteMovie(Long Id){
        repository.deleteById(Id);
    }

    public boolean UpdateMovie(Movie movie){
        if(repository.save(movie) != null)
        {
            return true;
        }
        else{
            return false;
        }
    }
}
