package com.video.store.api.mapping;

import com.video.store.api.dto.movie.MovieCreationDto;
import com.video.store.api.dto.movie.MovieDto;
import com.video.store.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "numberOfCopies", target = "numberOfCopies")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "cast", target = "cast")
    @Mapping(source = "synopsis", target = "synopsis")
    Movie movieDtoToMovie(MovieDto movieDto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "numberOfCopies", target = "numberOfCopies")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "cast", target = "cast")
    @Mapping(source = "synopsis", target = "synopsis")
    MovieDto movieToMovieDto(Movie movie);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "numberOfCopies", target = "numberOfCopies")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "cast", target = "cast")
    @Mapping(source = "synopsis", target = "synopsis")
    Movie movieCreationDtoToMovie(MovieCreationDto movieCreationDto);

    List<MovieDto> movieListToMovieDtoList(List<Movie> movies);
}
