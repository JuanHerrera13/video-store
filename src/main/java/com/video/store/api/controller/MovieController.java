package com.video.store.api.controller;

import com.video.store.api.dto.movie.MovieCreationDto;
import com.video.store.api.dto.movie.MovieDeleteDto;
import com.video.store.api.dto.movie.MovieDto;
import com.video.store.api.dto.movie.MovieUpdateDto;
import com.video.store.domain.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequiredArgsConstructor
public class MovieController extends RootController {

    private final MovieService movieService;

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> fetchMovies(@Valid @RequestParam(required = false) String director,
                                      @RequestParam(required = false) List<String> genres) {
        if (Objects.nonNull(director) && Objects.nonNull(genres)) {
            return this.movieService.findMoviesByDirectorAndGenres(director, genres);
        } else if (Objects.nonNull(director)) {
            return this.movieService.findMoviesByDirector(director);
        } else if (Objects.nonNull(genres)) {
            return this.movieService.findMoviesByGenre(genres);
        } else {
            return this.movieService.fetchMoviesList();
        }
    }

    @GetMapping("/movies/{title}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto fetchMovieByTitle(@PathVariable String title) {
        return this.movieService.findMovieByTitle(title);
    }

    @PostMapping("/movies.add")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@Valid @RequestBody MovieCreationDto movieCreationDto) {
        return this.movieService.addMovie(movieCreationDto);
    }

    @PutMapping("/movies.update")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto updateMovie(@Valid @RequestBody MovieUpdateDto movieUpdateDto) {
        return this.movieService.updateMovie(movieUpdateDto);
    }

    @DeleteMapping("/movies.delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@RequestBody MovieDeleteDto movieDeleteDto) {
        this.movieService.deleteMovie(movieDeleteDto.getTitle());
    }
}