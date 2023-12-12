package com.video.store.infrastructure.repository;

import com.video.store.domain.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findTopByTitleIgnoreCase(String title);
}
