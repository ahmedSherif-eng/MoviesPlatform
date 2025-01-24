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

    @PostMapping("/movies/new")
    public ResponseEntity<Movie> addMovie(@RequestBody @Valid MovieDTO movieDTO) {
        Movie movie = moviesService.addMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping("/movies/saved")
    public ResponseEntity<SearchResultDTO> getMoviesPage(@RequestParam
                                                             @Pattern(regexp = "^\\d+$", message = "page number has to be numeric value")
                                                             String page) {
        Pageable fixedPageable = PageRequest.of(Integer.parseInt(page), pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMoviesPage(fixedPageable));
    }

    @PostMapping("/movies/batch")
    public ResponseEntity<Boolean> addMoviesBatch(@RequestBody @Valid List<MovieDTO> movieDTOs) {
        moviesService.addMoviesBatch(movieDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @DeleteMapping("/movies/batch/delete")
    public ResponseEntity<Boolean> removeMoviesBatch(@RequestBody List<String> moviesName) {
        boolean isDeleted = moviesService.removeMoviesBatch(moviesName);
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }
}
