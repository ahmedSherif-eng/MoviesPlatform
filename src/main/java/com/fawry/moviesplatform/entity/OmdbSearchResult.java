package com.fawry.moviesplatform.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OmdbSearchResult {
 private List<Movie> SearchResult;
}
