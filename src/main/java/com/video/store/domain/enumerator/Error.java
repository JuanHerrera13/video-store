package com.video.store.domain.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    // NOT FOUND MESSAGES
    MOVIE_NOT_FOUND("MOVIE_NOT_FOUND", "Movie not found"),

    // ALREADY EXISTS EXCEPTION MESSAGES
    MOVIE_ALREADY_EXISTS("MOVIE_ALREADY_EXISTS", "Movie already exists"),

    // DIRECTOR MESSAGES
    DIRECTOR_HAS_NO_MOVIES("DIRECTOR_HAS_NO_MOVIES", "The director has no movies assigned");

    private final String errorMessage;
    private final String errorDescription;
}