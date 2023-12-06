package com.video.store.videostore.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    private String title;

    private String description;

    private String director;

    private List<String> cast;

    private boolean available;

    private Integer numberOfCopies;
}
