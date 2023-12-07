package com.video.store.api.controller;

import com.video.store.api.dto.MovieDto;
import com.video.store.domain.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video-store")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies.add")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@RequestBody MovieDto movieDto) {
        return this.movieService.addMovie(movieDto);
    }
}