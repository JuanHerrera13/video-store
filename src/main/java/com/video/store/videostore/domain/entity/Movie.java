package com.video.store.videostore.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    private String title;

    private String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
