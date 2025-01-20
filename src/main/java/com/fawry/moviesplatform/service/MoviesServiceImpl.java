package com.fawry.moviesplatform.service;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.entity.OmdbSearchResult;
import com.fawry.moviesplatform.mapper.MoviesMapper;
import com.fawry.moviesplatform.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static com.fawry.moviesplatform.constants.OmdbConst.OMDB_URL;

@Service

@AllArgsConstructor
@Setter
@Getter
public class MoviesServiceImpl implements MoviesService {

    private MovieRepository movieRepository;

    @Override
    public OmdbSearchResult searchMovies(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = OMDB_URL + query;
        return restTemplate.getForObject(url, OmdbSearchResult.class);
    }

    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        return movieRepository.save(MoviesMapper.mapToMovie(movieDTO));
    }

    @Override
    public void removeMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Page<MovieDTO> getMoviesPage(Pageable pageable) {
        return movieRepository.findAll(pageable).map(MoviesMapper::mapToMovieDTO);
    }

    @Override
    public Movie getMovieDetails(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
