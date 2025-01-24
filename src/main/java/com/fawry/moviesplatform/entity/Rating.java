package com.fawry.moviesplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String value;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}