package com.teun.moviemanager.Models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "genre2", nullable = true)
    private String genre2;
    @Column(name = "watched", nullable = false)
    private boolean watched;
/*    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade ={
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
            @JoinTable(name="movie_list)movie",
            joinColumns = {@JoinColumn(name = "movie_id")},
                    inverseJoinColumns = {@JoinColumn(name = "movielist_id")})
    List<MovieList> movieLists = new ArrayList<>();*/
}
