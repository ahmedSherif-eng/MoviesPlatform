package com.fawry.moviesplatform.service;

import com.fawry.moviesplatform.DTO.MovieDTO;
import com.fawry.moviesplatform.DTO.MovieDetailsDTO;
import com.fawry.moviesplatform.DTO.SearchResultDTO;
import com.fawry.moviesplatform.entity.Movie;
import com.fawry.moviesplatform.exception.MovieBatchProcessingException;
import com.fawry.moviesplatform.mapper.MoviesMapper;
import com.fawry.moviesplatform.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fawry.moviesplatform.constants.OmdbConst.OMDB_Movie_Details_URL;
import static com.fawry.moviesplatform.constants.OmdbConst.OMDB_Movie_URL;

@Service

@AllArgsConstructor
@Setter
@Getter
public class MoviesServiceImpl implements MoviesService {

    private static final Logger logger = LoggerFactory.getLogger(MoviesServiceImpl.class);

    private MovieRepository movieRepository;

    private static final int MAX_BATCH_SIZE = 10;

    @Override
    public SearchResultDTO searchMovies(String query, String page) {
        RestTemplate restTemplate = new RestTemplate();
        String url = OMDB_Movie_URL + query + "&page=" + page;
        return restTemplate.getForObject(url, SearchResultDTO.class);
    }

    @Override
    public MovieDetailsDTO searchMovieDetails(String imdbId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = OMDB_Movie_Details_URL + imdbId;
        return restTemplate.getForObject(url, MovieDetailsDTO.class);
    }

    @Override
    public SearchResultDTO getMoviesPage(Pageable pageable) {
        Page<Movie> moviesPage = movieRepository.findAll(pageable);
        List<MovieDTO> movieDTOs = moviesPage.stream().map(MoviesMapper::mapToMovieDTO).collect(Collectors.toList());
        return new SearchResultDTO(movieDTOs, Integer.toString(moviesPage.getTotalPages()), Long.toString(moviesPage.getTotalElements()));
    }

    @Override
    public List<String> getSavedMoviesIds() {
        return movieRepository.findAllIds();
    }

    @Override
    public MovieDetailsDTO getMovieDetails(String imdbId) {
        Movie movie = movieRepository.findByImdbID(imdbId).orElseThrow(() -> new IllegalArgumentException("Movie with IMDB ID " + imdbId + " not found"));
        if(movie == null) {
            throw new IllegalArgumentException("Movie with IMDB ID " + imdbId + " not found");
        }
        System.out.println(movie.getImdbID()+" "+ movie.getTitle());
        return MoviesMapper.mapToMovieDetailsDTO(movie);
    }

    @Override
    public SearchResultDTO getMoviesPageContaining(String title, Pageable pageable) {
        Page<Movie> moviesPage = movieRepository.findByTitleContaining(title, pageable);
        List<MovieDTO> movieDTOs = moviesPage.stream().map(MoviesMapper::mapToMovieDTO).collect(Collectors.toList());
        return new SearchResultDTO(movieDTOs, Integer.toString(moviesPage.getTotalPages()), Long.toString(moviesPage.getTotalElements()));
    }


    @Override
    @Transactional
    public boolean addMoviesBatch(List<String> moviesImbdIds) {
        logger.debug("Starting batch processing of {} movies", moviesImbdIds.size());

        if (moviesImbdIds.size() > MAX_BATCH_SIZE) {
            logger.warn("Batch size {} exceeds maximum allowed size of {}", moviesImbdIds.size(), MAX_BATCH_SIZE);
            throw new IllegalArgumentException("Batch size cannot exceed " + MAX_BATCH_SIZE + " movies");
        }

        List<Movie> savedMovies = moviesImbdIds.parallelStream()
                .map(imdbId -> {
                    try {
                        Optional<Movie> optMovie = movieRepository.findByImdbID(imdbId);
                        if (optMovie.isPresent()) {
                            logger.warn("Movie with IMDB ID {} already exists", imdbId);
                            return null;
                        }
                        MovieDetailsDTO movieDetails = searchMovieDetails(imdbId);
                        Movie movie = MoviesMapper.mapToMovie(movieDetails);
                        return movieRepository.save(movie);
                    } catch (Exception e) {
                        logger.error("Error processing movie with IMDB ID {}: {}", imdbId, e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (savedMovies.isEmpty()) {
            throw new MovieBatchProcessingException("No movies were saved. All movies either failed to save or already existed.");
        }
        return true;
    }

    @Transactional
    @Override
    public boolean removeMoviesBatch(List<String> movieTitles) {
        for(String movieTitle : movieTitles){
            try{
            movieRepository.deleteByTitle(movieTitle);
        }
            catch (Exception e){
                logger.error("Error in deleting movie: " + movieTitle + " - " + e.getMessage());
            }
        }
        return true;
    }


}
