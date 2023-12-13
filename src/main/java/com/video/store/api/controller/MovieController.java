package com.video.store.api.controller;

import com.video.store.api.dto.MovieCreationDto;
import com.video.store.api.dto.MovieDeleteDto;
import com.video.store.api.dto.MovieDto;
import com.video.store.api.dto.MovieUpdateDto;
import com.video.store.domain.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/video-store")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> fetchMovies() {
        return this.movieService.fetchMoviesList();
    }

    @GetMapping("/movies/{title}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto fetchMovieByTitle(@PathVariable String title) {
        return this.movieService.findMovieByTitle(title);
    }

    @GetMapping("/movies/")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> fetchMoviesByDirector(@RequestParam(value = "director") String director) {
        return this.movieService.findMoviesByDirector(director);
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