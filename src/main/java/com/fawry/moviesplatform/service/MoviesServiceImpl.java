package com.fawry.moviesplatform.service;

import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.entity.OmdbSearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Override
    public OmdbSearchResult searchMovies(String query) {
        return null;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public void removeMovie(Long id) {

    }

    @Override
    public List<Movie> getAllMovies() {
        return List.of();
    }

    @Override
    public Movie getMovieDetails(Long id) {
        return null;
    }
}
