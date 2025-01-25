package com.fawry.moviesplatform.service;

import com.fawry.moviesplatform.DTO.MovieDetailsDTO;
import com.fawry.moviesplatform.DTO.SearchResultDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoviesService {
    SearchResultDTO searchMovies(String query, String page);

    SearchResultDTO getMoviesPage(Pageable pageable);

    MovieDetailsDTO searchMovieDetails(String imdbId);

    List<String> getSavedMoviesIds();

    SearchResultDTO getMoviesPageContaining(String title, Pageable pageable);

    @Transactional
    boolean addMoviesBatch(List<String> ImdbIds);

    @Transactional
    boolean removeMoviesBatch(List<String> movieNames);
}
