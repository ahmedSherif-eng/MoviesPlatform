package com.fawry.moviesplatform.repository;

import com.fawry.moviesplatform.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Movie> findById(Long Id);
    void deleteById(Long Id);
    
    @Query("SELECT m.title FROM Movie m")
    List<String> findAllTitles();
    
    void deleteByTitle(@NonNull String title);
    Page<Movie> findAll(Pageable pageable);


}
