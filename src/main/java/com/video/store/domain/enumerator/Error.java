package com.video.store.domain.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    // NOT FOUND MESSAGES
    MOVIE_NOT_FOUND("MOVIE_NOT_FOUND", "Movie not found"),
    DIRECTOR_HAS_NO_MOVIES("DIRECTOR_HAS_NO_MOVIES", "No film found for the given director"),
    GENRE_HAS_NO_MOVIES("GENRE_HAS_NO_MOVIES", "No film found for the given genre"),
    DIRECTOR_AND_GENRE_HAVE_NO_MOVIES("DIRECTOR_AND_GENRE_HAVE_NO_MOVIES", "No film found for " +
            "the given director and genre"),

    // ALREADY EXISTS EXCEPTION MESSAGES,
    MOVIE_ALREADY_EXISTS("MOVIE_ALREADY_EXISTS", "Movie already exists");

    private final String errorMessage;
    private final String errorDescription;
}