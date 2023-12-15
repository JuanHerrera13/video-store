package com.video.store.api.dto.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieDto {

    @NotBlank
    private String id;

    @NotBlank
    private String title;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotNull
    private boolean available;

    @NotNull
    private Integer numberOfCopies;

    @NotBlank
    private List<String> genres;

    private String director;

    private List<String> cast;

    private String synopsis;
}
