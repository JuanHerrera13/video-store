package com.video.store.api.controller;

import com.video.store.api.dto.MovieDto;
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

    @PostMapping("/movies.add")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@Valid @RequestBody MovieDto movieDto) {
        return this.movieService.addMovie(movieDto);
    }

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
}