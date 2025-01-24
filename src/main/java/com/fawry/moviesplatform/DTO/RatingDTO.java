package com.fawry.moviesplatform.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RatingDTO {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;
}
