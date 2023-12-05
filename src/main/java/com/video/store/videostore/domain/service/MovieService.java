package com.video.store.videostore.domain.service;

import com.video.store.videostore.domain.entity.Movie;
import com.video.store.videostore.infrastructure.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void save(String title, String description) {
        Movie movie = new Movie(title, description);
        this.movieRepository.save(movie);
    }
}
