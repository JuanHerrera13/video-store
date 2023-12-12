package com.video.store.domain.service;

import com.video.store.api.dto.MovieCreationDto;
import com.video.store.api.dto.MovieDto;
import com.video.store.api.mapping.MovieMapper;
import com.video.store.domain.entity.Movie;
import com.video.store.exception.MovieAlreadyExistsException;
import com.video.store.exception.NotFoundException;
import com.video.store.infrastructure.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.video.store.domain.enumerator.Error.MOVIE_ALREADY_EXISTS;
import static com.video.store.domain.enumerator.Error.MOVIE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieDto findMovieByTitle(String title) {
        final Movie movie = this.movieRepository.findTopByTitleIgnoreCase(title).orElseThrow(() ->
                new NotFoundException(MOVIE_NOT_FOUND.getErrorDescription()));
        log.info("Movie found");
        return this.movieMapper.movieToMovieDto(movie);
    }

    public List<MovieDto> fetchMoviesList() {
        final List<Movie> movies = this.movieRepository.findAll();
        log.info("Fetching all movies in db");
        return this.movieMapper.movieListToMovieDtoList(movies);
    }

    public MovieDto addMovie(MovieCreationDto movieCreationDto) {
        final Optional<Movie> validatingMovie = this.movieRepository.findTopByTitleIgnoreCase(movieCreationDto.getTitle());
        if (validatingMovie.isPresent()) {
            log.error("Movie already exists in db");
            throw new MovieAlreadyExistsException(MOVIE_ALREADY_EXISTS.getErrorDescription());
        }
        final Movie movie = this.movieMapper.movieCreationDtoToMovie(movieCreationDto);
        log.info("Adding movie to db");
        this.movieRepository.save(movie);
        log.info("Movie added to db");
        return this.movieMapper.movieToMovieDto(movie);
    }

    public void deleteMovie(String title) {
        final MovieDto movieDto = findMovieByTitle(title);
        final Movie movie = this.movieMapper.movieDtoToMovie(movieDto);
        this.movieRepository.delete(movie);
        log.info("Movie deleted");
    }
}
