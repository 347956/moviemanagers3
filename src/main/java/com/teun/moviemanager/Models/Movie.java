package com.teun.moviemanager.Models;

import javax.persistence.*;

@Entity (name="movie")
@Table (name="move")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "genre2", nullable = true)
    private String genre2;
    @Column(name = "watched", nullable = false)
    private boolean watched;
    public Movie (Long id, String name, String genre, boolean watched){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.watched = watched;
    }
    //watched n/a
    public Movie (Long id, String name, String Genre){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.watched = false;
    }
    //empty
    public Movie(){

    }
}
