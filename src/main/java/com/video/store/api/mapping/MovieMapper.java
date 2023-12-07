package com.video.store.api.mapping;

import com.video.store.api.dto.MovieDto;
import com.video.store.domain.entity.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    Movie movieDtoToMovie(MovieDto movieDto);

    MovieDto movieToMovieDto(Movie movie);
}
