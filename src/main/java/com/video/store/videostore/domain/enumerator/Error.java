package com.video.store.videostore.domain.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    // NOT FOUND MESSAGES
    MOVIE_NOT_FOUND("MOVIE_NOT_FOUND", "Movie not found"),

    // ALREADY EXISTS EXCEPTION
    MOVIE_ALREADY_EXISTS("MOVIE_ALREADY_EXISTS", "Movie already exists");

    private final String errorMessage;
    private final String errorDescription;
}