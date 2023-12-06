package com.video.store.videostore.domain.service;

import com.video.store.videostore.api.mapping.MovieMapper;
import com.video.store.videostore.infrastructure.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    /**
     * TODO
     * Create CRUD
     */
}
