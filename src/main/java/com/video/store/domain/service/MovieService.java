package com.video.store.domain.service;

import com.video.store.api.dto.MovieCreationDto;
import com.video.store.api.dto.MovieDto;
import com.video.store.api.dto.MovieUpdateDto;
import com.video.store.api.mapping.MovieMapper;
import com.video.store.domain.entity.Movie;
import com.video.store.exception.DirectorException;
import com.video.store.exception.MovieAlreadyExistsException;
import com.video.store.exception.NotFoundException;
import com.video.store.infrastructure.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static com.video.store.domain.enumerator.Error.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    /**
     * Find a movie by its title
     *
     * @param title movie's title
     * @return a movie data transfer object
     * @throws NotFoundException when the given title doesn't match with any movie present in db
     */
    public MovieDto findMovieByTitle(String title) {
        final Movie movie = this.movieRepository.findTopByTitleIgnoreCase(title).orElseThrow(() ->
                new NotFoundException(MOVIE_NOT_FOUND.getErrorDescription()));
        log.info("Movie found");
        return this.movieMapper.movieToMovieDto(movie);
    }

    /**
     * Fetch all movies from a given director
     *
     * @param director director's name
     * @return a list of movies from the given director
     */
    public List<MovieDto> findMoviesByDirector(String director) {
        final List<Movie> movies = this.movieRepository.findAllByDirectorIgnoreCase(director);
        if (movies.isEmpty()) {
            throw new DirectorException(DIRECTOR_HAS_NO_MOVIES.getErrorDescription());
        }
        return this.movieMapper.movieListToMovieDtoList(movies);
    }

    /**
     * Fetch all movies present in the db
     *
     * @return a movie data transfer object list
     */
    public List<MovieDto> fetchMoviesList() {
        final List<Movie> movies = this.movieRepository.findAll();
        log.info("Fetching all movies in db");
        return this.movieMapper.movieListToMovieDtoList(movies);
    }

    /**
     * Adds a movie in db
     *
     * @param movieCreationDto movie creation data transfer object
     * @return an added movie data transfer object
     * @throws MovieAlreadyExistsException when is given a title movie that already exists
     */
    public MovieDto addMovie(MovieCreationDto movieCreationDto) {
        final Optional<Movie> validatingMovie = this.movieRepository.findTopByTitleIgnoreCase(
                movieCreationDto.getTitle());
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

    /**
     * Updates a movie in db
     *
     * @param movieUpdateDto movie update data transfer object
     * @return an updated movie data transfer object
     */
    public MovieDto updateMovie(MovieUpdateDto movieUpdateDto) {
        final MovieDto movieDto = findMovieByTitle(movieUpdateDto.getTitle());
        final Movie updatedMovie = this.movieMapper.movieDtoToMovie(movieDto);
        updateIfNonNull(movieUpdateDto.getDirector(), updatedMovie::setDirector);
        updateIfNonNull(movieUpdateDto.getCast(), updatedMovie::setCast);
        updateIfNonNull(movieUpdateDto.getSynopsis(), updatedMovie::setSynopsis);
        updateIfNonNull(movieUpdateDto.getReleaseDate(), updatedMovie::setReleaseDate);
        updateIfNonNull(movieUpdateDto.getNumberOfCopies(), updatedMovie::setNumberOfCopies);
        this.movieRepository.save(updatedMovie);
        log.info("Movie updated");
        return this.movieMapper.movieToMovieDto(updatedMovie);
    }

    /**
     * Deletes a Movie by its title in db
     *
     * @param title movie title
     */
    public void deleteMovie(String title) {
        final MovieDto movieDto = findMovieByTitle(title);
        final Movie movie = this.movieMapper.movieDtoToMovie(movieDto);
        this.movieRepository.delete(movie);
        log.info("Movie deleted");
    }

    /**
     * Method used to check if newValue is null and if so,
     * update old value to new
     *
     * @param newValue newValue
     * @param setter   setter
     * @param <T>      value's type
     */
    private <T> void updateIfNonNull(T newValue, Consumer<T> setter) {
        if (Objects.nonNull(newValue)) {
            setter.accept(newValue);
        }
    }
}
