package com.teun.moviemanager.Controller;

import com.teun.moviemanager.DTO.MovieDTO;
import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Services.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    MovieService movieServiceMock;

    @InjectMocks
    MovieController movieControllerTest;

    //assign
    //act
    //assert

    //intergration tests
    @Test
    void getMoviePath_GetAMovieByID_ShouldReturnTrue() {
        //assign
        long id = 1;
        MovieDTO movieMock = new MovieDTO();
        movieMock.setId(id);
        movieMock.setName("MockitoMovie");
        movieMock.setGenre("Unit");
        movieMock.setGenre2("Testing");
        movieMock.setWatched(true);
        ResponseEntity<MovieDTO> retrievedMovie;
        //act
        Mockito.when(movieServiceMock.GetMovie(anyLong())).thenReturn(movieMock);
        retrievedMovie = movieControllerTest.getMoviePath(id);
        //assert
        assertEquals("MockitoMovie", retrievedMovie.getBody().getName(),"Wrong name");
    }
    @Test
    void getMoviePath_GetAMovieByIDNoResult_ShouldReturnFalse() {
        //assign
        long id = 1;
        MovieDTO movieMock = new MovieDTO();
        movieMock.setId(id);
        movieMock.setName("MockitoMovie");
        movieMock.setGenre("Unit");
        movieMock.setGenre2("Testing");
        movieMock.setWatched(true);
        ResponseEntity<MovieDTO> retrievedMovie;
        boolean retrieved;
        //act
        Mockito.when(movieServiceMock.GetMovie(anyLong())).thenReturn(null);
        retrievedMovie = (movieControllerTest.getMoviePath(id));
        if(retrievedMovie.getBody() == null){
            retrieved = false;
        }
        else{
            retrieved = true;
        }
        //assert
        assertFalse(retrieved);
    }

    @Test
    void filterMoviesByGenre_WhenMoviesAreReturned_ShouldReturnTrue() {
        //assign
        List<MovieDTO> movieList = new ArrayList<>();
        MovieDTO movie1 = new MovieDTO();
        MovieDTO movie2 = new MovieDTO();
        ResponseEntity<List<MovieDTO>> retrievedList;
        //act
        movieList.add(movie1);
        movieList.add(movie2);
        Mockito.when(movieServiceMock.getAllMoviesByGenres(anyString())).thenReturn(movieList);
        retrievedList = movieControllerTest.filterMoviesByGenre("mockito");
        //assert
        assertEquals(movieList.size(), retrievedList.getBody().size());
    }
    @Test
    void filterMoviesByGenre_WhenNoMoviesAreReturned_ShouldReturnFalse() {
        //assign
        List<MovieDTO> movieList = new ArrayList<>();
        ResponseEntity<List<MovieDTO>> retrievedList;
        //act
        Mockito.when(movieServiceMock.getAllMoviesByGenres(anyString())).thenReturn(movieList);
        retrievedList = movieControllerTest.filterMoviesByGenre("mockito");
        //assert
        assertSame(movieList.size(), retrievedList.getBody().size());
    }

    @Test
    void searchMovie() {
        //assign
        List<MovieDTO> movieList = new ArrayList<>();
        ResponseEntity<List<MovieDTO>> retrievedList;
        MovieDTO movie1 = new MovieDTO();
        MovieDTO movie2 = new MovieDTO();
        //act
        movieList.add(movie1);
        movieList.add(movie2);
        Mockito.when(movieServiceMock.searchMovies(anyString())).thenReturn(movieList);
        retrievedList = movieControllerTest.searchMovie("mockito");
        //assert
        assertNotNull(retrievedList.getBody());
    }

    @Test
    void getAllMovies() {
        //assign
        List<MovieDTO> movieList = new ArrayList<>();
        ResponseEntity<List<MovieDTO>> retrievedList;
        MovieDTO movie1 = new MovieDTO();
        MovieDTO movie2 = new MovieDTO();
        //act
        movieList.add(movie1);
        movieList.add(movie2);
        Mockito.when(movieServiceMock.searchMovies(anyString())).thenReturn(movieList);
        retrievedList = movieControllerTest.searchMovie("mockito");
        //assert
        assertTrue (retrievedList.getBody().size() >0);
    }

    @Test
    void addNewsItem() {
        //assign
        MovieDTO movie1 = new MovieDTO();
        boolean created;
        Optional<MovieDTO> createdMovie;
        //act
        Mockito.when(movieServiceMock.CreateMovie(any(MovieDTO.class))).thenReturn(movie1);
        createdMovie = Optional.ofNullable(movieControllerTest.addNewsItem(movie1));
        if(createdMovie.isPresent()){
            created = true;
        }
        else{
            created = false;
        }
        //assert
        assert (created == true);
    }

    @Test
    void deleteMovie_WhenDeletingAnExistingMovie_shouldReturnTrue() {
        //assign
        long id = 1;
        //act
        Mockito.when(movieServiceMock.DeleteMovie(anyLong())).thenReturn(true);
        //assert
        assertTrue(movieControllerTest.deleteMovie(id).hasBody());
    }
    @Test
    void deleteMovie_WhenDeletingANonExistingMovie_shouldReturnFalse() {
        //assign
        long id = 1;
        //act
        Mockito.when(movieServiceMock.DeleteMovie(anyLong())).thenReturn(false);
        //assert
        assertFalse(movieControllerTest.deleteMovie(id).hasBody());
    }

    @Test
    void updateMovie() {
        //assign
        long id = 1;
        MovieDTO movie1 = new MovieDTO();
        MovieDTO updatedMovie = new MovieDTO();
        movie1.setId(id);
        movie1.setName("MockitoMovie");
        movie1.setGenre("Unit");
        movie1.setGenre2("Testing");
        movie1.setWatched(true);
        updatedMovie.setId(id);
        updatedMovie.setName("MockitoMovie Updated");
        updatedMovie.setGenre("Unit");
        updatedMovie.setGenre2("Testing");
        updatedMovie.setWatched(true);
        //act
        Mockito.when(movieServiceMock.UpdateMovie(any(MovieDTO.class))).thenReturn(updatedMovie);
        movie1 = movieControllerTest.UpdateMovie(updatedMovie).getBody();
        //assert
        assertEquals(movie1.getName(), updatedMovie.getName());
    }
}