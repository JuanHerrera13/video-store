package com.video.store.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    @NotBlank
    private String title;

    private String description;

    private String director;

    @NotNull
    private Date releaseDate;

    private List<String> cast;

    @NotNull
    private boolean available;

    @NotNull
    private Integer numberOfCopies;
}
