package com.teun.moviemanager.DTO;

import com.teun.moviemanager.Models.Movie;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieListDTO {
    private Long id;
    private Long movielistid;
    private Long userId;
    private String name;
    private Date created;
    private Long movieCount;
    private List<Movie> movies;
}
