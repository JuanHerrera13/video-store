package com.video.store.videostore.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class MovieDto {

    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String title;

    private String description;

    private String director;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private List<String> cast;

    @NotNull
    private boolean available;

    @NotNull
    private Integer numberOfCopies;
}
