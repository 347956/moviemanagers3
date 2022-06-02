package com.teun.moviemanager.Models;

import com.teun.moviemanager.DTO.MovieListDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//The MovieList entity is used for the Users to create a custom movieList
//They can add movies from the "main" list which contains every movie in the databse
@Entity
@Table (name="movieList")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieList {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="movielist_id")
    private Long movielistId;
    @Column(name="userId")
    private Long userId;
    @Column(name="name")
    private String name;
    @Column(name="created")
    private Date created;
    @Column(name="movieCount")
    private Long movieCount;
/*    @ElementCollection
    @CollectionTable(name = "movie_list_movies", joinColumns =@JoinColumn(name ="movie_list_id"))
    @Column(name="movie_id")*/
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade ={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
                    }
    )
    @JoinTable(
            name="movie_list_movie", joinColumns = @JoinColumn(name="movielist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private List<Movie> movies = new ArrayList();


    //Used in the service layer to update a model with the values from a DTO before sending the model to the Repository
    public void updateWithDTO(MovieListDTO movieListDTO){
        this.id = movieListDTO.getId();
        this.movielistId = movieListDTO.getMovielistid();
        this.userId = movieListDTO.getUserId();
        this.name = movieListDTO.getName();
        this.created = movieListDTO.getCreated();
        this.movieCount = movieListDTO.getMovieCount();
        this.movies = movieListDTO.getMovies();
    }
}
