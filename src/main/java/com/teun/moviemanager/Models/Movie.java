package com.teun.moviemanager.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name="movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "genre2", nullable = true)
    private String genre2;
    @Column(name = "watched", nullable = false)
    private boolean watched;
}
