package com.teun.moviemanager.Controller;
import com.teun.moviemanager.DTO.MovieDTO;
import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<MovieDTO> getMoviePath(@PathVariable(value = "id") Long id){
        try{
            MovieDTO movie = service.GetMovie(id);
            return ResponseEntity.ok().body(movie);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/filter/{genre}")
    public ResponseEntity<List<MovieDTO>> filterMoviesByGenre(@PathVariable(value = "genre") String genre){
        List<MovieDTO> movies = service.getAllMoviesByGenres(genre);
        if(movies != null){
            return ResponseEntity.ok().body(movies);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search/{term}")
    public ResponseEntity <List<MovieDTO>> searchMovie(@PathVariable (value = "term")String term){
        List<MovieDTO> movies = service.searchMovies(term);
        if(movies != null){
            return ResponseEntity.ok().body(movies);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    //get a list of all movies
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
        List<MovieDTO> movies = service.GetAllMovies();
        if(movies != null){
            return ResponseEntity.ok().body(movies);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/save")
    public MovieDTO addNewsItem(@RequestBody MovieDTO movie){
        return service.CreateMovie(movie);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id){
        boolean deleted = service.DeleteMovie(id);
        if(deleted == true){
            return ResponseEntity.ok().body(deleted);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping()
    public ResponseEntity<MovieDTO> UpdateMovie(@RequestBody MovieDTO movie){
        MovieDTO updatedMovie = service.UpdateMovie(movie);
        if(updatedMovie.getName().equals(movie.getName())){
            return ResponseEntity.ok().body(updatedMovie);
        }
        else{
            return new ResponseEntity("Something went wrong. Make sure the movie already exists before updating", HttpStatus.NOT_FOUND);
        }
    }
}
