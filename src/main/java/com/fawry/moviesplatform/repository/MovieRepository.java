package com.fawry.moviesplatform.repository;

import com.fawry.moviesplatform.entity.Movie;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Movie> findById(Long Id);

    void deleteById(Long Id);

    Optional<Movie> findByImdbID(String imdbID);

    @Query("SELECT m.imdbID FROM Movie m")
    List<String> findAllIds();

    Page<Movie> findAll(Pageable pageable);


    Page<Movie> findByTitleContaining(String title, Pageable pageable);

    @Modifying
    void deleteByTitle(@NonNull String title);
    Page<Movie> findAll(Pageable pageable);
    Optional<Movie> findByTitle(String title);
}
