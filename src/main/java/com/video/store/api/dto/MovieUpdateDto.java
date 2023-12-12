package com.video.store.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieUpdateDto {

    @NotBlank
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private Integer numberOfCopies;

    private String director;

    private List<String> cast;

    private String synopsis;
}
