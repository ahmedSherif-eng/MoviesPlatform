package com.fawry.moviesplatform.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDTO {
 @JsonProperty("Search")
 private List<MovieDTO> Search;

 @JsonProperty("totalResults")
 private String totalResults;

 @JsonProperty("Response")
 private String Response;
}
