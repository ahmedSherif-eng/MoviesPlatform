package com.fawry.moviesplatform.controllers;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.DTO.MovieDetailsDTO;
import com.fawry.moviesplatform.DTO.SearchResultDTO;
import com.fawry.moviesplatform.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class UserController {
    private final MoviesService moviesService;

    @Value("${pagination.page-size}")
    private int pageSize;

    @GetMapping("/movies/saved")
    public ResponseEntity<SearchResultDTO> getMoviesPage(@RequestParam String page) {
        Pageable fixedPageable = PageRequest.of(Integer.parseInt(page), pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMoviesPage(fixedPageable));
    }

    @GetMapping("/movies/details")
    public ResponseEntity<MovieDetailsDTO> getMovie(@RequestParam String imdbId) {
        return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMovieDetails(imdbId));
    }

    @GetMapping(value = "/movies/saved/search")
    public ResponseEntity<SearchResultDTO> searchMovies(@RequestParam String title, @RequestParam String page) {
        Pageable fixedPageable = PageRequest.of(Integer.parseInt(page), pageSize);
       return ResponseEntity.status(HttpStatus.OK).body(moviesService.getMoviesPageContaining(title,fixedPageable));
    }

}