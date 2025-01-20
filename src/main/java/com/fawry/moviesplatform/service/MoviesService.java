package com.fawry.moviesplatform.service;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.entity.OmdbSearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MoviesService {
    OmdbSearchResult searchMovies(String query);
    Movie addMovie(MovieDTO movieDTO);
    void removeMovie(Long id);
    Page<MovieDTO> getMoviesPage(Pageable pageable);
    Movie getMovieDetails(Long id);
}
