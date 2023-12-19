package com.video.store.api.mapping;

import com.video.store.api.dto.movie.MovieCreationDto;
import com.video.store.api.dto.movie.MovieDto;
import com.video.store.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MovieMapper {

    Movie movieDtoToMovie(MovieDto movieDto);

    @Mapping(source = "title", target = "title")
    MovieDto movieToMovieDto(Movie movie);

    List<MovieDto> movieListToMovieDtoList(List<Movie> movies);

    Movie movieCreationDtoToMovie(MovieCreationDto movieCreationDto);
}
