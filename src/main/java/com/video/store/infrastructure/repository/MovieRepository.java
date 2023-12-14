package com.video.store.infrastructure.repository;

import com.video.store.domain.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findTopByTitleIgnoreCase(String title);

    List<Movie> findAllByDirectorIgnoreCase(String director);

    List<Movie> findByGenresInIgnoreCase(List<String> genres);

    List<Movie> findAllByDirectorIgnoreCaseAndGenresInIgnoreCase(String director, List<String> genres);
}
