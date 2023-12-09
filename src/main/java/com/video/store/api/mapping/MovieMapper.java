package com.video.store.api.mapping;

import com.video.store.api.dto.MovieDto;
import com.video.store.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MovieMapper {

    @Mapping(source = "cast", target = "cast")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "numberOfCopies", target = "numberOfCopies")
    Movie movieDtoToMovie(MovieDto movieDto);

    @Mapping(source = "cast", target = "cast")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "numberOfCopies", target = "numberOfCopies")
    MovieDto movieToMovieDto(Movie movie);
}
