package com.teun.moviemanager.Services;

import com.teun.moviemanager.DTO.MovieDTO;
import com.teun.moviemanager.Models.Movie;
import com.teun.moviemanager.Repositories.IMovieRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//git test
//hello

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final IMovieRepository repository;

    public MovieDTO GetMovie(Long Id){
        Optional<Movie> optmovie = repository.findById(Id);
        if(optmovie.isPresent()){
            return createDTOFromModel(repository.findById(Id).orElse(null));
        }
        else{
            log.error("Movie with id: {} not found in the database", Id);
            throw new EntityNotFoundException();
        }
    }
    public MovieDTO getMovieByName(String name){
        Movie movie = repository.findByName(name).orElse(null);
        if(movie == null){
            log.error("Movie with name: {} not found in the database", name);
        }
        return createDTOFromModel(movie);
    }
    public List<MovieDTO> searchMovies(String term) throws EntityNotFoundException{
        List<Movie> foundMovies = repository.findAllByName(term).orElse(null);
        if(foundMovies.size() != 0){
            List<MovieDTO> movieDTOS = new ArrayList<>();
            return createMovieDTOListFromModelList(foundMovies);
        }
        else{
            log.error("No movies with name containing: {} not found in the database", term);
            throw new EntityNotFoundException("No movies with that term found in the database");
        }
    }
    public List<MovieDTO> GetAllMovies(){
        return createMovieDTOListFromModelList(repository.findAll());
    }
    public List<MovieDTO> getAllMoviesByGenres(String genre){
        List<Movie> foundMovies = repository.findAllByGenre(genre).orElse(null);
        if(foundMovies.size() != 0){
            return createMovieDTOListFromModelList(foundMovies);
        }
        else{
            log.error("No movies with genre: {} not found in the database", genre);
            throw new EntityNotFoundException();

        }
    }
    public MovieDTO CreateMovie(MovieDTO movie){
        return createDTOFromModel(repository.save(createModelFromMovieDTO(movie)));
    }
    public boolean DeleteMovie(Long Id){
        repository.deleteById(Id);
        Optional<Movie> optMovie = repository.findById(Id);
        if(optMovie.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public MovieDTO UpdateMovie(MovieDTO movieDTO){
        if (repository.findById(movieDTO.getId()).isPresent()){
            Movie movieTosend = createModelFromMovieDTO(movieDTO);
            MovieDTO updatedMovie = createDTOFromModel(repository.save(movieTosend));
            log.info("Movie: {} upated", movieDTO.getName());
            return updatedMovie;
        }
        else{
            throw new EntityNotFoundException("Could not find a movie in the database to update");
        }
    }
    private MovieDTO createDTOFromModel(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setGenre2(movie.getGenre2());
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setWatched(movie.isWatched());
        return movieDTO;
    }
    private Movie createModelFromMovieDTO(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setGenre2(movieDTO.getGenre2());
        movie.setMovieId(movieDTO.getMovieId());
        movie.setWatched(movieDTO.isWatched());
        return movie;
    }
    private List<MovieDTO> createMovieDTOListFromModelList(List<Movie> movies){
        List<MovieDTO> movieDTOS = new ArrayList<>();
        for (Movie movie: movies) {
            movieDTOS.add(createDTOFromModel(movie));
        }
        return movieDTOS;
    }
}
