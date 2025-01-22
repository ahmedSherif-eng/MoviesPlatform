package com.fawry.moviesplatform.mapper;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.entity.Movie;

public class MoviesMapper {
    public static Movie mapToMovie(MovieDTO movieDTO){
        if (movieDTO == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle().toLowerCase());
        movie.setType(movieDTO.getType());
        movie.setYear(movieDTO.getYear());
        movie.setImdbID(movieDTO.getImdbID());
        movie.setPoster(movieDTO.getPoster());
        return movie;
    }
    public static MovieDTO mapToMovieDTO(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setImdbID(movie.getImdbID());
        movieDTO.setPoster(movie.getPoster());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setType(movie.getType());
        movieDTO.setYear(movie.getYear());
        return movieDTO;
    }
}
