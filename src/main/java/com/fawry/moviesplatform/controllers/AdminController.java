package com.fawry.moviesplatform.controllers;

import com.fawry.moviesplatform.DTO.MovieDetailsDTO;
import com.fawry.moviesplatform.DTO.SearchResultDTO;
import com.fawry.moviesplatform.service.MoviesService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminController {
    private final MoviesService moviesService;

    @Value("${pagination.page-size}")
    private int pageSize;


    @GetMapping(value = "/movies/search")
    public ResponseEntity<SearchResultDTO> searchMovies(@RequestParam String query, @RequestParam String page) {
        SearchResultDTO omdbSearchResult = moviesService.searchMovies(query,page);
        return ResponseEntity.status(HttpStatus.OK).body(omdbSearchResult);
    }


    @GetMapping("/movies/saved")
    public ResponseEntity<SearchResultDTO> getMoviesPage(@RequestParam
                                                             @Pattern(regexp = "^\\d+$", message = "page number has to be numeric value")
                                                             String page) {
        Pageable fixedPageable = PageRequest.of(Integer.parseInt(page), pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMoviesPage(fixedPageable));
    }

    @GetMapping("/movies/details")
    public ResponseEntity<MovieDetailsDTO> getMovie(@RequestParam
                                                        @Pattern(regexp = "^tt\\d{7}$", message = "imdbID must start with 'tt' followed by 7 numeric digits")
                                                        String imdbId) {
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.searchMovieDetails(imdbId));
    }

    @GetMapping("/movies/saved/ids")
    public ResponseEntity<List<String>> getSavedMoviesIds() {
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getSavedMoviesIds());
    }

    @PostMapping("/movies/batch")
    public ResponseEntity<Boolean> addMoviesBatch(@RequestBody List<String> ImdbIds) {
        moviesService.addMoviesBatch(ImdbIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @DeleteMapping("/movies/batch/delete")
    public ResponseEntity<Boolean> removeMoviesBatch(@RequestBody List<String> moviesName) {
        boolean isDeleted = moviesService.removeMoviesBatch(moviesName);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

}
