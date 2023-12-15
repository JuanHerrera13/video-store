package com.video.store.domain.service;

import com.video.store.api.dto.movie.MovieCreationDto;
import com.video.store.api.dto.movie.MovieDto;
import com.video.store.api.dto.movie.MovieUpdateDto;
import com.video.store.api.mapping.MovieMapper;
import com.video.store.domain.entity.Movie;
import com.video.store.exception.MovieException;
import com.video.store.exception.NotFoundException;
import com.video.store.infrastructure.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.video.store.domain.enumerator.Error.*;
import static com.video.store.domain.service.utils.ServiceUtils.updateIfNonNull;

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
     * Fetch all films that match with the director and genres
     *
     * @param director director's name
     * @param genres   genres list
     * @return a list of movies that match with both genre and director
     */
    public List<MovieDto> findMoviesByDirectorAndGenres(String director, List<String> genres) {
        final List<Movie> movies = this.movieRepository.findAllByDirectorIgnoreCaseAndGenresInIgnoreCase(director, genres);
        if (movies.isEmpty()) {
            throw new NotFoundException(DIRECTOR_AND_GENRE_HAVE_NO_MOVIES.getErrorDescription());
        }
        log.info("Fetching director's and genre's movies list");
        return this.movieMapper.movieListToMovieDtoList(movies);
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
            throw new NotFoundException(DIRECTOR_HAS_NO_MOVIES.getErrorDescription());
        }
        log.info("Fetching director's movies list");
        return this.movieMapper.movieListToMovieDtoList(movies);
    }

    /**
     * Fetch all movies with the passed genres
     *
     * @param genres movie's genres
     * @return a list of movies with the given genres
     */
    public List<MovieDto> findMoviesByGenre(List<String> genres) {
        final List<Movie> movies = this.movieRepository.findByGenresInIgnoreCase(genres);
        if (movies.isEmpty()) {
            throw new NotFoundException(GENRE_HAS_NO_MOVIES.getErrorDescription());
        }
        log.info("Fetching the films list of the given genre");
        return this.movieMapper.movieListToMovieDtoList(movies);
    }

    /**
     * Fetch all movies present in the db
     *
     * @return a movie data transfer object list
     */
    public List<MovieDto> fetchMoviesList() {
        final List<Movie> movies = this.movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new NotFoundException(NO_MOVIES_FOUND.getErrorDescription());
        }
        log.info("Fetching all movies in db");
        return this.movieMapper.movieListToMovieDtoList(movies);
    }

    /**
     * Adds a movie in db
     *
     * @param movieCreationDto movie creation data transfer object
     * @return an added movie data transfer object
     * @throws MovieException when is given a title movie that already exists
     */
    public MovieDto addMovie(MovieCreationDto movieCreationDto) {
        final Optional<Movie> validatingMovie = this.movieRepository.findTopByTitleIgnoreCase(
                movieCreationDto.getTitle());
        if (validatingMovie.isPresent()) {
            log.error("Movie already exists in db");
            throw new MovieException(MOVIE_ALREADY_EXISTS.getErrorDescription());
        }
        final Movie movie = this.movieMapper.movieCreationDtoToMovie(movieCreationDto);
        log.info("Adding movie to db");
        this.movieRepository.save(movie);
        log.info("Movie added");
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
        updateIfNonNull(movieUpdateDto.getGenres(), updatedMovie::setGenres);
        log.info("Updating movie in db");
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
        log.info("Deleting moving in db");
        this.movieRepository.delete(movie);
        log.info("Movie deleted");
    }
}
