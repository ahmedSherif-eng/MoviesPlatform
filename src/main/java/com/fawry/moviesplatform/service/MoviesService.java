package com.fawry.moviesplatform.service;

import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.entity.OmdbSearchResult;

import java.util.List;

public interface MoviesService {
    OmdbSearchResult searchMovies(String query);
    Movie addMovie(Movie movie);
    void removeMovie(Long id);
    List<Movie> getAllMovies();
    Movie getMovieDetails(Long id);
}
