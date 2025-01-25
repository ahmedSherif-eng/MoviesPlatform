package com.fawry.moviesplatform.repository;

import com.fawry.moviesplatform.entity.Movie;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByImdbID(String imdbID);

    @Query("SELECT m.imdbID FROM Movie m")
    List<String> findAllIds();

    Page<Movie> findByTitleContaining(@NonNull String title, Pageable pageable);

    @Transactional
    void deleteByTitle(@NonNull String title);
}
