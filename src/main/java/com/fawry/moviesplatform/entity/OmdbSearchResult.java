package com.fawry.moviesplatform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fawry.moviesplatform.DTO.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OmdbSearchResult {
 @JsonProperty("Search")
 private List<MovieDTO> Search;

 @JsonProperty("totalResults")
 private String totalResults;

 @JsonProperty("Response")
 private String Response;
}
