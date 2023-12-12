package com.video.store.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String title;

    @NotNull
    private Date releaseDate;

    @NotNull
    private boolean available;

    @NotNull
    private Integer numberOfCopies;

    private String director;

    private List<String> cast;

    private String synopsis;
}
