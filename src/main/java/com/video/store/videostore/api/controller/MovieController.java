package com.video.store.videostore.api.controller;

import com.video.store.videostore.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * TODO
     * Create APIs to manage movies
     */
}
