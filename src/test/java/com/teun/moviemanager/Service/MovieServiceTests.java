package com.teun.moviemanager.Service;

import com.teun.moviemanager.DTO.MovieDTO;
import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Repositories.IMovieRepository;
import com.teun.moviemanager.Services.MovieService;

import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {
    @Mock
    private IMovieRepository movieRepositoryMock;
    @InjectMocks
    private MovieService movieService;

    @Test
    void createMovie_SavesAMovieInTheDataBase_ShouldReturnTrue(){
        //assign
        MovieDTO movieDTO = new MovieDTO();
        Movie movie = new Movie();
        MovieDTO retrievedMovie;
        long id = 200;
        movie.setId(id);
        movie.setName("TestMovie");
        movie.setGenre("Mockito");
        movie.setGenre2("UnitTesting");
        movie.setWatched(true);

        //act
        //saves the movie and gives it back
        Mockito.when(movieRepositoryMock.save(any(Movie.class))).thenReturn(movie);
        retrievedMovie = movieService.CreateMovie(movieDTO);
        //assert
        assert(retrievedMovie.getName() == movie.getName());
    }
    @Test
    void getMovie_GetsAMovieFromTheDataBase_ShouldReturnTrue(){
        //assign
        Movie movie = new Movie();
        MovieDTO retrievedMovie;
        long id = 200;
        //act
        movie.setId(id);
        movie.setName("TestMovie");
        movie.setGenre("Mockito");
        movie.setGenre2("UnitTesting");
        movie.setWatched(true);

        Mockito.when(movieRepositoryMock.findById( anyLong())).thenReturn(Optional.of(movie));
        retrievedMovie = movieService.GetMovie(id);
        //assert
        assert(retrievedMovie.getName() == movie.getName());
    }
    @Test
    void updateMovie_checksIfAnExistantMovieGetsUpdated_ShouldReturnTrue(){
        //assign
        long id = 200;
        String expected = "updateMovie";
        MovieDTO updatedMovieDTO = new MovieDTO();
        updatedMovieDTO.setId(id);
        Movie movie = new Movie();
        Movie updatedMovie = new Movie();
        movie.setId(id);
        movie.setName("Movie");
        movie.setGenre("Mockito");
        movie.setGenre2("UnitTesting");
        movie.setWatched(true);
        updatedMovie.setId(id);
        updatedMovie.setName("updateMovie");
        updatedMovie.setGenre("Mockito");
        updatedMovie.setGenre2("UnitTesting");
        updatedMovie.setWatched(true);
        //act
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(Optional.of(movie));
        Mockito.when(movieRepositoryMock.save(any(Movie.class))).thenReturn(updatedMovie);
        MovieDTO movieDTO = movieService.UpdateMovie(updatedMovieDTO);
        //assert
        assertEquals(movieDTO.getName(), expected);
    }
    @Test()
    void updateMovie_checksIfANonExistantMovieGetsUpdated_ShouldReturnFalse(){
        //assign
        long id = 200;
        Movie movie = new Movie();
        MovieDTO updatedMovieDTO = new MovieDTO();
        updatedMovieDTO.setId(id);
        String exception =" ";
        String expected = "javax.persistence.EntityNotFoundException: Could not find a movie in the database to update";
        movie.setId(id);
        movie.setName("Movie");
        movie.setGenre("Mockito");
        movie.setGenre2("UnitTesting");
        movie.setWatched(true);
        updatedMovieDTO.setId(id);
        updatedMovieDTO.setName("updateMovie");
        updatedMovieDTO.setGenre("Mockito");
        updatedMovieDTO.setGenre2("UnitTesting");
        updatedMovieDTO.setWatched(true);
        //act
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        try{
            movieService.UpdateMovie(updatedMovieDTO);
        }
        catch (EntityNotFoundException e){
            exception = e.toString();
        }
        //assert
        assertEquals(expected, exception);
    }
}
