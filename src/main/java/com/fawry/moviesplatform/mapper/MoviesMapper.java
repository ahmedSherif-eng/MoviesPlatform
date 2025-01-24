package com.fawry.moviesplatform.mapper;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.DTO.MovieDetailsDTO;
import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.entity.Rating;

import java.util.stream.Collectors;

public class MoviesMapper {
    public static Movie mapToMovie(MovieDetailsDTO movieDetailsDTO){
        if (movieDetailsDTO == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setImdbID(movieDetailsDTO.getImdbID());
        movie.setPoster(movieDetailsDTO.getPoster());
        movie.setTitle(movieDetailsDTO.getTitle());
        movie.setType(movieDetailsDTO.getType());
        movie.setYear(movieDetailsDTO.getYear());
        movie.setActors(movieDetailsDTO.getActors());
        movie.setAwards(movieDetailsDTO.getAwards());
        movie.setCountry(movieDetailsDTO.getCountry());
        movie.setDirector(movieDetailsDTO.getDirector());
        movie.setGenre(movieDetailsDTO.getGenre());
        movie.setLanguage(movieDetailsDTO.getLanguage());
        movie.setPlot(movieDetailsDTO.getPlot());
        movie.setProduction(movieDetailsDTO.getProduction());
        movie.setRated(movieDetailsDTO.getRated());
        movie.setReleased(movieDetailsDTO.getReleased());
        movie.setRuntime(movieDetailsDTO.getRuntime());
        movie.setWriter(movieDetailsDTO.getWriter());
        movie.setImdbRating(movieDetailsDTO.getImdbRating());
        movie.setImdbVotes(movieDetailsDTO.getImdbVotes());
        movie.setRatings(movieDetailsDTO.getRatings().stream()
                .map(ratingDTO -> {
                    Rating rating = new Rating();
                    rating.setMovie(movie);
                    rating.setSource(ratingDTO.getSource());
                    rating.setValue(ratingDTO.getValue());
                    return rating;
                })
                .collect(Collectors.toList()));

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
