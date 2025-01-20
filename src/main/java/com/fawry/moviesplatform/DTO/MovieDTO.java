package com.fawry.moviesplatform.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class MovieDTO {

    @JsonProperty("Title")
    @NotEmpty(message = "Title can not be empty or null")
    @Size(max = 100, message = "The length of the title should not exceed 100 character")
    private String Title;

    @JsonProperty("Year")
    @NotEmpty(message = "Year can not be empty or null")
    @Size(min = 4, max = 4, message = "The length of the year should be 4 character")
    private String Year;

    @JsonProperty("imdbID")
    @NotEmpty(message = "imdbID can not be empty or null")
    @Pattern(regexp = "^tt\\d{7}$", message = "imdbID must start with 'tt' followed by 7 numeric digits")//tt0271732
    private String imdbID;

    @JsonProperty("Type")
    @Pattern(regexp = "^(movie|series|episode)$", message = "Type must be one of 'movie', 'series', or 'episode'")
    private String Type;

    @JsonProperty("Poster")
    @Pattern(
            regexp = "^https://m\\.media-amazon\\.com/images.*\\.jpg$",
            message = "Poster URL must start with 'https://m.media-amazon.com/images' and end with '.jpg'"
    )
    private String Poster;

    // Getters and Setters
}