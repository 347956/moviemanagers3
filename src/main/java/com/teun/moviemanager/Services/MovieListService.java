package com.teun.moviemanager.Services;

import com.teun.moviemanager.DTO.MovieListDTO;
import com.teun.moviemanager.Models.MovieList;
import com.teun.moviemanager.Repositories.IMovieListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieListService {

    private final IMovieListRepository repository;
    private final MovieService movieService;

    //Gets the MovieList by movielist id
    public MovieListDTO getMovieListById(Long id){
        MovieList movieList = repository.findById(id).orElse(null);
        if(movieList == null){
            log.error("Could not find Movielist with id: {}",id);
            throw new EntityNotFoundException();
        }
        else{
            MovieListDTO movieListDTO = createDTOFromModel(movieList);
            return (movieListDTO);
        }
    }

    public List<MovieListDTO> getMovieListByUserId(Long userid){
        List<MovieListDTO> movieListDTOS = new ArrayList<>();
        for (MovieList movieList: repository.findAllByUserId(userid)){
            movieListDTOS.add(createDTOFromModel(movieList));
        }
        return movieListDTOS;
    }

    //Creates a movie model, updates the moviemodel with the values of the DTO, saves it to the repository
    public void createMovieList(MovieListDTO movieListDTO){
        MovieList movieList = new MovieList();
        movieList.updateWithDTO(movieListDTO);
        repository.save(movieList);
    }

    //deletes the movieList
    public void deleteMovieList(Long id){
        repository.deleteById(id);
    }

    //Updates the movieList if it can be found and return true when updated
    //if it's not found it will not update and return false
    public Boolean updateMovieList(MovieListDTO movieListDTO){
        MovieList movieList = repository.findById(movieListDTO.getId()).orElse(null);
        if(movieList != null){
            movieList.updateWithDTO(movieListDTO);
            repository.save(movieList);
            return true;
        }
        else {
            return false;
        }
    }

    //Creates a DTO from a model so a DTO can easily be returned to the controller
    //Also I dont want to convert every model to DTO in every method
    private MovieListDTO createDTOFromModel(MovieList movieList){

        MovieListDTO movieListDTO = new MovieListDTO();

        movieListDTO.setId(movieList.getId());
        movieListDTO.setMovielistid(movieList.getMovielistId());
        movieListDTO.setUserId(movieList.getUserId());
        movieListDTO.setName(movieList.getName());
        movieListDTO.setCreated(movieList.getCreated());
        movieListDTO.setMovieCount(movieList.getMovieCount());
        movieListDTO.setMovies(movieList.getMovies());

        return movieListDTO;
    }
    //retrieve a list of movieDTO's that need to go into the movielist DTO
    private List<MovieListDTO> retrieveMovieListMovies(int movieListId){
        List<Integer> movieIds = new ArrayList<Integer>();
        return null;
    }

}
