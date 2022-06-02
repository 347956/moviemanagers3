package com.teun.moviemanager.Controller;


import com.teun.moviemanager.DTO.MovieListDTO;
import com.teun.moviemanager.Services.MovieListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movielist")
public class MovieListController {
//test
    private final MovieListService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<MovieListDTO> getMovieListById(@PathVariable(value = "id") Long id){
        MovieListDTO movieListDTO= service.getMovieListById(id);
        if(movieListDTO != null){
            return ResponseEntity.ok().body(movieListDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/userid/{id}")
    public ResponseEntity<List<MovieListDTO>> getMovieListsByUserId(@PathVariable(value = "id") Long id){
        List<MovieListDTO> movieListDTOS = service.getMovieListByUserId(id);
        if(movieListDTOS != null){
            return ResponseEntity.ok().body(movieListDTOS);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public void createMovieList(@RequestBody MovieListDTO movieListDTO){
        service.createMovieList(movieListDTO);
    }

    @PutMapping
    public void updateMovieList(@RequestBody MovieListDTO movieListDTO){
        service.updateMovieList(movieListDTO);
    }
}
