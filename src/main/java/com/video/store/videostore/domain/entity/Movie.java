package com.video.store.videostore.domain.entity;

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

    private String title;

    private String description;

    private String director;

    private Date releaseDate;

    private List<String> cast;

    private boolean available;

    private Integer numberOfCopies;
}
