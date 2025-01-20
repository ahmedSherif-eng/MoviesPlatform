package com.fawry.moviesplatform.controllers;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.entity.OmdbSearchResult;
import com.fawry.moviesplatform.service.MoviesService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    private  final MoviesService moviesService;

    @Value("${pagination.page-size}")
    private int pageSize;

    @GetMapping(value = "/movies/search")
    public ResponseEntity<OmdbSearchResult> searchMovies (@RequestParam String query){
        OmdbSearchResult  omdbSearchResult = moviesService.searchMovies(query);
        return ResponseEntity.status(HttpStatus.OK).body(omdbSearchResult);
    }

    @PostMapping("/movies/new")
    public ResponseEntity<Movie> addMovie(@RequestBody @Valid MovieDTO movieDTO) {
        Movie movie = moviesService.addMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }
    @GetMapping("/movies")
    public ResponseEntity<Page<MovieDTO>> getMoviesPage(Pageable pageable){
        Pageable fixedPageable = PageRequest.of(pageable.getPageNumber(), pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMoviesPage(fixedPageable));    }

}
