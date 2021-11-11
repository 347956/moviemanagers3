package com.teun.moviemanager.Controller;
import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

    @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> getMoviePath(@PathVariable(value = "id") Long id){
        Movie movie = service.GetMovie(id);
        if(movie != null){
            return ResponseEntity.ok().body(movie);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    //get a list of all movies

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = null;
        movies = service.GetAllMovies();
        if(movies != null){
            return ResponseEntity.ok().body(movies);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public Movie addNewsItem(@RequestBody Movie movie){
        return service.CreateMovie(movie);
    }



//    @PostMapping()
//    public ResponseEntity<Movie> CreateMovie(@RequestBody Movie movie){
//        int newMovieId = 0;
//        service.CreateMovie(movie);
//        if(newMovieId != 0){
//            String url = "Movie" + "/";
//            URI uri = URI.create(url);
//            return new ResponseEntity(uri, HttpStatus.CREATED);
//        }
//        else{
//            String entity = "A movie with the same name: already Exist";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        }
//    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id){
        service.DeleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Movie> UpdateMovie(@RequestBody Movie movie){
        if(service.UpdateMovie(movie)){
            return ResponseEntity.noContent().build();
        }
        else{
            return new ResponseEntity("Please provide a valid Movie Id.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id){
        Movie movie = service.GetMovie(id);
        service.UpdateMovie(movie);
        return ResponseEntity.ok().build();
    }
}
