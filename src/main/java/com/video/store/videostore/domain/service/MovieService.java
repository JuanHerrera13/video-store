package com.video.store.videostore.domain.service;

import com.video.store.videostore.api.dto.MovieDto;
import com.video.store.videostore.api.mapping.MovieMapper;
import com.video.store.videostore.domain.entity.Movie;
import com.video.store.videostore.exception.MovieException;
import com.video.store.videostore.exception.NotFoundException;
import com.video.store.videostore.infrastructure.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.video.store.videostore.domain.enumerator.Error.MOVIE_ALREADY_EXISTS;
import static com.video.store.videostore.domain.enumerator.Error.MOVIE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public Movie findMovieById(String id) {
        return this.movieRepository.findById(id).orElseThrow(() ->
                new NotFoundException(MOVIE_NOT_FOUND.getErrorDescription()));
    }

    public Movie findMovieByTitle(String title) {
        return this.movieRepository.findTopByTitle(title).orElseThrow(() ->
                new NotFoundException(MOVIE_NOT_FOUND.getErrorDescription()));
    }

    public MovieDto addMovie(MovieDto movieDto) {
        final Optional<Movie> validatingMovie = this.movieRepository.findTopByTitle(movieDto.getTitle());
        if (validatingMovie.isPresent()) {
            log.error("Movie already exists in db");
            throw new MovieException(MOVIE_ALREADY_EXISTS.getErrorDescription());
        }
        final Movie movie = this.movieMapper.movieDtoToMovie(movieDto);
        log.info("Adding movie to db");
        this.movieRepository.save(movie);
        log.info("Movie added to db");
        return this.movieMapper.movieToMovieDto(movie);
    }
}
