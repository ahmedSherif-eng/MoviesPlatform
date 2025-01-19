package com.fawry.moviesplatform.repository;

import com.fawry.moviesplatform.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
